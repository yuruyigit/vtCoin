package com.block.vtCoin.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.net.util.L;
import com.block.vtCoin.constant.Constant;
import com.block.vtCoin.R;
import com.block.vtCoin.entity.SmsEntity;
import com.block.vtCoin.login.p.RequestSmsPresenter;
import com.block.vtCoin.login.p.VerifySmsPresenter;
import com.block.vtCoin.login.v.RequestSmsViewImpl;
import com.block.vtCoin.login.v.VerifySmsViewImpl;
import com.block.vtCoin.main.BaseActivity;
import com.block.vtCoin.login.phone_location.TelephoneLocationActivity;
import com.block.vtCoin.request.ApiAction;
import com.block.vtCoin.request.MyModel;
import com.block.vtCoin.util.CountDownUtil;
import com.block.vtCoin.widget.editText.ClearEditText;
import com.block.vtCoin.widget.editText.MyTextWatcher;
import com.block.vtCoin.widget.title.NormalTitleBar;
import com.zhy.android.percent.support.PercentRelativeLayout;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

public class PhoneRegisterActivity extends BaseActivity {
    @Bind(R.id.tv_city)
    TextView tvCity;
    @Bind(R.id.rl_city)
    PercentRelativeLayout rlCity;
    @Bind(R.id.tv_area_code)
    TextView tvAreaCode;
    @Bind(R.id.et_phone)
    ClearEditText etPhone;
    @Bind(R.id.et_code)
    ClearEditText etCode;
    @Bind(R.id.get_code)
    TextView getCode;
    @Bind(R.id.next)
    TextView next;
    @Bind(R.id.agree)
    TextView agree;
    @Bind(R.id.terms)
    TextView terms;
    @Bind(R.id.email_register)
    TextView emailRegister;
    @Bind(R.id.scrollView)
    ScrollView scrollView;
    private RequestSmsPresenter requestSmsPresenter;
    private VerifySmsPresenter verifySmsPresenter;
    private String city, areaCode, phone, code;
    private CountDownUtil countDownUtil;
    private boolean isAgree = true;
    private boolean isSmsSuccess = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_register);
        setUltimateBar(R.color.white0);
        initView();
        initData();
    }

    private void initData() {
        city = "中国";
        areaCode = "86";
    }

    private void initView() {
        etPhone.addTextChangedListener(new MyTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().isEmpty()) {
                    setClickable(getCode, false);
                    setClickable(next, false);
                    etCode.setText("");
                } else if (!s.toString().isEmpty()) {
                    setClickable(getCode, true);
                    if (!etCode.getText().toString().trim().isEmpty()) {
                        setClickable(next, true);
                    }
                }
            }
        });
        etCode.addTextChangedListener(new MyTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().isEmpty())
                    setClickable(next, false);
                else if (!s.toString().isEmpty() && !etPhone.getText().toString().isEmpty())
                    setClickable(next, true);
            }
        });
        countDownUtil = new CountDownUtil(this, getCode, Constant.SendCodeTime, 1000,
                new CountDownUtil.onFinishListener() {
                    @Override
                    public void finish() {
                        L.i("finish");
                        countDownUtil.cancel();
                        setClickable(getCode, true);
                    }
                });
    }

    /**
     * @return
     */
    @Override
    public View getTitleBar() {
        NormalTitleBar normalTitleBar = getNormalTitleBar().setTitle(getString(R.string.telephone_register)).setBackground(R.color.white0);
        normalTitleBar.setLeftDrawable(R.mipmap.return_01).setTitleTextColor(R.color.black1);
        return normalTitleBar;
    }

    @Override
    protected List<BasePresenter> getPresenter(List<BasePresenter> presenters) {
        requestSmsPresenter = new RequestSmsPresenter();
        requestSmsPresenter.setModelView(new MyModel<SmsEntity>(), new RequestSmsViewImpl(this, ApiAction.Account_SendSmsCode, 0));
        verifySmsPresenter=new VerifySmsPresenter();
        verifySmsPresenter.setModelView(new MyModel<SmsEntity>(),new VerifySmsViewImpl(this,ApiAction.Account_VerifyRegSMSCode,0));
        presenters.add(requestSmsPresenter);
        presenters.add(verifySmsPresenter);
        return presenters;
    }

    public boolean isNext() {
        city = tvCity.getText().toString().trim();
        areaCode = tvAreaCode.getText().toString().trim();
        phone = etPhone.getText().toString().trim();
        code = etCode.getText().toString().trim();
        if (!isAgree) {
            showTip(getString(R.string.please_read_terms));
            return false;
        }
        if(!isSmsSuccess){
            showTip(getString(R.string.tip_please_get_cod));
            return true;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 0 && data != null) {
            city = data.getStringExtra("city");
            areaCode = data.getStringExtra("areaCode");
            tvCity.setText(city);
            tvAreaCode.setText(areaCode);
        }
    }

    @OnClick({R.id.rl_city, R.id.get_code, R.id.next, R.id.agree, R.id.terms, R.id.email_register})

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_city:
                Intent intent = new Intent(this, TelephoneLocationActivity.class);
                intent.putExtra("from", 0);
                startActivityForResult(intent, 0);
                break;
            case R.id.get_code:
                countDownUtil.start();
                setClickable(getCode, false);
                requestSmsPresenter.requestCode();
                break;
            case R.id.next:
                if(isNext()){
                    verifySmsPresenter.verifyCode();
                }
                break;
            case R.id.agree:
                if (isAgree) {
                    isAgree = false;
                    setLeftDrawable(agree, R.mipmap.circle);
                } else {
                    isAgree = true;
                    setLeftDrawable(agree, R.mipmap.circle_click);
                }
                break;
            case R.id.terms:
                toActivity(TermsActivity.class);
                break;
            case R.id.email_register:
                startActivity(new Intent(this, EmailRegisterActivity.class).putExtra("from", 0));
                break;
        }
    }

    @Override
    protected void onDestroy() {
        countDownUtil.cancel();
        super.onDestroy();
    }

    /*获取验证码*/
    public Map<String, Object> getRequestSmsMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("Country", "chain");
        map.put("CountryCode", areaCode);
        map.put("PhoneNumber", etPhone.getText().toString().trim());
        map.put("Type", 0);
        map.put("Lang", "zh_CN");
        return map;
    }

    public void setRequestSms(boolean isSuccess) {
        this.isSmsSuccess = isSuccess;
        if (!isSuccess) {
            countDownUtil.onFinish();
        }
    }
    /*验证验证码*/
    public Map<String, Object> getVerifySmsMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", code);
        map.put("Type", 0);
        return map;
    }

    public void setVerifySms(boolean isSuccess) {
        if (!isSuccess) {
            Map<String,Object> map=new HashMap<>();
            map.put("from",0);
            map.put("phone",phone);
            map.put("code",code);
            L.i(phone+"   "+code);
            toActivity(SetPasswordActivity.class,map);
        }
    }
}
