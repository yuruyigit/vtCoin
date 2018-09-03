package com.block.vtCoin.deal.fragment.P;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.net.mvp.presenter.IPresenterCallback;
import com.block.vtCoin.deal.fragment.v.MarketPriceViewImpl;
import com.block.vtCoin.entity.MarketPriceEntity;
import com.block.vtCoin.market.v.IListView;
import com.block.vtCoin.request.ApiAction;
import com.block.vtCoin.request.MyModel;
import com.block.vtCoin.request.ResponseSubscriber;
import com.block.vtCoin.util.JsonUtils;
import com.google.gson.Gson;

import java.util.ArrayList;

import rx.Subscription;

/**
 * Created by liubin on 2017/11/8.
 */

public class MarketPricePresenter extends BasePresenter<MyModel<MarketPriceEntity>, IListView> {
    private Subscription subscription = null;

    public void marketPrice() {
        removeSub(subscription);
        subscription = model.getAction(ApiAction.Market , new ResponseSubscriber<>(view.getEClass(), new IPresenterCallback<MarketPriceEntity>() {
            @Override
            public void onSuccess(MarketPriceEntity entity) {

            }

            @Override
            public void onSuccess(String json) {
                ArrayList<MarketPriceEntity> marketPriceEntities = JsonUtils.jsonToArrayList(json, MarketPriceEntity.class);
                view.onSuccess(marketPriceEntities);

            }
            @Override
            public void onError(int code, String msg) {

            }
        }));
        addSub(subscription);
    }
}
