package com.block.vtCoin.login.v;

import android.content.Context;

import com.block.net.mvp.view.ICommonView;
import com.block.net.util.L;
import com.block.vtCoin.R;
import com.block.vtCoin.entity.LoginEntity;
import com.block.vtCoin.entity.TokenEntity;
import com.block.vtCoin.login.LoginActivity;
import com.block.vtCoin.request.ApiAction;

import java.util.Map;

/**
 * @Description
 * @Author lijie
 * @Date 2017/7/13.
 */
public class TokenViewImpl implements ICommonView<TokenEntity> {

    private LoginActivity activity;

    public TokenViewImpl(LoginActivity activity) {
        this.activity = activity;
    }

    @Override
    public void loading() {
    }

    @Override
    public void dismiss() {
    }

    @Override
    public Context getContext() {
        return activity;
    }

    @Override
    public Map<String, Object> getParameters() {
        return null;
    }


    @Override
    public void onCompleted(TokenEntity entity) {
        activity.setToken(entity);
    }

    @Override
    public void onError(int code, String msg) {
        activity.setToken(null);
    }

    @Override
    public String getUrlAction() {
        return null;
    }

    @Override
    public Class<TokenEntity> getEClass() {
        return TokenEntity.class;
    }
}
