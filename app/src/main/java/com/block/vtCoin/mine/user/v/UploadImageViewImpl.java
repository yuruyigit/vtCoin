package com.block.vtCoin.mine.user.v;

import android.content.Context;

import com.block.net.mvp.view.ICommonView;
import com.block.vtCoin.R;
import com.block.vtCoin.entity.SmsEntity;
import com.block.vtCoin.login.FindPasswordActivity;
import com.block.vtCoin.login.PhoneRegisterActivity;
import com.block.vtCoin.main.BaseActivity;
import com.block.vtCoin.mine.setting.modify_password.PhoneFindBackPasswordActivity;
import com.block.vtCoin.mine.user.NickNameActivity;
import com.block.vtCoin.mine.user.UserManagerActivity;

import java.util.Map;

/**
 * 验证验证码的公共类view
 */
public class UploadImageViewImpl implements ICommonView<SmsEntity> {

    private final UserManagerActivity activity;
    private String urlAction="";
    public UploadImageViewImpl(UserManagerActivity activity , String urlAction) {
        this.activity = activity;
        this.urlAction=urlAction;
    }

    @Override
    public Map<String, Object> getParameters() {
        return null;
    }

    @Override
    public void onError(int code, String msg) {
        activity.showTip(msg);
    }

    @Override
    public void onCompleted(SmsEntity entity) {
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
