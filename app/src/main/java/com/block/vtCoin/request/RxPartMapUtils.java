package com.block.vtCoin.request;

import android.support.annotation.NonNull;

import com.block.vtCoin.util.FileUtils;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by liubin on 2017/10/31.
 */

public class RxPartMapUtils {
    public static RequestBody toRequestTextBody(String value) {
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), value);
        return body;
    }

    public static RequestBody toRequestImageBody(File file) {

        RequestBody fileBody = RequestBody.create(MediaType.parse("image/*"), file);
        return fileBody;
    }
    public static RequestBody toRequestImageForm(File file) {

        RequestBody fileBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        return fileBody;
    }

    public static final String MULTIPART_FORM_DATA = "multipart/form-data";

    @NonNull
    private RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(MediaType.parse(MULTIPART_FORM_DATA), descriptionString);
    }

}
