package com.block.vtCoin.request;

import com.block.net.request.RetrofitHelper;
import com.block.net.request.progress.ProgressListener;


/**
 * @Description  拿到不同类型的retrofit,再转化为IRetrofitService的对象
 */
public class RequestHelper {

    public static IRetrofitService getRequestService(){
        return RetrofitHelper.getInstance().getRetrofit().create(IRetrofitService.class);
    }

    //下载专用
    public static IRetrofitService getRequestService(String url, ProgressListener listener) {
        return RetrofitHelper.getInstance().getDownLoadRetrofit(url, listener).create(IRetrofitService.class);
    }

}
