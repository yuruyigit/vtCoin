package com.block.vtCoin.mine.tobe_trader.p;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.net.mvp.presenter.IPresenterCallback;
import com.block.net.mvp.view.ICommonView;
import com.block.vtCoin.entity.QueryDealerEntity;
import com.block.vtCoin.request.MyModel;
import com.block.vtCoin.request.ResponseSubscriber;

import rx.Subscription;

/**
 * @Description 获取验证码的公共presenter
 */
public class QueryDealerPresenter extends BasePresenter<MyModel<QueryDealerEntity>, ICommonView<QueryDealerEntity>> {
    /*请求验证码*/
    public void queryDealer() {
        Subscription subscription = model.postAction(view.getUrlAction(), new ResponseSubscriber<>(view.getEClass(), new IPresenterCallback<QueryDealerEntity>() {
            @Override
            public void onSuccess(QueryDealerEntity entity) {
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
