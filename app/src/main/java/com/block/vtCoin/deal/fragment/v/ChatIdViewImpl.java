package com.block.vtCoin.deal.fragment.v;

import android.content.Context;

import com.block.net.mvp.view.ICommonView;
import com.block.vtCoin.deal.fragment.BuyFragment;
import com.block.vtCoin.deal.fragment.BuyFragment1;
import com.block.vtCoin.deal.fragment.ChatActivity;
import com.block.vtCoin.deal.fragment.SellFragment1;
import com.block.vtCoin.entity.ChatIdEntity;
import com.block.vtCoin.entity.ChatTokenEntity;
import com.block.vtCoin.market.BaseFragment;

import java.util.Map;

/**
 * Created by liubin on 2017/11/8.
 */

public class ChatIdViewImpl implements ICommonView<ChatIdEntity> {
    private ChatActivity activity;

    public ChatIdViewImpl(ChatActivity activity) {
        this.activity = activity;
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
    public void onCompleted(ChatIdEntity entity) {
        activity.setChatId(entity);
    }

    @Override
    public void onError(int code, String msg) {
        activity.setChatId(null);
    }

    @Override
    public String getUrlAction() {
        return null;
    }

    @Override
    public Class<ChatIdEntity> getEClass() {
        return ChatIdEntity.class;
    }
}
