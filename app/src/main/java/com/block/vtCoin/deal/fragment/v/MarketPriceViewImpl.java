package com.block.vtCoin.deal.fragment.v;

import android.content.Context;

import com.block.vtCoin.deal.fragment.DealDetailActivity1;
import com.block.vtCoin.entity.MarketPriceEntity;
import com.block.vtCoin.market.v.IListView;

import java.util.List;
import java.util.Map;

/**
 * Created by liubin on 2017/11/8.
 */

public class MarketPriceViewImpl implements IListView<MarketPriceEntity> {
    private DealDetailActivity1 activity;

    public MarketPriceViewImpl(DealDetailActivity1 activity) {
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
    public void onError(int code, String msg) {

    }

    @Override
    public Class<MarketPriceEntity> getEClass() {
        return MarketPriceEntity.class;
    }

    @Override
    public void onSuccess(List<MarketPriceEntity> list) {

    }
}
