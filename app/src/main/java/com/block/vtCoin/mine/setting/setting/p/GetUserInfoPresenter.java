package com.block.vtCoin.mine.setting.setting.p;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.net.mvp.presenter.IPresenterCallback;
import com.block.net.mvp.view.ICommonView;
import com.block.vtCoin.entity.UserInfoEntity;
import com.block.vtCoin.request.MyModel;
import com.block.vtCoin.request.ResponseSubscriber;
import rx.Subscription;

/**
 * @Description
 * @Author lijie
 * @Date 2017/7/14.
 */
public class GetUserInfoPresenter extends BasePresenter<MyModel<UserInfoEntity>, ICommonView<UserInfoEntity>> {
    /*请求验证码*/
    public void getUserInfo() {
        Subscription subscription=model.postAction(view.getUrlAction(),new ResponseSubscriber<>(view.getEClass(), new IPresenterCallback<UserInfoEntity>(){
                    @Override
                    public void onSuccess(UserInfoEntity entity) {
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
