package com.block.net.request;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;

import com.block.net.request.interceptor.HeaderInterceptor;
import com.block.net.request.interceptor.RequestCacheInterceptor;
import com.block.net.request.interceptor.TagInterceptor;
import com.block.net.util.L;
import com.block.net.util.NumUtil;
import com.block.net.util.TimeUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @Description
 * @Author lijie
 * @Date 2017/6/12.
 */
public class HttpClient {
    @SuppressLint("StaticFieldLeak")
    public static Context mContext;
    public static String url;// 请求地址域名
    private static OkHttpClient client;
    private static OkHttpClient.Builder builder;
    /*初始化请求,*/
    @TargetApi(Build.VERSION_CODES.FROYO)
    public static void init(Context context, String baseUrl) {
        mContext = context;
        url = baseUrl;
        //http cache path设置缓存路径
        File cacheFile = new File(mContext.getExternalCacheDir(), "responses");
        //cache size设置缓存大小 100M
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100);
        builder = new OkHttpClient.Builder()
                //添加缓存拦截器
//                .retryOnConnectionFailure(true)//连接失败后是否重新连接
//                .addInterceptor(new LoggingInterceptor())
                .connectTimeout(30, TimeUnit.SECONDS)//连接超时时间30S
                .readTimeout(30, TimeUnit.SECONDS) //读取超时时间设置30S
                .writeTimeout(30, TimeUnit.SECONDS)//写入超时时间设置30S
//                .addInterceptor(new HeaderInterceptor())//解决onError:unexpected end of stream on okhttp3.Address@5ddf6bae
                .addInterceptor(new TagInterceptor())
                .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        L.i("RetrofitLog,  " + message);
                    }
                }).setLevel(HttpLoggingInterceptor.Level.BODY))
//                .addInterceptor(new RequestCacheInterceptor(context))
                .cookieJar(new CookieJar() {
                    private final HashMap<String, List<Cookie>> cookieStore = new HashMap<>();
                    @Override
                    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                        //保存cookie
                        cookieStore.put(url.host(), cookies);
                    }

                    @Override
                    public List<Cookie> loadForRequest(HttpUrl url) {
                        //读取cookie
                        List<Cookie> cookies = cookieStore.get(url.host());
                        return cookies != null ? cookies : new ArrayList<Cookie>();
                    }
                })
                .cache(cache);
    }

    /*默认的client*/
    public static OkHttpClient getClient() {
        if (client == null) {
            client = builder.build();
        }
        return client;
    }

    //随机数
    public static String getRandom() {
        return NumUtil.getRandomNumValue();
    }

    //时间戳
    public static String getTime() {
        return TimeUtils.getUTCtime() + "";
    }

    /**
     * @return 拿到url的端口号https://www.victorychain.com/package/pocket_weite_c4_v1.1.1_release.apk
     * https://www.victorychain.com/
     */
    public static String getHostName(String urlString) {
        String head = "";
        int index = urlString.indexOf("://");
        if (index != -1) {
            head = urlString.substring(0, index + 3);
            urlString = urlString.substring(index + 3);
        }
        index = urlString.indexOf("/");
        if (index != -1) {
            urlString = urlString.substring(0, index + 1);
        }
        return head + urlString;
    }
}
