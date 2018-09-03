package com.block.vtCoin.mine.user.p;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.net.mvp.presenter.IPresenterCallback;
import com.block.net.mvp.view.ICommonView;
import com.block.vtCoin.entity.SmsEntity;
import com.block.vtCoin.request.MyModel;
import com.block.vtCoin.request.ResponseSubscriber;

import java.util.Map;

import rx.Subscription;

/**
 * @Description 验证验证码的公共presenter
 */
public class UpdateNickNamePresenter extends BasePresenter<MyModel<SmsEntity>, ICommonView<SmsEntity>> {
    /*验证验证码*/
    public void updateNikeName() {
        view.loading();
        Subscription subscription = model.postPara(view.getUrlAction(), view.getParameters(), new ResponseSubscriber<>(view.getEClass(), new IPresenterCallback<SmsEntity>() {
            @Override
            public void onSuccess(SmsEntity entity) {
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
