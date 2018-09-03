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
import com.block.vtCoin.request.ApiAction;
import com.block.vtCoin.request.MyModel;
import com.block.vtCoin.util.CountDownUtil;
import com.block.vtCoin.widget.editText.ClearEditText;
import com.block.vtCoin.widget.editText.MyTextWatcher;
import com.block.vtCoin.widget.title.NormalTitleBar;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

public class FindPasswordActivity extends BaseActivity {
    @Bind(R.id.et_phone)
    ClearEditText etPhone;
    @Bind(R.id.et_code)
    ClearEditText etCode;
    @Bind(R.id.get_code)
    TextView getCode;
    @Bind(R.id.next)
    TextView next;
    @Bind(R.id.find_by_email)
    TextView findByEmail;
    @Bind(R.id.scrollView)
    ScrollView scrollView;
    private RequestSmsPresenter requestSmsPresenter;
    private VerifySmsPresenter verifySmsPresenter;
    private boolean isSmsSuccess = false;
    private String phone, code;
    private CountDownUtil countDownUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_password);
        setUltimateBar(R.color.white0);
        initView();

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
        NormalTitleBar normalTitleBar = getNormalTitleBar().setTitle(getString(R.string.find_password)).setBackground(R.color.white0);
        normalTitleBar.setLeftDrawable(R.mipmap.return_01).setTitleTextColor(R.color.black1);
        return normalTitleBar;
    }

    @Override
    protected List<BasePresenter> getPresenter(List<BasePresenter> presenters) {
        requestSmsPresenter = new RequestSmsPresenter();
        requestSmsPresenter.setModelView(new MyModel<SmsEntity>(), new RequestSmsViewImpl(this, ApiAction.Account_SendSmsCode, 1));
        verifySmsPresenter = new VerifySmsPresenter();
        verifySmsPresenter.setModelView(new MyModel<SmsEntity>(), new VerifySmsViewImpl(this, ApiAction.Account_VerifyRegSMSCode, 1));
        presenters.add(requestSmsPresenter);
        presenters.add(verifySmsPresenter);
        return presenters;
    }

    public boolean isNext() {
        phone = etPhone.getText().toString().trim();
        code = etCode.getText().toString().trim();
        if (phone.isEmpty() || code.isEmpty()) {
            return false;
        } else if (!isSmsSuccess) {
            showTip("请获取验证码");
            return false;
        }
        return true;
    }


    @Override
    protected void onDestroy() {
        countDownUtil.cancel();
        super.onDestroy();
    }

    @OnClick({R.id.get_code, R.id.next, R.id.find_by_email})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.get_code:
                countDownUtil.start();
                setClickable(getCode, false);
                requestSmsPresenter.requestCode();
                break;
            case R.id.next:
                if (isNext() && isHasNet()) {
                    verifySmsPresenter.verifyCode();
                }
                break;
            case R.id.find_by_email:
                startActivity(new Intent(this, EmailRegisterActivity.class).putExtra("from", 1));
                break;
        }
    }

    /*获取验证码*/
    public Map<String, Object> getRequestSmsMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("Country", "chain");
        map.put("CountryCode", "86");
        map.put("PhoneNumber", etPhone.getText().toString().trim());
        map.put("Type", 1);
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
        map.put("Type", 1);
        return map;
    }

    public void setVerifySms(boolean isSuccess) {
        if (isSuccess) {
            Map<String, Object> map = new HashMap<>();
            map.put("from", 2);
            map.put("phone", phone);
            map.put("code", code);
            L.i(phone + "   " + code);
            toActivity(SetPasswordActivity.class, map);
        }
    }
}
