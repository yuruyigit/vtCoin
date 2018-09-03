package com.block.vtCoin.mine.buy_coin.v;

import android.content.Context;

import com.block.net.mvp.view.ICommonView;
import com.block.vtCoin.entity.BalanceAddressEntity;
import com.block.vtCoin.entity.WalletEntity;
import com.block.vtCoin.main.BaseActivity;
import com.block.vtCoin.mine.buy_coin.TurnInActivity;
import com.block.vtCoin.mine.buy_coin.p.BalanceAddressPresenter;
import com.block.vtCoin.mine.wallet.WalletActivity;

import java.util.Map;

/**
 * @Description 获取验证码的公共view
 */
public class BalanceAddressViewImpl implements ICommonView<BalanceAddressEntity> {
    private final TurnInActivity activity;
    private String urlAction = "";

    public BalanceAddressViewImpl(TurnInActivity activity, String urlAction) {
        this.activity = activity;
        this.urlAction = urlAction;
    }

    @Override
    public Map<String, Object> getParameters() {
        return null;
    }

    @Override
    public void onError(int code, String msg) {
        activity.setBalanceAddress(null);
    }

    @Override
    public void onCompleted(BalanceAddressEntity entity) {
        activity.setBalanceAddress(entity);
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
    public Class<BalanceAddressEntity> getEClass() {
        return BalanceAddressEntity.class;
    }
}
