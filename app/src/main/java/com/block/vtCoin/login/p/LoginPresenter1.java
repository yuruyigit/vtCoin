package com.block.vtCoin.login.p;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.net.mvp.presenter.IPresenterCallback;
import com.block.net.mvp.view.ICommonView;
import com.block.vtCoin.entity.MarketEntity;
import com.block.vtCoin.entity.SmsEntity;
import com.block.vtCoin.entity1.HttpResult;
import com.block.vtCoin.entity1.LoginEntity;
import com.block.vtCoin.net.BaseSubscriber;
import com.block.vtCoin.net.ServiceFactory;
import com.block.vtCoin.net.api.AccountApi;
import com.block.vtCoin.request.MyModel;
import com.block.vtCoin.request.ResponseSubscriber;
import com.google.gson.Gson;

import rx.Observable;
import rx.Subscription;

/**
 * @Description
 * @Author lijie
 * @Date 2017/7/13.
 */
public class LoginPresenter1 {

    public void login(String phone, String password) {
        Subscription subscribe = ServiceFactory.getInstance()
                .createBaseService(AccountApi.class)
                .login(phone, password)
                .subscribe(new BaseSubscriber<LoginEntity>() {
                    @Override
                    public void onSuccess(LoginEntity loginEntity) {

                    }

                    @Override
                    public void onFail(String msg) {

                    }
                });
    }
}
