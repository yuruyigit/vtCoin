package com.block.vtCoin.mine.wallet.v;

import android.content.Context;

import com.block.net.mvp.view.ICommonView;
import com.block.vtCoin.entity.WalletEntity;
import com.block.vtCoin.main.BaseActivity;
import com.block.vtCoin.mine.buy_coin.TurnInActivity;
import com.block.vtCoin.mine.wallet.WalletActivity;

import java.util.Map;

/**
 * @Description 获取验证码的公共view
 */
public class CapitalViewImpl implements ICommonView<WalletEntity> {
    private final WalletActivity activity;
    private String urlAction = "";
    private int type;

    public CapitalViewImpl(WalletActivity activity, String urlAction) {
        this.activity = activity;
        this.urlAction = urlAction;
        this.type=type;
    }

    @Override
    public Map<String, Object> getParameters() {
        return null;
    }

    @Override
    public void onError(int code, String msg) {
           activity.setCapital(null);
    }

    @Override
    public void onCompleted(WalletEntity entity) {
        activity.setCapital(entity);
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
    public Class<WalletEntity> getEClass() {
        return WalletEntity.class;
    }
}
