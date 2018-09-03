package com.block.vtCoin.deal.fragment.v;

import android.content.Context;

import com.block.net.mvp.view.ICommonView;
import com.block.net.util.L;
import com.block.vtCoin.deal.fragment.BuyFragment1;
import com.block.vtCoin.deal.fragment.SellFragment1;
import com.block.vtCoin.entity.ChatTokenEntity;
import com.block.vtCoin.market.BaseFragment;

import java.util.Map;

/**
 * Created by liubin on 2017/11/8.
 */

public class ChatTokenViewImpl implements ICommonView<ChatTokenEntity> {
    private BaseFragment fragment;
    private int type;

    public ChatTokenViewImpl(BaseFragment fragment,int type) {
        this.fragment = fragment;
        this.type=type;
    }

    @Override
    public void loading() {
        fragment.loadDialog();
    }

    @Override
    public void dismiss() {
        fragment.dismissDialog();
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
    public void onCompleted(ChatTokenEntity entity) {
        if(type==0)
            ((BuyFragment1)fragment).setChatToken(entity);
        else if(type==1)
            ((SellFragment1)fragment).setChatToken(entity);
    }

    @Override
    public void onError(int code, String msg) {
        if(type==0)
            ((BuyFragment1)fragment).setChatToken(null);
        else if(type==1)
            ((SellFragment1)fragment).setChatToken(null);
    }

    @Override
    public String getUrlAction() {
        return null;
    }

    @Override
    public Class<ChatTokenEntity> getEClass() {
        return ChatTokenEntity.class;
    }
}
