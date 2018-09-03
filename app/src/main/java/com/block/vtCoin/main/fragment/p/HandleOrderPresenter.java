package com.block.vtCoin.main.fragment.p;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.net.mvp.presenter.IPresenterCallback;
import com.block.net.mvp.view.ICommonView;
import com.block.vtCoin.entity.HandleOrderEntity;
import com.block.vtCoin.request.MyModel;
import com.block.vtCoin.request.ResponseSubscriber;

import rx.Subscription;

/**
 * @Description 获取验证码的公共presenter
 */
public class HandleOrderPresenter extends BasePresenter<MyModel<HandleOrderEntity>, ICommonView<HandleOrderEntity>> {
    /*请求验证码*/
    public void handleOrder() {
        Subscription subscription = model.postAction(view.getUrlAction(), new ResponseSubscriber<>(view.getEClass(), new IPresenterCallback<HandleOrderEntity>() {
            @Override
            public void onSuccess(HandleOrderEntity entity) {
                view.onCompleted(entity);
            }

            @Override
            public void onError(int code, String msg) {
                view.onError(code, msg);
            }

            @Override
            public void onSuccess(String json) {

            }

        }));
        addSub(subscription);
    }
}
