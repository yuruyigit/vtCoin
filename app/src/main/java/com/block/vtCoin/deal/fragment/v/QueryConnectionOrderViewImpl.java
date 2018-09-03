package com.block.vtCoin.deal.fragment.v;

import android.content.Context;

import com.block.net.mvp.view.ICommonView;
import com.block.vtCoin.deal.fragment.DealDetailActivity1;
import com.block.vtCoin.entity.QueryConnectionOrder;

import java.util.Map;

/**
 * Created by liubin on 2017/11/8.
 */

public class QueryConnectionOrderViewImpl implements ICommonView<QueryConnectionOrder> {
    private DealDetailActivity1 activity;

    public QueryConnectionOrderViewImpl(DealDetailActivity1 activity) {
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
    public void onCompleted(QueryConnectionOrder entity) {

    }

    @Override
    public void onError(int code, String msg) {

    }

    @Override
    public String getUrlAction() {
        return null;
    }

    @Override
    public Class<QueryConnectionOrder> getEClass() {
        return QueryConnectionOrder.class;
    }
}
