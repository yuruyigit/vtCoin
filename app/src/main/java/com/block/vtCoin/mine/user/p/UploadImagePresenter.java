package com.block.vtCoin.mine.user.p;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.net.mvp.presenter.IPresenterCallback;
import com.block.net.mvp.view.ICommonView;
import com.block.net.util.L;
import com.block.vtCoin.entity.SmsEntity;
import com.block.vtCoin.request.MyModel;
import com.block.vtCoin.request.ResponseSubscriber;

import java.util.Map;

import rx.Subscription;

/**
 * @Description 验证验证码的公共presenter
 */
public class UploadImagePresenter extends BasePresenter<MyModel<SmsEntity>, ICommonView<SmsEntity>> {
    /*验证验证码*/
    public void uploadImage(Map<String, String> parameters, String[] imagePaths) {
        view.loading();
        Subscription subscription = model.postUpload(view.getUrlAction(), parameters, imagePaths, new ResponseSubscriber<>(view.getEClass(), new IPresenterCallback<SmsEntity>() {
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
