package com.block.vtCoin.mine.setting.set_deal_password;

import android.content.Context;

import com.block.net.mvp.view.ICommonView;
import com.block.vtCoin.R;
import com.block.vtCoin.entity.SmsEntity;

import java.util.Map;

/**
 * @Description
 * @Author lijie
 * @Date 2017/7/13.
 */
public class SetDealPasViewImpl implements ICommonView<SmsEntity> {

    private SetDealPasswordActivity activity;
    private String apiAction;

    public SetDealPasViewImpl(SetDealPasswordActivity activity, String apiAction) {
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
        return activity.getSetDealPasMap();
    }

    @Override
    public void onCompleted(SmsEntity entity) {
        activity.showTip(activity.getString(R.string.tip_set_deal_pas_success));
        activity.setDealPas();
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
