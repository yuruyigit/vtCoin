package com.block.vtCoin.mine.my_attention.p;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.net.mvp.presenter.IPresenterCallback;
import com.block.vtCoin.entity.AttentionEntity;
import com.block.vtCoin.entity.Page;
import com.block.vtCoin.mine.my_attention.v.AttentionViewImpl;
import com.block.vtCoin.request.MyModel;
import com.block.vtCoin.request.ResponseSubscriber;
/**
 * Created by liubin on 2017/11/6.
 */

public class AttentionPresenter extends BasePresenter<MyModel<AttentionEntity>, AttentionViewImpl> {
    public void getAttention(Page page) {
        model.postBodyPage(view.getUrlAction(), page, new ResponseSubscriber<>(view.getEClass(), new IPresenterCallback<AttentionEntity>() {
            @Override
            public void onSuccess(AttentionEntity entity) {
                view.onCompleted(entity);
            }

            @Override
            public void onSuccess(String json) {

            }

            @Override
            public void onError(int code, String msg) {
                view.onError(code,msg);
            }
        }));
    }
}
