package com.block.vtCoin.deal.fragment.P;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.net.mvp.presenter.IPresenterCallback;
import com.block.net.mvp.view.ICommonView;
import com.block.vtCoin.entity.AppFeeRateEntity;
import com.block.vtCoin.entity.TraderAdEntity;
import com.block.vtCoin.request.ApiAction;
import com.block.vtCoin.request.MyModel;
import com.block.vtCoin.request.ResponseSubscriber;

import rx.Subscription;

/**
 * Created by liubin on 2017/11/8.
 */

public class TraderAdPresenter extends BasePresenter<MyModel<TraderAdEntity>, ICommonView> {
    private Subscription subscription = null;

    public void traderAd(String[] ids) {
        subscription = model.postArray(ApiAction.Preference_ListManyProvi ,ids, new ResponseSubscriber<>(view.getEClass(), new IPresenterCallback<TraderAdEntity>() {
            @Override
            public void onSuccess(TraderAdEntity entity) {
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
