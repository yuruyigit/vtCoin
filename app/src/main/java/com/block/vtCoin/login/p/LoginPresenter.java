package com.block.vtCoin.login.p;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.net.mvp.presenter.IPresenterCallback;
import com.block.net.mvp.view.ICommonView;
import com.block.vtCoin.entity.LoginEntity;
import com.block.vtCoin.request.MyModel;
import com.block.vtCoin.request.ResponseSubscriber;
import com.google.gson.Gson;

import rx.Subscription;

public class LoginPresenter extends BasePresenter<MyModel<LoginEntity>, ICommonView<LoginEntity>> {

    public void login() {
        view.loading();
        Subscription subscription = model.postPara(view.getUrlAction(), view.getParameters(), new ResponseSubscriber<>(view.getEClass(), new IPresenterCallback<LoginEntity>() {
            @Override
            public void onSuccess(LoginEntity entity) {
                view.dismiss();
                view.onCompleted(entity);
            }

            @Override
            public void onError(int code, String msg) {
                view.dismiss();
                view.onError(code, msg);
            }

            @Override
            public void onSuccess(String json) {
                view.dismiss();
                view.onCompleted(new Gson().fromJson(json,LoginEntity.class));
            }
        }));
        addSub(subscription);
    }
}
