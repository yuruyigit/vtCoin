package com.block.net.request;

import com.block.net.request.interceptor.ProcessInterceptor;
import com.block.net.request.progress.ProgressListener;
import com.block.net.util.L;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {
    private static RetrofitHelper instance;

    public static RetrofitHelper getInstance() {
        if (instance == null)
            instance = new RetrofitHelper();
        return instance;
    }

    /*用默认url*/
    public Retrofit getRetrofit() {
        OkHttpClient client = HttpClient.getClient();
        Retrofit.Builder builder = getBuilder(HttpClient.url, client);
        return builder.build();
    }

    /*拿部分url，且增加监听*/
    public Retrofit getDownLoadRetrofit(String url, ProgressListener listener) {
        OkHttpClient client = HttpClient.getClient();
        url = HttpClient.getHostName(url);
        client = client.newBuilder().addNetworkInterceptor(new ProcessInterceptor(listener)).build();
        Retrofit.Builder builder = getBuilder(url, client);
        return builder.build();
    }

    /*将url和client配置到retrofit*/
    private Retrofit.Builder getBuilder(String url, OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client);
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
