package com.block.vtCoin.mine.buy_coin.p;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.net.mvp.presenter.IPresenterCallback;
import com.block.net.mvp.view.ICommonView;
import com.block.vtCoin.entity.BalanceAddressEntity;
import com.block.vtCoin.entity.WalletEntity;
import com.block.vtCoin.request.MyModel;
import com.block.vtCoin.request.ResponseSubscriber;
import com.google.gson.Gson;

import rx.Subscription;

/**
 * @Description 获取验证码的公共presenter
 */
public class BalanceAddressPresenter extends BasePresenter<MyModel<BalanceAddressEntity>, ICommonView<BalanceAddressEntity>> {
    /*请求验证码*/
    public void getBalanceAddress() {
        Subscription subscription = model.postAction(view.getUrlAction(), new ResponseSubscriber<>(view.getEClass(), new IPresenterCallback<BalanceAddressEntity>() {
            @Override
            public void onSuccess(BalanceAddressEntity entity) {
                view.onCompleted(entity);
            }

            @Override
            public void onError(int code, String msg) {
                view.onError(code, msg);
            }

            @Override
            public void onSuccess(String json) {
                view.onCompleted(new Gson().fromJson(json,BalanceAddressEntity.class));
            }

        }));
        addSub(subscription);
    }
}
