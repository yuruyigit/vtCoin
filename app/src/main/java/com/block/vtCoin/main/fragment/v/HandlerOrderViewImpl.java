package com.block.vtCoin.main.fragment.v;

import android.content.Context;

import com.block.net.mvp.view.ICommonView;
import com.block.vtCoin.entity.HandleOrderEntity;
import com.block.vtCoin.entity.SmsEntity;
import com.block.vtCoin.main.fragment.MineFragment;

import java.util.Map;

/**
 * @Description 获取验证码的公共view
 */
public class HandlerOrderViewImpl implements ICommonView<HandleOrderEntity> {
    private final MineFragment fragment;
    private String urlAction = "";

    public HandlerOrderViewImpl(MineFragment fragment, String urlAction) {
        this.fragment = fragment;
        this.urlAction = urlAction;
    }

    @Override
    public Map<String, Object> getParameters() {
        return null;
    }

    @Override
    public void onError(int code, String msg) {
        fragment.setHandleOrder(null);
    }

    @Override
    public void onCompleted(HandleOrderEntity entity) {
        fragment.setHandleOrder(entity);
    }

    @Override
    public void loading() {
    }

    @Override
    public void dismiss() {
    }

    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public String getUrlAction() {
        return urlAction;
    }

    @Override
    public Class<HandleOrderEntity> getEClass() {
        return HandleOrderEntity.class;
    }
}
