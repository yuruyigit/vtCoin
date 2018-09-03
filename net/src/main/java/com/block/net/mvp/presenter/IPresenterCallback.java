package com.block.net.mvp.presenter;

public interface IPresenterCallback<T> {
    /**
     *  返回实体类
     */
    void onSuccess(T entity);

    /**
     *  返回json
     */
    void onSuccess(String json);

    /**
     * 请求发生错误
     * @param code 错误码
     * @param msg 提示信息
     */
    void onError(int code, String msg);
}
