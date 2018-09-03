package com.block.vtCoin.deal.fragment.P;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.net.mvp.presenter.IPresenterCallback;
import com.block.vtCoin.deal.fragment.v.DealPriceViewImpl;
import com.block.vtCoin.entity.ChatTokenEntity;
import com.block.vtCoin.entity.DealPriceEntity;
import com.block.vtCoin.entity.MarketEntity;
import com.block.vtCoin.request.ApiAction;
import com.block.vtCoin.request.MyModel;
import com.block.vtCoin.request.ResponseSubscriber;
import com.block.vtCoin.util.JsonUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import rx.Subscription;

/**
 * Created by liubin on 2017/11/8.
 */

public class DealPricePresenter extends BasePresenter<MyModel<DealPriceEntity>, DealPriceViewImpl> {
    private Subscription subscription = null;

    public void getDealPrice() {
        removeSub(subscription);
        Map<String, Object> map = new HashMap<>();
        map.put("symbol", "btc");//目前只做btc
        subscription = model.getPara(ApiAction.Deal_Ticket, map, new ResponseSubscriber<>(view.getEClass(), new IPresenterCallback<DealPriceEntity>() {
            @Override
            public void onSuccess(DealPriceEntity entity) {
            }

            @Override
            public void onSuccess(String json) {
                ArrayList<DealPriceEntity> dealPriceEntities = JsonUtils.jsonToArrayList(json, DealPriceEntity.class);
                view.onSuccess(dealPriceEntities);
            }

            @Override
            public void onError(int code, String msg) {
                view.onError(code, msg);
            }
        }));
        addSub(subscription);
    }
}
