package com.block.vtCoin.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.block.vtCoin.main.BApplication;
import com.google.gson.Gson;
import java.lang.reflect.Type;

public class SPUtils {
	public static String PREFERENCE_NAME = "preferInfo";
    /**
     * 得到缓存对象
     */
    public static SharedPreferences getDefaultShare() {
        return BApplication.mContext.getSharedPreferences(PREFERENCE_NAME,Context.MODE_APPEND);
    }

    public static boolean putString(String key, String value) {
        SharedPreferences.Editor editor = getDefaultShare().edit();
        editor.putString(key, value);
        return editor.commit();
    }

    public static String getString(String key) {
        return getString(key, "");
    }

    public static String getString(String key, String defaultValue) {
        SharedPreferences share = getDefaultShare();
        if(null!=share){
            return share.getString(key, defaultValue);
        }
        return defaultValue;
    }

    public static boolean putInt(String key, int value) {
        SharedPreferences.Editor editor = getDefaultShare().edit();
        editor.putInt(key, value);
        return editor.commit();
    }

    public static int getInt(String key) {
        return getInt( key, -1);
    }

    public static int getInt(String key, int defaultValue) {
        SharedPreferences share = getDefaultShare();
        if(null!=share){
            return share.getInt(key, defaultValue);
        }
        return defaultValue;
    }

    public static boolean putLong(String key, long value) {
        SharedPreferences.Editor editor = getDefaultShare().edit();
        editor.putLong(key, value);
        return editor.commit();
    }

    public static long getLong(String key) {
        return getLong(key, -1);
    }

    public static long getLong(String key, long defaultValue) {
        SharedPreferences share = getDefaultShare();
        if(null!=share){
            return share.getLong(key, defaultValue);
        }
        return defaultValue;
    }

    public static boolean putFloat(String key, float value) {
        SharedPreferences.Editor editor = getDefaultShare().edit();
        editor.putFloat(key, value);
        return editor.commit();
    }

    public static float getFloat(String key) {
        return getFloat(key, -1);
    }

    public static float getFloat(String key, float defaultValue) {
        SharedPreferences share = getDefaultShare();
        if(null!=share){
            return share.getFloat(key, defaultValue);
        }
        return defaultValue;
    }

    public static boolean putBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = getDefaultShare().edit();
        editor.putBoolean(key, value);
        return editor.commit();
    }

    public static boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        SharedPreferences share = getDefaultShare();
        if(null!=share){
            return share.getBoolean(key, defaultValue);
        }
        return defaultValue;
    }
    /**
     * 添加Object数据
     *
     */
    public static void putObject(String key, Object obj) {
        SharedPreferences.Editor sysEdit = getDefaultShare().edit();
        sysEdit.putString(key, new Gson().toJson(obj));
        sysEdit.commit();
    }

    public static <T> T getObject(String key, Class<T> classT) {
        SharedPreferences share = getDefaultShare();
        String result = null;
        if (share != null) {
            result = share.getString(key, "");
        }
        return toBean(classT, result);
    }

    public static <T> T getObject(String key, T t) {
        SharedPreferences share = getDefaultShare();
        String result = null;
        if (share != null) {
            result = share.getString(key, "");
        }
        return toBean((Class<T>) t, result);
    }
    public static <T> T toBean(Class<T> classT, String result) {
        Type mySuperClass = classT;

        try {
            return new Gson().fromJson(result, mySuperClass);
        } catch (Exception e) {
            return null;
        }
    }
}
