package com.block.vtCoin.mine.setting.modify_password;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
import com.block.vtCoin.login.SetPasswordActivity;
import com.block.vtCoin.request.ApiAction;
import com.block.vtCoin.request.MyModel;
import com.block.vtCoin.util.CountDownUtil;
import com.block.vtCoin.widget.editText.ClearEditText;
import com.block.vtCoin.widget.editText.MyTextWatcher;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

public class PhoneFindBackPasswordActivity extends BaseActivity {
    @Bind(R.id.et_phone)
    ClearEditText etPhone;
    @Bind(R.id.et_code)
    ClearEditText etCode;
    @Bind(R.id.get_code)
    TextView getCode;
    @Bind(R.id.next)
    TextView next;
    private int from;
    private RequestSmsPresenter requestSmsPresenter;
    private VerifySmsPresenter verifySmsPresenter;
    private boolean isSmsSuccess = false;
    private String phone, code;
    private CountDownUtil countDownUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handIntent();
        setContentView(R.layout.activity_phone_find_back_password);
        setUltimateBar(R.color.blue1);
        initView();
    }

    private void handIntent() {
         /*0--找回登录密码，1--找回交易密码*/
        from = getIntent().getIntExtra("from", 0);
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
        return getNormalTitleBar().setTitle(getString(R.string.find_password));
    }

    @Override
    protected List<BasePresenter> getPresenter(List<BasePresenter> presenters) {
        requestSmsPresenter = new RequestSmsPresenter();
        requestSmsPresenter.setModelView(new MyModel<SmsEntity>(), new RequestSmsViewImpl(this, ApiAction.Account_SendSmsCode, 2));
        verifySmsPresenter = new VerifySmsPresenter();
        verifySmsPresenter.setModelView(new MyModel<SmsEntity>(), new VerifySmsViewImpl(this, ApiAction.Account_VerifyRegSMSCode, 2));
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

    @OnClick({R.id.get_code, R.id.next})
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
