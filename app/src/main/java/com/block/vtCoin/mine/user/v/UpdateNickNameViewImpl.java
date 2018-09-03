package com.block.vtCoin.mine.user.v;

import android.content.Context;

import com.block.net.mvp.view.ICommonView;
import com.block.vtCoin.entity.SmsEntity;
import com.block.vtCoin.mine.user.NickNameActivity;
import com.block.vtCoin.mine.user.UserManagerActivity;

import java.util.Map;

/**
 * 验证验证码的公共类view
 */
public class UpdateNickNameViewImpl implements ICommonView<SmsEntity> {
    private final NickNameActivity activity;
    private String urlAction = "";

    public UpdateNickNameViewImpl(NickNameActivity activity, String urlAction) {
        this.activity = activity;
        this.urlAction = urlAction;
    }

    @Override
    public Map<String, Object> getParameters() {
        return activity.getNikeMap();
    }

    @Override
    public void onError(int code, String msg) {
        activity.showTip(msg);
    }

    @Override
    public void onCompleted(SmsEntity entity) {
        activity.setNickName();
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
