package com.block.vtCoin.util;

import android.content.Context;
import android.text.TextUtils;

import com.block.vtCoin.R;
import com.block.vtCoin.constant.Constant;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @Description
 * @Author lijie
 * @Date 2017/7/12.
 */
public class StringUtils {

    public static String checkImagePath(String imagePath) {
        if (!TextUtils.isEmpty(imagePath)) {
            if (imagePath.contains("localhost")) {
                imagePath = imagePath.replace("localhost", "1711255wc1.iok.la");
            }
        }
        return imagePath;
    }
}