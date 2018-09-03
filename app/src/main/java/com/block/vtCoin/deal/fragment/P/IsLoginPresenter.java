package com.block.vtCoin.deal.fragment.P;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.net.mvp.presenter.IPresenterCallback;
import com.block.net.mvp.view.ICommonView;
import com.block.vtCoin.deal.fragment.v.AppFeeRateViewImpl;
import com.block.vtCoin.deal.fragment.v.IsLoginViewImpl;
import com.block.vtCoin.entity.AppFeeRateEntity;
import com.block.vtCoin.entity.IsLoginEntity;
import com.block.vtCoin.request.ApiAction;
import com.block.vtCoin.request.MyModel;
import com.block.vtCoin.request.ResponseSubscriber;

import rx.Subscription;

/**
 * Created by liubin on 2017/11/8.
 */

public class IsLoginPresenter extends BasePresenter<MyModel<IsLoginEntity>, ICommonView> {
    private Subscription subscription = null;

    public void isLogin() {
        removeSub(subscription);
        subscription = model.postAction(ApiAction.Account_Islogin , new ResponseSubscriber<>(view.getEClass(), new IPresenterCallback<IsLoginEntity>() {
            @Override
            public void onSuccess(IsLoginEntity entity) {
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
