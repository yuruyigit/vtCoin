package com.block.vtCoin.login.p;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.net.mvp.presenter.IPresenterCallback;
import com.block.net.mvp.view.ICommonView;
import com.block.vtCoin.entity.RegisterEntity;
import com.block.vtCoin.request.MyModel;
import com.block.vtCoin.request.ResponseSubscriber;

import rx.Subscription;

/**
 * @Description
 * @Author lijie
 * @Date 2017/7/13.
 */
public class RegisterPresenter extends BasePresenter<MyModel<RegisterEntity>, ICommonView<RegisterEntity>> {

    public void register() {
        view.loading();
        Subscription subscription = model.postPara(view.getUrlAction(), view.getParameters(), new ResponseSubscriber<>(view.getEClass(), new IPresenterCallback<RegisterEntity>() {
            @Override
            public void onSuccess(RegisterEntity entity) {
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

            }
        }));
        addSub(subscription);
    }
}
