package com.block.vtCoin.mine.tobe_trader.v;

import android.content.Context;

import com.block.net.mvp.view.ICommonView;
import com.block.vtCoin.entity.HandleOrderEntity;
import com.block.vtCoin.entity.QueryDealerEntity;
import com.block.vtCoin.main.fragment.MineFragment;
import com.block.vtCoin.mine.tobe_trader.TobeTraderActivity;
import com.block.vtCoin.mine.tobe_trader.TobeTraderActivity1;

import java.util.Map;

/**
 * @Description 获取验证码的公共view
 */
public class QueryDealerViewImpl implements ICommonView<QueryDealerEntity> {
    private final TobeTraderActivity1 activity;
    private String urlAction = "";

    public QueryDealerViewImpl(TobeTraderActivity1 activity, String urlAction) {
        this.activity = activity;
        this.urlAction = urlAction;
    }

    @Override
    public Map<String, Object> getParameters() {
        return null;
    }

    @Override
    public void onError(int code, String msg) {
        activity.setQueryDealer(null);
    }

    @Override
    public void onCompleted(QueryDealerEntity entity) {
        activity.setQueryDealer(entity);
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
    public Class<QueryDealerEntity> getEClass() {
        return QueryDealerEntity.class;
    }
}
