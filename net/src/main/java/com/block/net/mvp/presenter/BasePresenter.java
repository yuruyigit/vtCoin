package com.block.net.mvp.presenter;
import com.block.net.mvp.model.IBaseModel;
import com.block.net.mvp.view.IBaseView;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * @Description
 * @Author lijie
 * @Date 2017/6/12.
 */
public abstract  class BasePresenter<M extends IBaseModel,V extends IBaseView> {
    protected M model;
    protected V view;
    private CompositeSubscription sub = new CompositeSubscription();
    public void setModel(M m){
        model = m;
    }
    public void setView(V v){
        view = v;
    }
    public void setModelView(M m,V v){
        model = m;
        view = v;
    }

    protected void addSub(Subscription subscription)
    {
        if (sub != null)
            sub.add(subscription);
    }

    /**
     * 取消注册RxJava 防止内存溢出
     */
    public void unRegisterRx(){
       unRegisterRx(false);
    }

    public void unRegisterRx(boolean needView){
        if (sub != null && sub.hasSubscriptions()) {
            sub.unsubscribe();
            if(!needView){
                view = null;
            }
        }
    }

    public V getView()
    {
        return view;
    }


    /**
     * 取消下载时使用，修改者：朱飞龙,请求在进行时停止
     * @param subscription
     */
    public void removeSub(Subscription subscription) {
        if (sub != null&&subscription!=null)
            sub.remove(subscription);
    }
}
