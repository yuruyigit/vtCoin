package com.block.vtCoin.deal.fragment.v;

import android.content.Context;

import com.block.net.mvp.view.ICommonView;
import com.block.vtCoin.deal.fragment.BuyFragment1;
import com.block.vtCoin.deal.fragment.DealDetailActivity1;
import com.block.vtCoin.deal.fragment.SellFragment1;
import com.block.vtCoin.entity.AppFeeRateEntity;
import com.block.vtCoin.entity.ChatTokenEntity;
import com.block.vtCoin.market.BaseFragment;

import java.util.Map;

/**
 * Created by liubin on 2017/11/8.
 */

public class AppFeeRateViewImpl implements ICommonView<AppFeeRateEntity> {
    private DealDetailActivity1 activity;

    public AppFeeRateViewImpl(DealDetailActivity1 activity) {
        this.activity = activity;
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
        return null;
    }

    @Override
    public Map<String, Object> getParameters() {
        return null;
    }

    @Override
    public void onCompleted(AppFeeRateEntity entity) {
        activity.setAppFeeRate(entity);
    }

    @Override
    public void onError(int code, String msg) {
        activity.setAppFeeRate(null);
    }

    @Override
    public String getUrlAction() {
        return null;
    }

    @Override
    public Class<AppFeeRateEntity> getEClass() {
        return AppFeeRateEntity.class;
    }
}
