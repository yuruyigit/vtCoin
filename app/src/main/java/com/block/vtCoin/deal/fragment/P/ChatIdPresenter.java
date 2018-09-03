package com.block.vtCoin.deal.fragment.P;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.net.mvp.presenter.IPresenterCallback;
import com.block.net.mvp.view.ICommonView;
import com.block.vtCoin.deal.fragment.v.ChatTokenViewImpl;
import com.block.vtCoin.entity.ChatIdEntity;
import com.block.vtCoin.entity.ChatTokenEntity;
import com.block.vtCoin.request.ApiAction;
import com.block.vtCoin.request.MyModel;
import com.block.vtCoin.request.ResponseSubscriber;

import java.util.HashMap;
import java.util.Map;

import rx.Subscription;

/**
 * Created by liubin on 2017/11/8.
 */

public class ChatIdPresenter extends BasePresenter<MyModel<ChatIdEntity>, ICommonView> {
    private Subscription subscription = null;

    public void chatId(String name) {
        removeSub(subscription);
        Map<String,Object> map=new HashMap<>();
        map.put("Name",name);
        subscription = model.postPara(ApiAction.Chat_GetChatTokenByName, map, new ResponseSubscriber<>(view.getEClass(), new IPresenterCallback<ChatIdEntity>() {
            @Override
            public void onSuccess(ChatIdEntity entity) {
                view.onCompleted(entity);
            }

            @Override
            public void onSuccess(String json) {
            }

            @Override
            public void onError(int code, String msg) {
                view.onError(code, msg);
            }
        }));
        addSub(subscription);
    }
}
