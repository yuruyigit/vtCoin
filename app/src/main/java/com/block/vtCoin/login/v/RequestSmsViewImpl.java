package com.block.vtCoin.login.v;

import android.content.Context;

import com.block.net.mvp.view.ICommonView;
import com.block.vtCoin.R;
import com.block.vtCoin.entity.SmsEntity;
import com.block.vtCoin.login.FindPasswordActivity;
import com.block.vtCoin.login.PhoneRegisterActivity;
import com.block.vtCoin.main.BaseActivity;
import com.block.vtCoin.mine.setting.modify_password.PhoneFindBackPasswordActivity;

import java.util.Map;

/**
 * @Description 获取验证码的公共view
 */
public class RequestSmsViewImpl implements ICommonView<SmsEntity> {
    private final BaseActivity activity;
    private String urlAction = "";
    /**
     * 0--手机号注册
     * 1--忘记登陆密码
     */
    private final int type;

    public RequestSmsViewImpl(BaseActivity activity, String urlAction, int type) {
        this.activity = activity;
        this.urlAction = urlAction;
        this.type = type;
    }

    @Override
    public Map<String, Object> getParameters() {
        Map<String, Object> map = null;
        switch (type) {
            case 0://手机号注册
                map = ((PhoneRegisterActivity) activity).getRequestSmsMap();
                break;
            case 1://忘记登陆密码
                map = ((FindPasswordActivity) activity).getRequestSmsMap();
                break;
            case 2://通过手机号找回登陆密码
                map = ((PhoneFindBackPasswordActivity) activity).getRequestSmsMap();
                break;
        }

        return map;
    }

    @Override
    public void onError(int code, String msg) {
        activity.showTip(msg);
        switch (type) {
            case 0://手机号注册
                ((PhoneRegisterActivity) activity).setRequestSms(false);
                break;
            case 1://忘记登陆密码
                ((FindPasswordActivity) activity).setRequestSms(false);
                break;
            case 2://忘记登陆密码
                ((PhoneFindBackPasswordActivity) activity).setRequestSms(false);
                break;
        }
    }

    @Override
    public void onCompleted(SmsEntity entity) {
        activity.showTip(activity.getString(R.string.tip_sms_send_success));
        switch (type) {
            case 0://手机号注册
                ((PhoneRegisterActivity) activity).setRequestSms(true);
                break;
            case 1://忘记登陆密码
                ((FindPasswordActivity) activity).setRequestSms(true);
                break;
            case 2://忘记登陆密码
                ((PhoneFindBackPasswordActivity) activity).setRequestSms(true);
                break;
        }
    }

    @Override
    public void loading() {
        activity.loadDialog();
    }

    @Override
    public void dismiss() {
        activity.dismissDialog();
    }

    @Override
    public Context getContext() {
        return activity;
    }

    @Override
    public String getUrlAction() {
        return urlAction;
    }

    @Override
    public Class<SmsEntity> getEClass() {
        return SmsEntity.class;
    }
}
