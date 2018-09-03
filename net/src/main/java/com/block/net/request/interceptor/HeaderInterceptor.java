package com.block.net.request.interceptor;

import android.os.Build;

import java.io.IOException;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Description 为请求添加header的过虑器
 * @Author lijie
 * @Date 2017/6/27.
 */
public class HeaderInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request().newBuilder().addHeader("Connection", "close").build();

        return chain.proceed(request);
    }
}
