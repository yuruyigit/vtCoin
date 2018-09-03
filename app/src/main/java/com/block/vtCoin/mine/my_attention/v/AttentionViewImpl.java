package com.block.vtCoin.mine.my_attention.v;

import android.content.Context;

import com.block.net.mvp.view.ICommonView;
import com.block.vtCoin.entity.AttentionEntity;
import com.block.vtCoin.mine.my_attention.MyAttentionActivity;

import java.util.Map;

/**
 * Created by liubin on 2017/11/6.
 */

public class AttentionViewImpl implements ICommonView<AttentionEntity> {
    private MyAttentionActivity activity;
    private String action;

    public AttentionViewImpl(MyAttentionActivity activity, String action) {
        this.activity = activity;
        this.action = action;
    }

    @Override
    public void loading() {
    }

    @Override
    public void dismiss() {
    }

    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public Map<String, Object> getParameters() {
        return null;
    }

    @Override
    public void onCompleted(AttentionEntity entity) {
        activity.setAttention(entity);
    }

    @Override
    public void onError(int code, String msg) {
        activity.showTip(msg);
    }

    @Override
    public String getUrlAction() {
        return action;
    }

    @Override
    public Class<AttentionEntity> getEClass() {
        return AttentionEntity.class;
    }

}
