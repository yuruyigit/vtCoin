package com.block.net.mvp.view;

import android.content.Context;

/**
 * @Description
 * @Author lijie
 * @Date 2017/6/20.
 */
public interface ILoadingView  extends IBaseView{
    /**
     * 正在请求中显示加载框
     */
    void loading();

    /**
     * 请求完成关闭加载框
     */
    void dismiss();

    /**
     * 上下文
     * @return
     */
    Context getContext();
}
