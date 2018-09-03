package com.block.vtCoin.mine.setting.setting.v;

import android.content.Context;

import com.block.net.mvp.view.ICommonView;
import com.block.vtCoin.entity.LoginEntity;
import com.block.vtCoin.entity.SmsEntity;
import com.block.vtCoin.mine.setting.setting.MineSettingActivity;

import java.util.Map;

public class LogoutViewImpl implements ICommonView<SmsEntity> {

    private MineSettingActivity activity;
    private String apiAction;

    public LogoutViewImpl(MineSettingActivity activity, String apiAction) {
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
        return null;
    }

    @Override
    public void onCompleted(SmsEntity entity) {
        activity.logout();
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
