package com.block.vtCoin.deal.fragment.v;

import android.content.Context;

import com.block.net.mvp.view.ICommonView;
import com.block.vtCoin.deal.fragment.BuyFragment1;
import com.block.vtCoin.deal.fragment.SellFragment1;
import com.block.vtCoin.entity.QueryOrder;
import com.block.vtCoin.market.BaseFragment;

import java.util.Map;

/**
 * Created by liubin on 2017/11/8.
 */

public class QueryOrderViewImpl implements ICommonView<QueryOrder> {
    private BaseFragment fragment;
    private int type;

    public QueryOrderViewImpl(BaseFragment fragment,int type) {
        this.fragment = fragment;
        this.type=type;
    }

    @Override
    public void loading() {
        fragment.loadDialog();
    }

    @Override
    public void dismiss() {
        fragment.dismissDialog();
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
    public void onCompleted(QueryOrder entity) {
        if(type==0)
            ((BuyFragment1)fragment).setQueryOrder(entity);
        else if(type==1)
            ((SellFragment1)fragment).setQueryOrder(entity);
    }

    @Override
    public void onError(int code, String msg) {
        if(type==0)
            ((BuyFragment1)fragment).setQueryOrder(null);
        else if(type==1)
            ((SellFragment1)fragment).setQueryOrder(null);
    }

    @Override
    public String getUrlAction() {
        return null;
    }

    @Override
    public Class<QueryOrder> getEClass() {
        return QueryOrder.class;
    }
}
