package com.block.vtCoin.login.v;

import android.content.Context;

import com.block.net.mvp.view.ICommonView;
import com.block.vtCoin.R;
import com.block.vtCoin.entity.SmsEntity;
import com.block.vtCoin.login.FindPasswordActivity;
import com.block.vtCoin.login.PhoneRegisterActivity;
import com.block.vtCoin.login.SetPasswordActivity;
import com.block.vtCoin.main.BaseActivity;

import java.util.Map;

/**
 * @Description
 * @Author lijie
 * @Date 2017/7/14.
 */
public class ResetLoginPasViewImpl implements ICommonView<SmsEntity> {
    private final SetPasswordActivity activity;
    private String urlAction="";

    public ResetLoginPasViewImpl(SetPasswordActivity activity , String urlAction) {
        this.activity = activity;
        this.urlAction=urlAction;
    }

    @Override
    public Map<String, Object> getParameters() {
        return activity.getResetPasMap();
    }

    @Override
    public void onError(int code, String msg) {
        activity.showTip(msg);
        activity.setResetPas(false);
    }

    @Override
    public void onCompleted(SmsEntity entity) {
        activity.showTip(activity.getString(R.string.tip_reset_password_success));
        activity.setResetPas(true);
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
    public Class<SmsEntity> getEClass()
    {
        return SmsEntity.class;
    }
}
