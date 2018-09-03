package com.block.vtCoin.util;

import android.content.Context;
import android.os.Environment;

import com.block.vtCoin.constant.Constant;
import com.block.vtCoin.entity.MarketEntity;
import com.block.vtCoin.entity.PersonInfoEntity;

import java.io.File;
import java.util.List;

/**
 * @Description
 * @Author Yanx
 * @Date 17-1-25.
 */
public class AppManager {
    public static final String AppPath = Environment.getExternalStorageDirectory() + File.separator + "vtCoin" + File.separator;

    private static Context mContext;
    private static PersonInfoEntity.DataBean mPersonInfo;
    private static List<MarketEntity> mBtcData, mLtcData, mEthData;

    public static Context getContext() {
        return mContext;
    }

    public static void setContext(Context context) {
        mContext = context;
        ScreenUtils.init(context);
    }

    /*是否登陆*/
    public static boolean isLogin() {
        return SPUtils.getBoolean(Constant.IsLogin, false);
    }

    /*是否设置了交易密码*/
    public static boolean isSetPassword() {
        return SPUtils.getBoolean(Constant.IsSetPassword, false);
    }

    /*是否绑定手机号*/
    public static boolean isBindPhone() {
        return SPUtils.getBoolean(Constant.IsBindPhone, false);
    }

    /*是否绑定邮箱*/
    public static boolean isBindEmail() {
        return SPUtils.getBoolean(Constant.IsBindEmail, false);
    }

    public static String currentLanguage() {
        return SPUtils.getString(Constant.CurrentLanguage, Constant.Chinese);
    }

    public static void setPersonInfo(PersonInfoEntity.DataBean data) {
        mPersonInfo = data;
    }

    public static PersonInfoEntity.DataBean getPersonInfo() {
        return mPersonInfo;
    }

    public static void setBtcData(List<MarketEntity> mMarketList) {
        mBtcData = mMarketList;
    }
    public static List<MarketEntity> getBtcData() {
        return mBtcData;
    }
    public static void setLtcData(List<MarketEntity> mMarketList) {
        mLtcData = mMarketList;
    }
    public static List<MarketEntity> getLtcData() {
        return mLtcData;
    }
    public static void setEthData(List<MarketEntity> mMarketList) {
        mEthData = mMarketList;
    }
    public static List<MarketEntity> getEthData() {
        return mEthData;
    }
}
