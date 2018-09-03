package com.block.vtCoin.request;

import com.block.net.mvp.model.IBaseModel;
import com.block.vtCoin.entity.Page;
import com.block.vtCoin.entity.PageEntity;

import java.io.File;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MyModel<E> implements IBaseModel {

    /**
     * get请求，可以重新定义url,
     * @url 如果传进来的url以 http:// 开头则表示重新定义url,否则就是拼接到默认的url后面
     */
    public Subscription getAction() {
        return RequestHelper.getRequestService().getAction("http://blog.csdn.net/stven_king/article/details/52372172")
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public Subscription getAction(String action,ResponseSubscriber<E> subscriber) {
        return RequestHelper.getRequestService().getAction(action)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * get请求，
     */
    public Subscription getPara(String action, Map<String, Object> parameters, ResponseSubscriber<E> subscriber) {
        return RequestHelper.getRequestService().getPara(action, parameters)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
    /**
     * 上传图片
     */
    public Subscription postUpload(String action, Map<String, String> parameters, String[] imagePaths, ResponseSubscriber<E> subscriber) {
        File file = new File(imagePaths[0]);
        RequestBody requestBody = null;
        MultipartBody.Part body = null;
        if (file.exists()) {
            requestBody = RxPartMapUtils.toRequestImageBody(file);
            body = MultipartBody.Part.createFormData("image", file.getName(), requestBody);
        }
        return RequestHelper.getRequestService().uploadImage(action, "0", body)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * post请求,只传action
     */
    public Subscription postAction(String action, ResponseSubscriber<E> subscriber) {
        return RequestHelper.getRequestService().postAction(action)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
    /**
     * post请求,传action和para ,para放在请求体里
     */
    public Subscription postPara(String action, Map<String, Object> parameters, ResponseSubscriber<E> subscriber) {
        return RequestHelper.getRequestService().postPara(action, parameters)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
    /**
     * post请求,传action和para，para拼接在url后面
     */
    public Subscription postParaQuery(String action, Map<String, Object> parameters, ResponseSubscriber<E> subscriber) {
        return RequestHelper.getRequestService().postParaQuery(action, parameters)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * post请求,传action和数组
     */
    public Subscription postArray(String action, String[] array, ResponseSubscriber<E> subscriber) {
        return RequestHelper.getRequestService().postArray(action, array)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


    /**
     * post请求,传action和body
     */
    public Subscription postBodyPageEntity(String action, PageEntity page, ResponseSubscriber<E> subscriber) {
        return RequestHelper.getRequestService().postBodyPageEntity(action, page)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
    /**
     * post请求,传action和body
     */
    public Subscription postBodyPage(String action, Page page, ResponseSubscriber<E> subscriber) {
        return RequestHelper.getRequestService().postBodyPage(action, page)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
    /**
     * post请求,传action、para和body
     */
    public Subscription postParaBody(String action, Map<String, Object> parameters, Page page, ResponseSubscriber<E> subscriber) {
        return RequestHelper.getRequestService().postParaBody(action,parameters,page)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

}
