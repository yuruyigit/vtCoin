package com.block.net.util;

/**
 * @Description
 * @Author lijie
 * @Date 2017/7/14.
 */
public class NumUtil {

    /*产生8位16进制的数*/
    public static String getRandomValue() {
        String str = "";
        for (int i = 0; i < 8; i++) {
            char temp = 0;
            int key = (int) (Math.random() * 2);
            switch (key) {
                case 0:
                    temp = (char) (Math.random() * 10 + 48);//产生随机数字
                    break;
                case 1:
                    temp = (char) (Math.random()*6 + 'a');//产生a-f
                    break;
                default:
                    break;
            }
            str = str + temp;
        }
        return str;
    }

    /*产生8位16进制的数*/
    public static String getRandomNumValue() {
        String str = "";
        int temp=0;
        for (int i = 0; i < 8; i++) {
            temp = (char) (Math.random() * 10);//产生随机数字
            str = str + temp;
        }
        return str;
    }
}
