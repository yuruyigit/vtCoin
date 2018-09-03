package com.block.net.util.sign;

import android.util.Base64;
import android.util.Log;

import java.io.UnsupportedEncodingException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @Description
 * @Author lijie
 * @Date 2017/7/16.
 */
public class AES128 {

    /**
     * @param data
     * @param key
     * @param iv
     * @return
     */
    public static String encrypt(String data, String key, String iv) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            int blockSize = cipher.getBlockSize();
            byte[] dataBytes = data.getBytes("UTF-8");
            int plaintextLength = dataBytes.length;
            if (plaintextLength % blockSize != 0) {
                plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
            }
            byte[] plaintext = new byte[plaintextLength];
            System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);

            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");

            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
            byte[] encrypted = cipher.doFinal(plaintext);
            return Base64.encodeToString(encrypted, Base64.DEFAULT).replaceAll("\\s*", "").trim(); // 加密
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static byte[] encrypt(byte[] content, String password, String iv) {
        try {
            SecretKeySpec key = createKey(password);
            Cipher cipher = Cipher.getInstance(CipherMode);
            cipher.init(Cipher.ENCRYPT_MODE, key, createIV(iv));
            byte[] result = cipher.doFinal(content);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 算法/模式/填充
     **/
    private static final String CipherMode = "AES/CBC/PKCS7Padding";


    /**
     * 创建密钥
     **/
    private static SecretKeySpec createKey(String key) {
        byte[] data = null;
        if (key == null) {
            key = "";
        }
        StringBuffer sb = new StringBuffer(16);
        sb.append(key);
        while (sb.length() < 16) {
            sb.append("0");
        }
        if (sb.length() > 16) {
            sb.setLength(16);
        }

        try {
            data = sb.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new SecretKeySpec(data, "AES");
    }

    /**
     * IV值格式化
     *
     * @param password
     * @return
     */
    private static IvParameterSpec createIV(String password) {
        byte[] data = null;
        if (password == null) {
            password = "";
        }
        StringBuffer sb = new StringBuffer(16);
        sb.append(password);
        while (sb.length() < 16) {
            sb.append("0");
        }
        if (sb.length() > 16) {
            sb.setLength(16);
        }

        try {
            data = sb.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new IvParameterSpec(data);
    }

    /**
     * @param content
     * @param password
     * @param iv
     * @return
     */
    public static String encryptchedan(String content, String password, String iv) {
        try {
            SecretKeySpec key = createKey(password);
            Cipher cipher = Cipher.getInstance(CipherMode);
            cipher.init(Cipher.ENCRYPT_MODE, key, createIV(iv));
            byte[] result = cipher.doFinal(content.getBytes("UTF-8"));
            byte[] result1 = cipher.doFinal(content.getBytes());
            Log.i("长度", result.length + "");
            return Base64.encodeToString(result, Base64.DEFAULT).replaceAll("\\s*","").trim(); // 加密result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密字节数组
     **/
    public static byte[] decrypt(byte[] content, String password, String iv) {
        try {
            SecretKeySpec key = createKey(password);
            Cipher cipher = Cipher.getInstance(CipherMode);
            cipher.init(Cipher.DECRYPT_MODE, key, createIV(iv));
            byte[] result = cipher.doFinal(content);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//    String data = "Test String";
//    String key = "1234567812345678";
//    String iv = "1234567812345678";
//    Cipher cipher = Cipher.getInstance("AES/CBCPadding");
//    int blockSize = cipher.getBlockSize();
//    byte[] dataBytes = data.getBytes();
//    int plaintextLength = dataBytes.length;
//    if(plaintextLength %blockSize !=0)
//
//    {
//        plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
//    }
//
//    byte[] plaintext = new byte[plaintextLength];
//    System.arraycopy(dataBytes,0,plaintext,0,dataBytes.length);
//    SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
//    IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());
//    cipher.init(Cipher.ENCRYPT_MODE,keyspec,ivspec);
//    byte[] encrypted = cipher.doFinal(plaintext);
//    return new sun.misc.BASE64Encoder().
//
//    encode(encrypted);
//} catch(Exception e){e.printStackTrace();return null;}
//
//
//
 }
