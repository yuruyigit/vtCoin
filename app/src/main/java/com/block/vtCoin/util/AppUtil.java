package com.block.vtCoin.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import com.block.vtCoin.constant.Constant;
import java.util.List;
import java.util.Locale;

/**
 * @Description
 * @Author lijie
 * @Date 2017/7/21.
 */
public class AppUtil {

    /*是否是中文*/
    public static boolean isZh(Context context) {
        Locale locale = context.getResources().getConfiguration().locale;
        String language = locale.getLanguage();
        if (language.endsWith("zh"))
            return true;
        else
            return false;
    }


    /**
     * 检查版本号，以判断是否是安装了新版本来设置是否要启动引导页
     *
     * @param context activity||application
     */
    public static void checkVersion(Context context) {
        try {
            int oldVersion = SPUtils.getInt(Constant.Version, -1);
            int currVersion = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
            if (currVersion != oldVersion) {
                SPUtils.putBoolean(Constant.IsFirstInApp, true);
                SPUtils.putInt(Constant.Version, currVersion);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 隐藏虚拟键盘
     *
     * @param v 当前view
     */
    public static void hideKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) v.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
        }
    }
    /**
     * 显示键盘
     *
     * @param view 当前view
     */
    public static void showKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, InputMethodManager.RESULT_UNCHANGED_SHOWN);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public static boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = { 0, 0 };
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left
                    + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击EditText的事件，忽略它。
                return false;
            } else {
                return true;
            }
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditView上，和用户用轨迹球选择其他的焦点
        return false;
    }



    /*得到系统语言*/
    public static String getDefaltLanguage() {
        Locale locale = Locale.getDefault();
        String language = locale.getLanguage();
        return language;
    }


    /*改变语言*/
    public static void changeLanguage(Context context, String language) {
        //设置应用语言类型
        Resources resources = context.getResources();
        Configuration config = resources.getConfiguration();
        DisplayMetrics dm = resources.getDisplayMetrics();
        if (language.equals("zh")) {//中文
            config.locale = Locale.SIMPLIFIED_CHINESE;
        } else {//英文
            config.locale = Locale.ENGLISH;
        }
        resources.updateConfiguration(config, dm);
    }

    /*解决scroolView不能滑动的问题*/
    public static void setSoft(Activity activity, final ScrollView scrollView) {
        final View decorView = activity.getWindow().getDecorView();
        decorView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect rect = new Rect();
                decorView.getWindowVisibleDisplayFrame(rect);
                int screenHeight = decorView.getRootView().getHeight();
                int heightDifference = screenHeight - rect.bottom;//计算软键盘占有的高度  = 屏幕高度 - 视图可见高度
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) scrollView.getLayoutParams();
                layoutParams.setMargins(0, 0, 0, heightDifference);//设置ScrollView的marginBottom的值为软键盘占有的高度即可
                scrollView.requestLayout();
            }
        });
    }

    /**
     * 检测是否安装微信
     *
     * @param context
     * @return
     */

    public static boolean isWxInstall(Context context) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mm")) {
                    return true;
                }
            }
        }

        return false;
    }
}