package com.block.vtCoin.util;

import android.text.TextUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @Description 单位换算工具类
 * @Author MoseLin
 * @Date 2016/8/29.
 */

public class UnitUtil
{
    /**
     * 格式化存储单位
     * @param size 字节
     * @return String
     */
    public static String formatStorageUnit(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            // return size + "Byte";
            return "0K";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "KB";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "MB";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()
                + "TB";
    }

    public static String addComma(double str) {
        return addComma(str,true);
    }
    public static String addComma(double str, boolean isHasDecimals) {
        // 将传进数字反转
        DecimalFormat df;
        if (isHasDecimals)
            df = new DecimalFormat("###############0.00");//   16位整数位，两小数位
        else df = new DecimalFormat("###############0");
        String temp = df.format(str);
        String end = "";
        if (temp.contains("."))
        {
            String[] sp = temp.split("\\.");
            temp = sp[0];
            end = "." + sp[1];
        }

        String reverseStr = new StringBuilder(temp).reverse().toString();
        String strTemp = "";
        for (int i = 0; i < reverseStr.length(); i++)
        {
            if (i * 3 + 3 > reverseStr.length())
            {
                strTemp += reverseStr.substring(i * 3, reverseStr.length());
                break;
            }
            strTemp += reverseStr.substring(i * 3, i * 3 + 3) + ",";
        }
        // 将[789,456,] 中最后一个[,]去除
        if (strTemp.endsWith(","))
        {
            strTemp = strTemp.substring(0, strTemp.length() - 1);
        }
        // 将数字重新反转
        String resultStr = new StringBuilder(strTemp).reverse().append(end).toString();
        return resultStr;
    }

    public static String formatOne(double money) {
        DecimalFormat df = new DecimalFormat("###############0.0");//   16位整数位，两小数位
        return df.format(money);
    }

    public static String formatTwo(double money) {
        DecimalFormat df = new DecimalFormat("###############0.00");//   16位整数位，两小数位
        return df.format(money);
    }


    public static String formatThird(double money) {
        DecimalFormat df = new DecimalFormat("###############0.000");//   16位整数位，两小数位
        return df.format(money);
    }
    public static String formatFour(double money) {
        DecimalFormat df = new DecimalFormat("###############0.0000");//   16位整数位，两小数位
        return df.format(money);
    }

    public static String formatFive(double money) {
        DecimalFormat df = new DecimalFormat("###############0.00000");//   16位整数位，两小数位
        return df.format(money);
    }
    /**
     * 计算桂富豆手续费
     *
     * @param egg
     * @return
     */
    public static double calculateEggMoney(int egg)
    {
        double money = egg * 0.05;
        //        DecimalFormat df = new DecimalFormat("###############0.00");//   16位整数位，两小数位
        //        return df.format(money);
        return money;
    }

    public static String addXingPhone(String user_name)
    {
        if (!TextUtils.isEmpty(user_name))
        {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < user_name.length(); i++)
            {
                char c = user_name.charAt(i);
                if (i >= 3 && i <= 8)
                {
                    sb.append('*');
                } else
                {
                    sb.append(c);
                }
            }
            return sb.toString();
        }
        return null;
    }
}
