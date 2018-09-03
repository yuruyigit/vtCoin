package com.block.vtCoin.login.v;

import android.content.Context;

import com.block.net.mvp.view.ICommonView;
import com.block.net.util.L;
import com.block.vtCoin.R;
import com.block.vtCoin.entity.LoginEntity;
import com.block.vtCoin.login.LoginActivity;
import com.block.vtCoin.request.ApiAction;

import java.util.Map;

/**
 * @Description
 * @Author lijie
 * @Date 2017/7/13.
 */
public class LoginViewImpl implements ICommonView<LoginEntity> {

    private LoginActivity activity;

    public LoginViewImpl(LoginActivity activity) {
        this.activity = activity;
    }

    @Override
    public void loading() {
        activity.loadDialog();
    }

    @Override
    public void dismiss() {
        activity.dismissDialog();
    }

    @Override
    public Context getContext() {
        return activity;
    }

    @Override
    public Map<String, Object> getParameters() {
        return activity.getLoginMap();
    }


    @Override
    public void onCompleted(LoginEntity entity) {
        activity.showTip(activity.getString(R.string.tip_login_success));
        activity.setLogin(entity);
    }

    @Override
    public void onError(int code, String msg) {
        L.i("onError msg="+msg);
        activity.showTip(msg);
        activity.setLogin(null);
    }

    @Override
    public String getUrlAction() {
        return ApiAction.Account_Login;
    }

    @Override
    public Class<LoginEntity> getEClass() {
        return LoginEntity.class;
    }
}
