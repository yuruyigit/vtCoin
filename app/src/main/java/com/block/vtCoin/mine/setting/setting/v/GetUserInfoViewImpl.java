package com.block.vtCoin.mine.setting.setting.v;

import android.content.Context;
import com.block.net.mvp.view.ICommonView;
import com.block.vtCoin.entity.UserInfoEntity;
import com.block.vtCoin.mine.setting.setting.MineSettingActivity;
import java.util.Map;

/**
 * @Description
 * @Author lijie
 * @Date 2017/7/13.
 */
public class GetUserInfoViewImpl implements ICommonView<UserInfoEntity> {

    private MineSettingActivity activity;
    private String apiAction;

    public GetUserInfoViewImpl(MineSettingActivity activity, String apiAction) {
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
    public void onCompleted(UserInfoEntity entity) {
        activity.setUserInfo(entity);
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
    public Class<UserInfoEntity> getEClass() {
        return UserInfoEntity.class;
    }
}
