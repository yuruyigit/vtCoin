package com.block.vtCoin.market.p;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.net.mvp.presenter.IPresenterCallback;
import com.block.vtCoin.entity.MarketEntity;
import com.block.vtCoin.market.v.IListView;
import com.block.vtCoin.request.ApiAction;
import com.block.vtCoin.request.MyModel;
import com.block.vtCoin.request.ResponseSubscriber;
import com.block.vtCoin.util.JsonUtils;

import java.util.ArrayList;

import rx.Subscription;

/**
 * @Description
 * @Author lijie
 * @Date 2017/7/13.
 */
public class ConvertPresenter extends BasePresenter<MyModel<MarketEntity>, IListView<MarketEntity>> {
    private Subscription subscription = null;

    public void requestVtc() {
        cancel();
        subscription = model.getPara(ApiAction.Markets, view.getParameters(), new ResponseSubscriber<>(view.getEClass(), new IPresenterCallback<MarketEntity>() {
            @Override
            public void onSuccess(MarketEntity entity) {
            }

            @Override
            public void onSuccess(String json) {
                ArrayList<MarketEntity> marketEntities = JsonUtils.jsonToArrayList(json, MarketEntity.class);
                view.onSuccess(marketEntities);
            }

            @Override
            public void onError(int code, String msg) {
                view.onError(code, msg);
            }
        }));
        addSub(subscription);
    }

    public void cancel() {
        removeSub(subscription);
    }
}
