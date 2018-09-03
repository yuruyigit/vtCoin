package com.block.vtCoin.deal.fragment.P;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.net.mvp.presenter.IPresenterCallback;
import com.block.net.mvp.view.ICommonView;
import com.block.vtCoin.deal.fragment.v.AppFeeRateViewImpl;
import com.block.vtCoin.deal.fragment.v.OneSellOrderViewImpl;
import com.block.vtCoin.entity.AppFeeRateEntity;
import com.block.vtCoin.entity.OneSellOrderEntity;
import com.block.vtCoin.request.ApiAction;
import com.block.vtCoin.request.MyModel;
import com.block.vtCoin.request.ResponseSubscriber;

import java.util.HashMap;
import java.util.Map;

import rx.Subscription;

/**
 * Created by liubin on 2017/11/8.
 */

public class QueryOneSellOrderPresenter extends BasePresenter<MyModel<OneSellOrderEntity>, ICommonView> {
    private Subscription subscription = null;

    public void oneSellOrder() {
        removeSub(subscription);
        Map<String,Object> map=new HashMap<>();
        map.put("QueryId","01709220T6N08");
        subscription = model.postPara(ApiAction.exchange_QueryOneSellOder ,map, new ResponseSubscriber<>(view.getEClass(), new IPresenterCallback<OneSellOrderEntity>() {
            @Override
            public void onSuccess(OneSellOrderEntity entity) {
                view.onCompleted(entity);
            }

            @Override
            public void onSuccess(String json) {
            }

            @Override
            public void onError(int code, String msg) {
                view.onError(code, msg);
            }
        }));
        addSub(subscription);
    }
}
