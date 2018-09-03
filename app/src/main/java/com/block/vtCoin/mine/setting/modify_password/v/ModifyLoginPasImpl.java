package com.block.vtCoin.mine.setting.modify_password.v;

import android.content.Context;

import com.block.net.mvp.view.ICommonView;
import com.block.vtCoin.R;
import com.block.vtCoin.entity.SmsEntity;
import com.block.vtCoin.mine.setting.modify_password.ModifyPasswordActivity;

import java.util.Map;

/**
 * @Description
 * @Author lijie
 * @Date 2017/7/13.
 */
public class ModifyLoginPasImpl implements ICommonView<SmsEntity> {

    private ModifyPasswordActivity activity;
    private String apiAction;

    public ModifyLoginPasImpl(ModifyPasswordActivity activity, String apiAction) {
        this.activity = activity;
        this.apiAction=apiAction;
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
        return activity.getModifyLoginPasMap();
    }

    @Override
    public void onCompleted(SmsEntity entity) {
        activity.showTip(activity.getString(R.string.tip_modify_login_pas_success));
        activity.setModifyLoginPas(entity);
    }

    @Override
    public void onError(int code, String msg) {
        activity.showTip(msg);
    }

    @Override
    public String getUrlAction() {
        return apiAction;
    }

    @Override
    public Class<SmsEntity> getEClass() {
        return SmsEntity.class;
    }
}
