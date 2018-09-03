package com.block.vtCoin.util;

import android.Manifest;
import android.app.Activity;

import com.tbruyelle.rxpermissions.RxPermissions;

import rx.functions.Action1;


/**
 * @Description 权限工具类, 针对6.0+机型
 * @Author Yanx
 * @Date 17-1-19.
 */

public class PermissionsUtils
{

    /**
     * 检测并请求多个权限
     *
     * @param activity
     * @param runnable 获取权限后统一执行
     */
    public static void requestPermissions(final Activity activity,
                                          final Runnable runnable,
                                          final String... permissions)
    {
        RxPermissions.getInstance(activity).request(permissions).subscribe(new Action1<Boolean>()
        {
            @Override
            public void call(Boolean aBoolean)
            {
                if (aBoolean)
                    runnable.run();
                else
                    RxPermissions.getInstance(activity).shouldShowRequestPermissionRationale
                            (activity, permissions);
            }
        });
    }

    /**
     * 检测并请求摄像头权限
     *
     * @param activity
     * @param runnable 获取权限后执行
     */

    public static void requestCamera(final Activity activity,
                                     final Runnable runnable)
    {
        requestPermissions(activity, runnable, Manifest.permission.CAMERA);
    }

    /**
     * 检测并请求定位权限
     *
     * @param activity
     * @param runnable 获取权限后执行
     */
    public static void requestLocation(final Activity activity,
                                       final Runnable runnable)
    {
        requestPermissions(activity, runnable, Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION);
    }

    /**
     * 检测并请求拨打电话权限
     *
     * @param activity
     * @param runnable 获取权限后执行
     */
    public static void requestCall(final Activity activity,
                                   final Runnable runnable)
    {
        requestPermissions(activity, runnable, Manifest.permission.CALL_PHONE);
    }

    /**
     * 检测并请求读取内存空间权限
     *
     * @param activity
     * @param runnable 获取权限后执行
     */
    @Deprecated
    public static void requestReadStorage(final Activity activity,
                                          final Runnable runnable)
    {
        requestPermissions(activity, runnable, Manifest.permission.READ_EXTERNAL_STORAGE);
    }

    /**
     * 检测并请求写入内存空间权限
     *
     * @param activity
     * @param runnable 获取权限后执行
     */
    @Deprecated
    public static void requestWriteStorage(final Activity activity,
                                           final Runnable runnable)
    {
        requestPermissions(activity, runnable, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    /**
     * 检测并请求读取/写入内存空间权限
     *
     * @param activity
     * @param runnable 获取权限后执行
     */
    public static void requestReadWriteStorage(final Activity activity,
                                               final Runnable runnable)
    {
        requestPermissions(activity, runnable, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest
                .permission.WRITE_EXTERNAL_STORAGE);
    }
}

/*
 * 以下权限只需要在AndroidManifest.xml中声明即可使用
 * android.permission.ACCESS_LOCATION_EXTRA_COMMANDS
 * android.permission.ACCESS_NETWORK_STATE
 * android.permission.ACCESS_NOTIFICATION_POLICY
 * android.permission.ACCESS_WIFI_STATE
 * android.permission.ACCESS_WIMAX_STATE
 * android.permission.BLUETOOTH
 * android.permission.BLUETOOTH_ADMIN
 * android.permission.BROADCAST_STICKY
 * android.permission.CHANGE_NETWORK_STATE
 * android.permission.CHANGE_WIFI_MULTICAST_STATE
 * android.permission.CHANGE_WIFI_STATE
 * android.permission.CHANGE_WIMAX_STATE
 * android.permission.DISABLE_KEYGUARD
 * android.permission.EXPAND_STATUS_BAR
 * android.permission.FLASHLIGHT
 * android.permission.GET_ACCOUNTS
 * android.permission.GET_PACKAGE_SIZE
 * android.permission.INTERNET
 * android.permission.KILL_BACKGROUND_PROCESSES
 * android.permission.MODIFY_AUDIO_SETTINGS
 * android.permission.NFC
 * android.permission.READ_SYNC_SETTINGS
 * android.permission.READ_SYNC_STATS
 * android.permission.RECEIVE_BOOT_COMPLETED
 * android.permission.REORDER_TASKS
 * android.permission.REQUEST_INSTALL_PACKAGES
 * android.permission.SET_TIME_ZONE
 * android.permission.SET_WALLPAPER
 * android.permission.SET_WALLPAPER_HINTS
 * android.permission.SUBSCRIBED_FEEDS_READ
 * android.permission.TRANSMIT_IR
 * android.permission.USE_FINGERPRINT
 * android.permission.VIBRATE
 * android.permission.WAKE_LOCK
 * android.permission.WRITE_SYNC_SETTINGS
 * com.android.alarm.permission.SET_ALARM
 * com.android.launcher.permission.INSTALL_SHORTCUT
 * com.android.launcher.permission.UNINSTALL_SHORTCUT
 */