package com.block.vtCoin.mine.user.p;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.net.mvp.presenter.IPresenterCallback;
import com.block.net.mvp.view.ICommonView;
import com.block.vtCoin.entity.LoginHistoryEntity;
import com.block.vtCoin.entity.SmsEntity;
import com.block.vtCoin.request.MyModel;
import com.block.vtCoin.request.ResponseSubscriber;

import java.util.HashMap;
import java.util.Map;

import rx.Subscription;

/**
 * @Description 验证验证码的公共presenter
 */
public class LanguagePresenter extends BasePresenter<MyModel<SmsEntity>, ICommonView<SmsEntity>> {
    /*验证验证码*/
    public void changeLanguage(String chooseLanguage) {
        view.loading();
        Map<String,Object> map=new HashMap<>();
        map.put("Lang",chooseLanguage);
        Subscription subscription = model.postPara(view.getUrlAction(), map,new ResponseSubscriber<>(view.getEClass(), new IPresenterCallback<SmsEntity>() {
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
