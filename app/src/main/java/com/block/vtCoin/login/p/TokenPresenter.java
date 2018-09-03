package com.block.vtCoin.login.p;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.net.mvp.presenter.IPresenterCallback;
import com.block.net.mvp.view.ICommonView;
import com.block.net.util.L;
import com.block.vtCoin.entity.LoginEntity;
import com.block.vtCoin.entity.TokenEntity;
import com.block.vtCoin.request.ApiAction;
import com.block.vtCoin.request.MyModel;
import com.block.vtCoin.request.ResponseSubscriber;
import com.google.gson.Gson;

import rx.Subscription;

public class TokenPresenter extends BasePresenter<MyModel<TokenEntity>, ICommonView<TokenEntity>> {

    public void getToken() {
        Subscription subscription = model.postAction(ApiAction.Chat_Token, new ResponseSubscriber<>(view.getEClass(), new IPresenterCallback<TokenEntity>() {
            @Override
            public void onSuccess(TokenEntity entity) {
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
