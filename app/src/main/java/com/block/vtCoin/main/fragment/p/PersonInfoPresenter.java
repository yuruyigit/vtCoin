package com.block.vtCoin.main.fragment.p;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.net.mvp.presenter.IPresenterCallback;
import com.block.net.mvp.view.ICommonView;
import com.block.net.util.L;
import com.block.vtCoin.entity.PersonInfoEntity;
import com.block.vtCoin.request.MyModel;
import com.block.vtCoin.request.ResponseSubscriber;

import rx.Subscription;

/**
 * @Description 获取验证码的公共presenter
 */
public class PersonInfoPresenter extends BasePresenter<MyModel<PersonInfoEntity>, ICommonView<PersonInfoEntity>> {
    /*请求验证码*/
    public void getUserInfo() {
        view.loading();
        Subscription subscription = model.postAction(view.getUrlAction(), new ResponseSubscriber<>(view.getEClass(), new IPresenterCallback<PersonInfoEntity>() {
            @Override
            public void onSuccess(PersonInfoEntity entity) {
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
