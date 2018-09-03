package com.block.vtCoin.net;

import com.block.net.util.L;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceFactory {
    public static final String baseUrl = "http://1711255wc1.iok.la/";//测试
//    public static final String baseUrl = "http://www.vtcoin.cn/";//正式
    public static final String downUrl = "";
    private final Gson gson;

    public ServiceFactory() {
        gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd hh:mm:ss")
                .create();
    }

    private static class SingleClass {
        private static final ServiceFactory Instance = new ServiceFactory();
    }

    public static ServiceFactory getInstance() {
        return SingleClass.Instance;
    }


    /**
     * 如果你返回一个类的对象，但你有不知道这个类是什么，那就这样写，传一个泛型，同时能用到这个类的属性
     * @param serviceClass
     * @return
     */
    public <S> S createBaseService(Class<S> serviceClass) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(serviceClass);
    }

    public <S> S createDownService(Class<S> serviceClass) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(downUrl)

                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(serviceClass);
    }

    private OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        L.i("RetrofitLog,  " + message);
                    }
                }).setLevel(HttpLoggingInterceptor.Level.BODY));
//                .cache(new Cache(new File("缓存文件路经", "缓存文件名"), 10 * 1024 * 1024));
        return builder.build();
    }
}
