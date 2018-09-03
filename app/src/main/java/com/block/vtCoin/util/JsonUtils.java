package com.block.vtCoin.util;

import android.content.Context;

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
public class JsonUtils {
    private static HashMap<String, String> chineseMap;
    private static HashMap<String, String> englishMap;

    public static void initCodeMessage(Context context) {
        /*解析中文*/
        InputStream chineseStream = context.getResources().openRawResource(R.raw.chinese_code);
        String chineseStr = FileUtils.getString(chineseStream);
        chineseMap = new HashMap<>();
        String[] chineseSplit1 = chineseStr.split("&");
        for (int i = 0; i < chineseSplit1.length; i++) {
            String[] chineseSplit2 = chineseSplit1[i].split(":");
            chineseMap.put(chineseSplit2[0].replace("\n", "").trim(), chineseSplit2[1].trim());
        }
//        /*解析英文*/
//        InputStream englishStream = context.getResources().openRawResource(R.raw.english_code);
//        String englishStr = FileUtils.getString(englishStream);
//        englishMap = new HashMap<>();
//        String[] englishSplit1 = englishStr.split("&");
//        for (int i = 0; i < englishSplit1.length; i++) {
//            String[] englishSplit2 = englishSplit1[i].split(":");
//            englishMap.put(englishSplit2[0].replace("\n", "").trim(), englishSplit2[1].trim());
//        }
    }

    public static boolean checkResult(String json) {
        try {
            JSONObject object = new JSONObject(json);
            if (object.has("msgNo")) {
                if (object.optInt("msgNo") == 0)
                    return true;
                else
                    return false;
            } else
                return true;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static int getCode(String json) {
        int code = 500;
        try {
            JSONObject object = new JSONObject(json);
            code = object.optInt("msgNo");
        } catch (JSONException e) {
            e.printStackTrace();

        }
        return code;
    }

    public static String getMessage(String json) {
        String message = null;
        try {
            JSONObject object = new JSONObject(json);

            if (object != null) {
                if (AppManager.currentLanguage().equals(Constant.Chinese))//中文
                    message = getChinaMessage(object.optString("msg"));
                else
                    message = getEnglishMessage(object.optString("msg"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
            message = "请求服务器出错，请稍候再试！";
        }
        return message;
    }

    public static String getChinaMessage(String msg) {
        String tipMsg = chineseMap.get(msg);
        if (tipMsg == null) {
            tipMsg = msg;
        }
        return tipMsg;
    }

    public static String getEnglishMessage(String msg) {
        String tipMsg = chineseMap.get(msg);
        if (tipMsg == null) {
            tipMsg = msg;
        }
        return tipMsg;
    }

    /**
     * @param json 数组字符串
     * @param clazz 对象
     * @return
     */
    public static <T> ArrayList<T> jsonToArrayList(String json, Class<T> clazz) {
        Type type = new TypeToken<ArrayList<JsonObject>>()
        {}.getType();
        ArrayList<JsonObject> jsonObjects = new Gson().fromJson(json, type);

        ArrayList<T> arrayList = new ArrayList<>();
        for (JsonObject jsonObject : jsonObjects) {
            arrayList.add(new Gson().fromJson(jsonObject, clazz));
        }
        return arrayList;
    }
}
