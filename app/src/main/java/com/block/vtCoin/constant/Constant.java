package com.block.vtCoin.constant;

public class Constant {

    /*&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&常量&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&*/
    public static final long PHOTOCOMPRESSEDSIZE = 200; //上传图片大小限制：200kb
    public static final int PHOTOCOMPRESSEDHEIGHT = 1920; //上传图片高度限制：1920
    public static final int PHOTOCOMPRESSEDWIDTH = 1080; //上传图片宽度限制：1080
    /**
     * 验证码发送时间间隔 60s
     */
    public static final long SendCodeTime = 6 * 10 * 1000;
    /*&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&SharedPreferences里的&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&*/
    public static final String IsFirstInApp = "isFirstInApp";
    public static final String Version = "version";
    public static final String IsLogin = "isLogin";
    /**
     * 是否设置交易密码
     */
    public static final String IsSetPassword = "isSetDealPassword";
    /**
     * 是否开启登录认证，即是决定交易的时候要输入几次密码（除了交易密码）
     */
    public static final String IsTwoLoginConfirm = "isLoginConfirm";
    /**
     * 如果开启登陆认证，是否是开启google二次认证
     */
    public static final String IsTowGoogleConfirm = "isTowGoogleConfirm";
    /**
     *  如果开启登陆认证，是否是开启sms二次认证
     */
    public static final String IsTowSmsConfirm = "isTowSmsConfirm";
    /**
     *  如果开启登陆认证，是否是开启sms二次认证
     */
    public static final String IsBindPhone = "isBindPhone";
    /**
     *  如果开启登陆认证，是否是开启sms二次认证
     */
    public static final String IsBindEmail = "isBindEmail";

    //当前语言
    public static final String CurrentLanguage = "current_language";
    public static final String Chinese = "中文";
    public static final String English = "English";

}
