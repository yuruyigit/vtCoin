package com.block.vtCoin.deal.fragment.P;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.net.mvp.presenter.IPresenterCallback;
import com.block.net.util.L;
import com.block.vtCoin.deal.fragment.v.ChatTokenViewImpl;
import com.block.vtCoin.entity.ChatTokenEntity;
import com.block.vtCoin.request.ApiAction;
import com.block.vtCoin.request.MyModel;
import com.block.vtCoin.request.ResponseSubscriber;

import rx.Subscription;

/**
 * Created by liubin on 2017/11/8.
 */

public class ChatTokenPresenter extends BasePresenter<MyModel<ChatTokenEntity>, ChatTokenViewImpl> {
    private Subscription subscription = null;

    public void chatToken(String[] names) {
        removeSub(subscription);
        L.i("拿Token");
        subscription = model.postArray(ApiAction.Chat_GetChatTokenByNameWithMany, names, new ResponseSubscriber<>(view.getEClass(), new IPresenterCallback<ChatTokenEntity>() {
            @Override
            public void onSuccess(ChatTokenEntity entity) {
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
