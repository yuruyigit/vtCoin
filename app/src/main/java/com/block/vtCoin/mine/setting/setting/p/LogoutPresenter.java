package com.block.vtCoin.mine.setting.setting.p;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.net.mvp.presenter.IPresenterCallback;
import com.block.net.mvp.view.ICommonView;
import com.block.vtCoin.entity.LoginEntity;
import com.block.vtCoin.entity.SmsEntity;
import com.block.vtCoin.request.MyModel;
import com.block.vtCoin.request.ResponseSubscriber;

import rx.Subscription;

public class LogoutPresenter extends BasePresenter<MyModel<SmsEntity>, ICommonView<SmsEntity>> {
    /*请求验证码*/
    public void logout() {
        view.loading();
        Subscription subscription=model.postAction(view.getUrlAction(),new ResponseSubscriber<>(view.getEClass(), new IPresenterCallback<SmsEntity>(){
                    @Override
                    public void onSuccess(SmsEntity entity) {
                    }
                    @Override
                    public void onError(int code, String msg) {
                        view.dismiss();
                        view.onError(code, msg);
                    }
                    @Override
                    public void onSuccess(String json) {
                        view.dismiss();
                        view.onCompleted(null);
                    }
                }));
        addSub(subscription);
    }
}
