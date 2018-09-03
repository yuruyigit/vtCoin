package com.block.net.util.sign;
import java.util.Iterator;
import java.util.Map;

/**
 * @Description
 * @Author lijie
 * @Date 2017/7/16.
 */
public class SignUntil {
    public static String GetSign(String random, String time, String token, Map<String, Object> parameters,String key,String iv){
        StringBuffer sb= new StringBuffer();
        sb.append(getValue(parameters)).append(random).append(time).append(token);//拼接
        String sign=stringSort(sb.toString().trim());//排序
        return AES128.encryptchedan(sign,key,iv);
    }


    public static String GetNoParasSign(String random, String time, String token, String key, String iv){
        StringBuffer sb= new StringBuffer();
        sb.append(random).append(time).append(token);//拼接
        String sign=stringSort(sb.toString().trim());//排序
        return AES128.encryptchedan(sign,key,iv);
    }

    /**
     * @param parameters 参数集合
     * @return post参数
     */
    public static String getValue(Map<String, Object> parameters) {
        StringBuffer sb= new StringBuffer();
        Iterator<Map.Entry<String, Object>> it = parameters.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> entry = it.next();
            Object value = entry.getValue();
            sb.append(value);
        }
        return sb.toString();
    }

    //排序
    public static String stringSort(String str){
        StringBuffer sb=new StringBuffer();
        char[] array=str.toCharArray();
            for (int i = 0; i <array.length; i++) {
                for(int j=0;j<array.length-i-1;j++){
                    if(array[j]>array[j+1]){
                        char temp=array[j];
                        array[j]=array[j+1];
                        array[j+1]=temp;
                    }
                }
            }
            for (int i = 0; i < array.length; i++) {
                sb.append(array[i]);
            }
        return sb.toString();
    }



}
