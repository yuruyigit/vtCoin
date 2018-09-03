package com.block.vtCoin.util;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;

/**
 * @Description 富文本工具类
 * @Author Yanx
 * @Date 2016/12/2.
 */
public class SpannableStringUtils
{

    /**
     * 获取firstStr为红色的，secondStr为黑色的CharSequence
     *
     * @param firstStr
     * @param secondStr
     * @return
     */
    public static SpannableStringBuilder getRedBlackString(String firstStr, int color, String secondStr)
    {
        SpannableStringBuilder textSpannedBuilder = new SpannableStringBuilder();
        SpannableString firstSpannableString = new SpannableString("【" + firstStr + "】 ");

        firstSpannableString.setSpan(new ForegroundColorSpan(color), 0, firstSpannableString
                .length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        SpannableString secondSpannableString = new SpannableString(secondStr);
        return textSpannedBuilder.append(firstSpannableString).append
                (secondSpannableString);
    }
    /**
     * 获取firstStr为红色的，secondStr为黑色的CharSequence
     *
     * @param firstStr
     * @param secondStr
     * @return
     */
    public static SpannableStringBuilder getGreenCombString(String firstStr, int color, String secondStr)
    {
        SpannableStringBuilder textSpannedBuilder = new SpannableStringBuilder();
        SpannableString firstSpannableString = new SpannableString(""+firstStr+" ");

        firstSpannableString.setSpan(new ForegroundColorSpan(color), 0, firstSpannableString
                .length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        SpannableString secondSpannableString = new SpannableString(secondStr);
        return textSpannedBuilder.append(firstSpannableString).append
                (secondSpannableString);
    }
    /**
     * 获取firstStr为红色的，secondStr为黑色的CharSequence
     *
     * @param firstStr
     * @param secondStr
     * @return
     */
    public static SpannableStringBuilder getBabyPriceString(String tig, String firstStr, int color, int color2, String secondStr)
    {
        SpannableStringBuilder textSpannedBuilder = new SpannableStringBuilder();
        SpannableString tigstring = new SpannableString(" "+tig+" ");

        tigstring.setSpan(new BackgroundColorSpan(color), 0, tigstring
                .length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);//背景色
        tigstring.setSpan(new ForegroundColorSpan(color2), 0, tigstring
                .length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);//字体颜色
        SpannableString firstSpannableString = new SpannableString("【" + firstStr + "】 ");

        firstSpannableString.setSpan(new ForegroundColorSpan(color), 0, firstSpannableString
                .length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        SpannableString secondSpannableString = new SpannableString(secondStr);
        return textSpannedBuilder.append(tigstring).append(" ").append(firstSpannableString).append
                (secondSpannableString);
    }
    /**
     * 自定义背景色和字体颜色
     *
     * @param firstStr
     * @param color
     *@param secondStr  @return
     */
    public static SpannableStringBuilder getRedpriceString(String firstStr, int color1, int color, String secondStr)
    {
        SpannableStringBuilder textSpannedBuilder = new SpannableStringBuilder();
        SpannableString firstSpannableString = new SpannableString(" "+firstStr+" ");

        firstSpannableString.setSpan(new ForegroundColorSpan(color1), 0, firstSpannableString
                .length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);//字体颜色
        firstSpannableString.setSpan(new BackgroundColorSpan(color), 0, firstSpannableString
                .length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);//背景色
        SpannableString secondSpannableString = new SpannableString(secondStr);
        return textSpannedBuilder.append(firstSpannableString).append(" ").append
                (secondSpannableString);
    }

    /**
     *
     * @param firstStr
     * @param color1
     * @param color
     * @param secondStr
     * @return
     */

    public static SpannableStringBuilder getRedServerpriceString(String firstStr, int color1, int color, int color2, String secondStr, String thirdStr)
    {
        SpannableStringBuilder textSpannedBuilder = new SpannableStringBuilder();
        SpannableString firstSpannableString = new SpannableString(" "+firstStr+" ");

        firstSpannableString.setSpan(new ForegroundColorSpan(color1), 0, firstSpannableString
                .length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);//字体颜色
        firstSpannableString.setSpan(new BackgroundColorSpan(color), 0, firstSpannableString
                .length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);//背景色

        SpannableString secondSpannableString = new SpannableString(secondStr);
        secondSpannableString.setSpan(new ForegroundColorSpan(color2), 0, secondSpannableString
                .length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);//字体颜色//第二字体颜色

        SpannableString thirdSpannableString = new SpannableString(thirdStr);
        return textSpannedBuilder.append(firstSpannableString).append(" ").append
                (secondSpannableString).append(thirdSpannableString);
    }



    public static SpannableStringBuilder getRedBlackString(String firstStr, String secondStr)
    {
        SpannableStringBuilder textSpannedBuilder = new SpannableStringBuilder();
        SpannableString firstSpannableString = new SpannableString("【" + firstStr + "】");

        firstSpannableString.setSpan(new ForegroundColorSpan(Color.RED), 0, firstSpannableString
                .length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        SpannableString secondSpannableString = new SpannableString(secondStr);
        return textSpannedBuilder.append(firstSpannableString).append(" ").append
                (secondSpannableString);
    }

    public static SpannableStringBuilder getDefaultAddressString(String address, int color)
    {
        SpannableStringBuilder textSpannedBuilder = new SpannableStringBuilder();
        SpannableString firstSpannableString = new SpannableString("【默认收货地址】");

        firstSpannableString.setSpan(new ForegroundColorSpan(color), 0,
                firstSpannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        SpannableString secondSpannableString = new SpannableString(address);
        return textSpannedBuilder.append(firstSpannableString).append(" ").append
                (secondSpannableString);
    }
}
