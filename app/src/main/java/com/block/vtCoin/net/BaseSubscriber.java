package com.block.vtCoin.net;

import com.block.net.util.L;
import com.block.vtCoin.entity1.HttpResult;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeoutException;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * Created by liubin on 2017/11/3.
 */

public abstract class BaseSubscriber<T> extends Subscriber<HttpResult<T>> {

    @Override
    public void onCompleted() {
        L.i("");
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        String msg = "";
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            L.i(httpException.getMessage());
            msg = getMessage(httpException.code());
        } else if (e instanceof ConnectException) {
            msg = "连接超时";
        } else if (e instanceof SocketTimeoutException) {
            msg = "socket连接超时";
        } else if (e instanceof TimeoutException) {
            msg = "超时";
        } else
            msg = "未知异常";
        onFail(msg);
    }

    private String getMessage(int code) {
        String msg = "";
        switch (code) {
            case 400:
                msg = "错误请求";
                break;
            case 404:
                msg = "服务器找不到请求的网页";
                break;
            case 500:
                msg = "服务器内部错误";
                break;
            case 504:
                msg = "当前网络不可用";
                break;
            case 505:
                msg = "服务器不支持请求中所使用的 HTTP 协议版本";
                break;
            default:
                msg = "未知错误";
        }
        return msg;
    }

    @Override
    public void onNext(HttpResult<T> result) {
        if (result.getMsgNo() == 0) {//成功
            onSuccess(result.getData());
        } else {
            onFail(getMessageByMap(result.getMsg()));
        }
    }

    private String getMessageByMap(String msg) {
        return msg;
    }

    public abstract void onSuccess(T t);

    public abstract void onFail(String msg);

}
