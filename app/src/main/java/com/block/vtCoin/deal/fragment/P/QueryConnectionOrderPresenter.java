package com.block.vtCoin.deal.fragment.P;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.net.mvp.presenter.IPresenterCallback;
import com.block.vtCoin.deal.fragment.v.QueryConnectionOrderViewImpl;
import com.block.vtCoin.entity.QueryConnectionOrder;
import com.block.vtCoin.request.ApiAction;
import com.block.vtCoin.request.MyModel;
import com.block.vtCoin.request.ResponseSubscriber;

import java.util.HashMap;
import java.util.Map;

import rx.Subscription;

/**
 * Created by liubin on 2017/11/8.
 */

public class QueryConnectionOrderPresenter extends BasePresenter<MyModel<QueryConnectionOrder>, QueryConnectionOrderViewImpl> {
    private Subscription subscription = null;

    public void queryConnectionOrder() {
        removeSub(subscription);
        Map<String, Object> map = new HashMap<>();
        map.put("QueryId", "017080958RRDN");
        map.put("PageIndex", 1);
        map.put("ListNumber", 10);
        subscription=model.postPara(ApiAction.Exchange_QueryConnectionSellOder , map , new ResponseSubscriber<>(view.getEClass(), new IPresenterCallback<QueryConnectionOrder>() {
            @Override
            public void onSuccess(QueryConnectionOrder entity) {
                view.onCompleted(entity);
            }

            @Override
            public void onSuccess(String json) {
            }
            @Override
            public void onError(int code, String msg) {
                view.onError(code,msg);
            }
        }));
        addSub(subscription);
    }
}
