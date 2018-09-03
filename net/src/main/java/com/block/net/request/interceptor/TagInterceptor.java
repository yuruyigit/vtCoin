package com.block.net.request.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Description 为请求添加header的过虑器
 * @Author lijie
 * @Date 2017/6/27.
 */
public class TagInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request().newBuilder().tag("http").build();
        return chain.proceed(request);
    }
}
