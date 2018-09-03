package com.block.vtCoin.deal.fragment.P;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.net.mvp.presenter.IPresenterCallback;
import com.block.vtCoin.deal.fragment.v.QueryOrderViewImpl;
import com.block.vtCoin.entity.PageEntity;
import com.block.vtCoin.entity.QueryOrder;
import com.block.vtCoin.request.ApiAction;
import com.block.vtCoin.request.MyModel;
import com.block.vtCoin.request.ResponseSubscriber;

import rx.Subscription;

/**
 * Created by liubin on 2017/11/8.
 */

public class QueryOrderPresenter extends BasePresenter<MyModel<QueryOrder>, QueryOrderViewImpl> {
    private Subscription subscription = null;

    public void queryOrder(PageEntity page, int type) {
        removeSub(subscription);
        String action = "";
        if (type == 0) {//买入订单
            action = ApiAction.Exchange_QuerySellRestingOrder;
        } else if (type == 1) {//卖出订单
            action = ApiAction.Exchange_QueryBuyRestingOrder;
        }
        subscription = model.postBodyPageEntity(action, page, new ResponseSubscriber<>(view.getEClass(), new IPresenterCallback<QueryOrder>() {
            @Override
            public void onSuccess(QueryOrder entity) {
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
