package com.block.vtCoin.mine.user.v;

import android.content.Context;

import com.block.net.mvp.view.ICommonView;
import com.block.vtCoin.entity.SmsEntity;
import com.block.vtCoin.mine.user.SignNameActivity;

import java.util.Map;

/**
 * 验证验证码的公共类view
 */
public class UpdateSignViewImpl implements ICommonView<SmsEntity> {
    private final SignNameActivity activity;
    private String urlAction = "";

    public UpdateSignViewImpl(SignNameActivity activity, String urlAction) {
        this.activity = activity;
        this.urlAction = urlAction;
    }

    @Override
    public Map<String, Object> getParameters() {
        return activity.getSignMap();
    }

    @Override
    public void onError(int code, String msg) {
        activity.showTip(msg);
    }

    @Override
    public void onCompleted(SmsEntity entity) {
        activity.setSign();
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
