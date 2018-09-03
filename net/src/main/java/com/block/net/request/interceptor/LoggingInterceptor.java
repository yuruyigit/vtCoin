package com.block.net.request.interceptor;

import com.block.net.util.L;

import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

/**
 *  自定义日志，这个chain里面包含了request和response，所以你要什么都可以从这里拿
 */
public class LoggingInterceptor implements Interceptor {
    private Charset UTF8 = Charset.forName("UTF-8");

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        long t1 = System.nanoTime();//请求发起的时间fen
        L.i("Net发送请求");
        L.i("请求方式=" + request.method());

        try {
            L.i("请求url=" + String.format("" + request.url()));
        } catch (Exception e) {
            e.printStackTrace();
            L.i("url 异常");
        }
        printHeaders(request.headers());
        printBody(request.body());

        Response response = chain.proceed(request);
        ResponseBody responseBody = response.peekBody(1024 * 1024);
        long t2 = System.nanoTime();//收到响应的时间//(t2 - t1) / 1e6d
        L.i("Net接收响应:");
        try {
            L.i("url=" + String.format("" + response.request().url()));
        } catch (Exception e) {
            e.printStackTrace();
            L.i("url 异常");
        }
        String responseStr = "";
        if (responseBody != null) {
            responseStr = responseBody.string();
        }
        L.i("body=" + responseStr);
        L.i("耗时=" + String.format("" + (t2 - t1) / 1e6d / 1000 + "s"));
        L.i("响应头=" + String.format("" + response.headers()));
        return response;
    }

    /*打印请求头*/
    private void printHeaders(Headers headers) {
        for (int i = 0, count = headers.size(); i < count; i++) {
            String name = headers.name(i);
            L.i(name + ": " + headers.value(i));
        }
    }

    /*打印请求体*/
    private void printBody(RequestBody requestBody) throws IOException {
        if (requestBody != null) {
            Buffer buffer = new Buffer();
            requestBody.writeTo(buffer);
            Charset charset = UTF8;
            if (requestBody.contentType() != null) {
                charset = requestBody.contentType().charset(UTF8);
            }
            if (isPlaintext(buffer)) {
                try {
                    L.i(buffer.readString(charset));
                } catch (Exception e) {
                    e.printStackTrace();
                    L.i("body 异常");
                }


            }
        }
    }

    /*判断是否可以打印请求体*/
    static boolean isPlaintext(Buffer buffer) throws EOFException {
        try {
            Buffer prefix = new Buffer();
            long byteCount = buffer.size() < 64 ? buffer.size() : 64;
            buffer.copyTo(prefix, 0, byteCount);
            for (int i = 0; i < 16; i++) {
                if (prefix.exhausted()) {
                    break;
                }
                int codePoint = prefix.readUtf8CodePoint();
                if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException e) {
            return false; // Truncated UTF-8 sequence.
        }
    }

}