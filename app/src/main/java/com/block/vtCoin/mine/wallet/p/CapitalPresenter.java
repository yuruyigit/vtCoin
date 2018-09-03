package com.block.vtCoin.mine.wallet.p;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.net.mvp.presenter.IPresenterCallback;
import com.block.net.mvp.view.ICommonView;
import com.block.vtCoin.entity.QueryDealerEntity;
import com.block.vtCoin.entity.WalletEntity;
import com.block.vtCoin.request.MyModel;
import com.block.vtCoin.request.ResponseSubscriber;
import com.google.gson.Gson;

import rx.Subscription;

/**
 * @Description 获取验证码的公共presenter
 */
public class CapitalPresenter extends BasePresenter<MyModel<WalletEntity>, ICommonView<WalletEntity>> {
    /*请求验证码*/
    public void getCapital() {
        Subscription subscription = model.postAction(view.getUrlAction(), new ResponseSubscriber<>(view.getEClass(), new IPresenterCallback<WalletEntity>() {
            @Override
            public void onSuccess(WalletEntity entity) {
                view.onCompleted(entity);
            }

            @Override
            public void onError(int code, String msg) {
                view.onError(code, msg);
            }
            @Override
            public void onSuccess(String json) {
                WalletEntity walletEntity = new Gson().fromJson(json, WalletEntity.class);
                view.onCompleted(walletEntity);
            }

        }));
        addSub(subscription);
    }
}
