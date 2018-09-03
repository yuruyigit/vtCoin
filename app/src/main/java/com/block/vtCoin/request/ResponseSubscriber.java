package com.block.vtCoin.request;

import com.block.net.mvp.presenter.IPresenterCallback;
import com.block.net.util.L;
import com.block.vtCoin.constant.ApolloAction;
import com.block.vtCoin.constant.Constant;
import com.block.vtCoin.entity.WalletEntity;
import com.block.vtCoin.util.AppManager;
import com.block.vtCoin.util.JsonUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.lsxiao.apllo.Apollo;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 *  方法上加泛型有两种方法
 *  1.public <S> S ResponseSubscriber(Class<S> serviceClass){}
 *  2. public ResponseSubscriber(Class<E> eClass, IPresenterCallback<E> callback) 同时类上加泛型
 */
public class ResponseSubscriber<E> extends Subscriber<ResponseBody> {
    private IPresenterCallback<E> callback;
    private Gson gson;
    private Class<E> eClass;
    private String msg;
    public ResponseSubscriber(Class<E> eClass, IPresenterCallback<E> callback) {
        this.callback = callback;
        gson = new Gson();
        this.eClass = eClass;
    }

    @Override
    public void onCompleted() {
//        L.i("onCompleted");
    }

    @Override
    public void onError(Throwable e) {
        L.i("onError");
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            int code = httpException.code();
            msg = httpException.getMessage();
            if (AppManager.currentLanguage().equals(Constant.Chinese))//中文
                httpCode(code, true);
            else
                httpCode(code, false);
            L.i(msg);
            callback.onError(code, msg);
        } else {
            L.i(e.getMessage());
            callback.onError(-1, e.getMessage());
        }
    }

    /**
     * 此处异常会调用onError()
     */
    @Override
    public void onNext(ResponseBody o) {
//        L.i("onNext");
        try {
            String json = o.string();
            if (json.isEmpty()) {//退出登陆，返回的是空字符串
                callback.onSuccess(json);
            } else if (json.startsWith("[")) {//get请求返回的是数组
                callback.onSuccess(json);
            } else if (json.endsWith("\\<")) {//返回的是登录界面，跳到登录界面
                Apollo.get().send(ApolloAction.To_Login);
            } else if (JsonUtils.checkResult(json)) {
                callback.onSuccess(gson.fromJson(json, eClass));
            } else {
                callback.onError(JsonUtils.getCode(json), JsonUtils.getMessage(json));
            }
        } catch (IOException e) {
            callback.onError(500, "服务器出错了啦！请稍候再试试！");
            e.printStackTrace();
        }
    }


    public void httpCode(int code, boolean isChinese) {
        switch (code) {
            case 400:
                if (isChinese)
                    msg = "错误请求";
                else
                    msg = "request fail";
                L.v(code + msg);
                break;
            case 404:
                if (isChinese)
                    msg = "服务器找不到请求的网页";
                else
                    msg = "The server could not find the requested page";
                L.v(code + msg);
                break;
            case 500:
                if (isChinese)
                    msg = "服务器内部错误";
                else
                    msg = "Server internal error";
                L.v(code + msg);
                break;
            case 504:
                if (isChinese)
                    msg = "当前网络不可用";
                else
                    msg = "The current network is not available";
                msg = "当前网络不可用";
                L.v(code + msg);
                break;
            case 505:
                if (isChinese)
                    msg = "服务器不支持请求中所使用的 HTTP 协议版本。";
                else
                    msg = "The server does not support the HTTP protocol version used in the request.";
                L.v(code + msg);
                break;
        }
    }


}
