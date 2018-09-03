package com.block.vtCoin.login.v;

import android.content.Context;

import com.block.net.mvp.view.ICommonView;
import com.block.vtCoin.R;
import com.block.vtCoin.entity.LoginEntity;
import com.block.vtCoin.entity.RegisterEntity;
import com.block.vtCoin.login.EmailRegisterActivity;
import com.block.vtCoin.login.LoginActivity;
import com.block.vtCoin.login.PhoneRegisterActivity;
import com.block.vtCoin.login.SetPasswordActivity;
import com.block.vtCoin.main.BaseActivity;
import com.block.vtCoin.request.ApiAction;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author lijie
 * @Date 2017/7/13.
 */
public class RegisterViewImpl implements ICommonView<RegisterEntity> {

    private BaseActivity activity;
    private String apiAction;
    /**
     * 0--手机号注册，1--邮箱注册
     */
    private int type;

    public RegisterViewImpl(BaseActivity activity,String apiAction, int type) {
        this.activity = activity;
        this.apiAction=apiAction;
        this.type = type;
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
        Map<String, Object> map = null;
        if (type == 0) {
            map = ((SetPasswordActivity) activity).getRegisterMap();
        } else if (type == 1) {
            map = ((EmailRegisterActivity) activity).getRegisterMap();
        }
        return map;
    }

    @Override
    public void onCompleted(RegisterEntity entity) {
        activity.showTip(activity.getString(R.string.tip_register_success));
        if (type == 0) {
           ((SetPasswordActivity) activity).setRegister(true);
        } else if (type == 1) {
            ((EmailRegisterActivity) activity).setRegister(true);
        }
    }

    @Override
    public void onError(int code, String msg) {
        activity.showTip(msg);
        if (type == 0) {
            ((SetPasswordActivity) activity).setRegister(true);
        } else if (type == 1) {
            ((EmailRegisterActivity) activity).setRegister(true);
        }
    }

    @Override
    public String getUrlAction() {
        return apiAction;
    }

    @Override
    public Class<RegisterEntity> getEClass() {
        return RegisterEntity.class;
    }
}
