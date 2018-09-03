package com.block.vtCoin.mine.user.p;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.net.mvp.presenter.IPresenterCallback;
import com.block.net.mvp.view.ICommonView;
import com.block.vtCoin.entity.LoginHistoryEntity;
import com.block.vtCoin.entity.SmsEntity;
import com.block.vtCoin.request.MyModel;
import com.block.vtCoin.request.ResponseSubscriber;

import rx.Subscription;

/**
 * @Description 验证验证码的公共presenter
 */
public class LoginHistoryPresenter extends BasePresenter<MyModel<LoginHistoryEntity>, ICommonView<LoginHistoryEntity>> {
    /*验证验证码*/
    public void loginHistory() {
        view.loading();
        Subscription subscription = model.postAction(view.getUrlAction(), new ResponseSubscriber<>(view.getEClass(), new IPresenterCallback<LoginHistoryEntity>() {
            @Override
            public void onSuccess(LoginHistoryEntity entity) {
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
