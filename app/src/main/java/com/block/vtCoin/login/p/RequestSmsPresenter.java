package com.block.vtCoin.login.p;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.net.mvp.presenter.IPresenterCallback;
import com.block.net.mvp.view.ICommonView;
import com.block.net.util.L;
import com.block.vtCoin.entity.SmsEntity;
import com.block.vtCoin.request.MyModel;
import com.block.vtCoin.request.ResponseSubscriber;

import rx.Subscription;

/**
 * @Description 获取验证码的公共presenter
 */
public class RequestSmsPresenter extends BasePresenter<MyModel<SmsEntity>, ICommonView<SmsEntity>> {
    /*请求验证码*/
    public void requestCode() {
        view.loading();
        Subscription subscription = model.postPara(view.getUrlAction(), view.getParameters(), new ResponseSubscriber<>(view.getEClass(), new IPresenterCallback<SmsEntity>() {
            @Override
            public void onSuccess(SmsEntity entity) {
                view.dismiss();
                view.onCompleted(entity);
            }

            @Override
            public void onError(int code, String msg) {
                L.i("发送验证码失败");
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
