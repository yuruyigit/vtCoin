package com.example;

import com.google.gson.Gson;

import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyClass {
    public static void main(String[] args) {
        stampToDate("1501744780124");
        // "VgBUADgAOAA0AFoARABOAFAATgBWAFQAOAAyAEoATAA4AEQASgBYAA=="
    }

    public static void split() {
        String str = "2017-11-06T14:33:13.834";
        String[] split = str.split("\\.");
        System.out.print(split[0]);
    }

    /*
        * 将时间戳转换为时间
        */
    public static String stampToDate(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        System.out.println(res);
        return res;
    }

    /*
      * 将时间转换为时间戳
      */
    public static String dateToStamp(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = simpleDateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }

    public static String utf8Encode(String str) {
        if (str.getBytes().length != str.length()) {
            try {
                return URLEncoder.encode(str, "Unicode");
            } catch (Exception e) {
                throw new RuntimeException("UnsupportedEncodingException occurred. ", e);
            }
        }
        return str;
    }
}
