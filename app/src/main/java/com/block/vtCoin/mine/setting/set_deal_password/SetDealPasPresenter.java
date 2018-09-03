package com.block.vtCoin.mine.setting.set_deal_password;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.net.mvp.presenter.IPresenterCallback;
import com.block.net.mvp.view.ICommonView;
import com.block.vtCoin.entity.SmsEntity;
import com.block.vtCoin.request.MyModel;
import com.block.vtCoin.request.ResponseSubscriber;

import rx.Subscription;

/**
 * @Description
 * @Author lijie
 * @Date 2017/7/14.
 */
public class SetDealPasPresenter extends BasePresenter<MyModel<SmsEntity>, ICommonView<SmsEntity>> {
    /*请求验证码*/
    public void setDealPas() {
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
