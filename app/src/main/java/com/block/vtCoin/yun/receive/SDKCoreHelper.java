package com.block.vtCoin.yun.receive;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.block.net.util.L;
import com.block.vtCoin.R;
import com.block.vtCoin.main.BApplication;
import com.block.vtCoin.main.MainActivity;
import com.yuntongxun.ecsdk.ECChatManager;
import com.yuntongxun.ecsdk.ECDevice;
import com.yuntongxun.ecsdk.ECError;
import com.yuntongxun.ecsdk.ECInitParams;
import com.yuntongxun.ecsdk.ECNotifyOptions;
import com.yuntongxun.ecsdk.SdkErrorCode;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 容联 初始化
 * 1. 初始化的回掉，
 * 2. 登录的回掉
 * 3. 退出登录的回掉
 * 4.初始化一对一的聊天接口
 * Created by zdc on 2015/10/18.
 */
public class SDKCoreHelper implements ECDevice.InitListener, ECDevice.OnECDeviceConnectListener, ECDevice.OnLogoutListener {

    public static final String TAG = "SDKCoreHelper";
    /*推出登陆*/
    public static final String ACTION_LOGOUT = "cn.ddhl.cust.ECDemo_logout";
    /*掉线了，去登录*/
    public static final String ACTION_SDK_CONNECT = "cn.ddhl.cust.Intent_Action_SDK_CONNECT";
    /*异地登陆，被踢下线*/
    public static final String ACTION_KICK_OFF = "cn.ddhl.cust.Intent_ACTION_KICK_OFF";
    private static SDKCoreHelper sInstance;
    private Context mContext;
    private ECDevice.ECConnectState mConnect = ECDevice.ECConnectState.CONNECT_FAILED;
    private ECInitParams mInitParams;
    private ECInitParams.LoginMode mMode = ECInitParams.LoginMode.FORCE_LOGIN;
    /**
     * 初始化错误
     */
    public static final int ERROR_CODE_INIT = -3;
    public static final int WHAT_SHOW_PROGRESS = 0x101A;
    public static final int WHAT_CLOSE_PROGRESS = 0x101B;
    private boolean mKickOff = false;//是否是被踢下线
    private ECNotifyOptions mOptions;
    public static SoftUpdate mSoftUpdate;//容云的版本信息

    private SDKCoreHelper() {
        initNotifyOptions();//接收消息的一些参数设置
    }

    private void initNotifyOptions() {
        if (mOptions == null) {
            mOptions = new ECNotifyOptions();
        }
        // 设置新消息是否提醒
        mOptions.setNewMsgNotify(true);
        // 设置状态栏通知图标
        mOptions.setSmallIcon(R.mipmap.ic_launcher);
        // 设置是否启用勿扰模式（不会声音/震动提醒）
        mOptions.setSilenceEnable(false);
        // 设置勿扰模式时间段（开始小时/开始分钟-结束小时/结束分钟）
        // 小时采用24小时制
        // 如果设置勿扰模式不启用，则设置勿扰时间段无效
        // 当前设置晚上11点到第二天早上8点之间不提醒
        mOptions.setSilenceTime(23, 0, 8, 0);
        // 设置是否震动提醒(如果处于免打扰模式则设置无效，没有震动)
        mOptions.enableShake(true);
        // 设置是否声音提醒(如果处于免打扰模式则设置无效，没有声音)
        mOptions.enableSound(true);
    }

    public static SDKCoreHelper getInstance() {
        if (sInstance == null) {
            sInstance = new SDKCoreHelper();
        }
        return sInstance;
    }

    public static boolean isKickOff() {
        return getInstance().mKickOff;
    }

    private String chatToken;

    public static void init(String chatToken) {
        getInstance().chatToken=chatToken;
        getInstance().mContext= BApplication.mContext;
        L.i("");
        getInstance().mKickOff = false;//不是被踢下线
        // 判断SDK是否已经初始化，没有初始化则先初始化SDK
        if (!ECDevice.isInitialized()) {
            L.i("");
            ECDevice.initial(getInstance().mContext, getInstance());
            return;
        }
        // 已经初始化成功，直接进行注册
        getInstance().onInitialized();
    }


    //设置软件更新的版本
    public static void setSoftUpdate(String version, boolean mode) {
        mSoftUpdate = new SoftUpdate(version, mode);
    }

    public static class SoftUpdate {
        public String version;
        public boolean mode;

        public SoftUpdate(String version, boolean mode) {
            this.version = version;
            this.mode = mode;
        }
    }

    /*初始化成功*/
    @Override
    public void onInitialized() {
        L.i("初始化成功");
        //     设置消息提醒
        ECDevice.setNotifyOptions(mOptions);
        /*让IMChattingHelper拿到聊天功能接口*/
        IMChattingHelper.getInstance().initManager();
        // 设置接收VoIP来电事件通知Intent
        // 呼入界面activity、开发者需修改该类
        Intent intent = new Intent(getInstance().mContext, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(getInstance().mContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        ECDevice.setPendingIntent(pendingIntent);
        // 设置SDK注册结果回调通知，当第一次初始化注册成功或者失败会通过该引用回调
        // 通知应用SDK注册状态
        // 当网络断开导致SDK断开连接或者重连成功也会通过该设置回调
        ECDevice.setOnChatReceiveListener(IMChattingHelper.getInstance());
        /*设置登录成功的监听*/
        ECDevice.setOnDeviceConnectListener(this);
        /*初始化登录参数*/
        if (mInitParams == null) {
            mInitParams = ECInitParams.createParams();
        }
        mInitParams.reset();
        // 如：VoIP账号/手机号码/..
//        mInitParams.setUserid("VT82JL8DJX");
        mInitParams.setUserid("15007253417");
        mInitParams.setAppKey("8a216da85fe1c856015fe31816030130");
        mInitParams.setToken("8d37c05e1894d60b78b97bb7fb733b22");
        mInitParams.setMode(getInstance().mMode);
        mInitParams.setAuthType(ECInitParams.LoginAuthType.NORMAL_AUTH);
        //LoginMode（强制上线：FORCE_LOGIN  默认登录：AUTO。使用方式详见注意事项）
        mInitParams.setMode(ECInitParams.LoginMode.FORCE_LOGIN);
        if (mInitParams.validate()) {
            /*登陆*/
            ECDevice.login(mInitParams);
        } else {
            Log.i("", "参数不对");
        }
    }

    /*初始化失败*/
    @Override
    public void onError(Exception exception) {
        L.i("初始化失败");
        getInstance().mConnect = ECDevice.ECConnectState.CONNECT_FAILED;
        if (true) {
            reConnect();
        } else {
            stopReConnect();
        }
        Intent intent = new Intent(ACTION_SDK_CONNECT);
        intent.putExtra("error", ERROR_CODE_INIT);
        if (mContext != null) {
            mContext.sendBroadcast(intent);
        }
        ECDevice.unInitial();
    }

    /*IM 聊天功能接口*/
    public static ECChatManager getECChatManager() {
        ECChatManager ecChatManager = ECDevice.getECChatManager();
        L.d("ecChatManager :" + ecChatManager);
        return ecChatManager;
    }

    /*多少秒重连一次*/
    private long time = 1 * 60 * 1000;
    private Timer mTimer;

    /*开始连接*/
    public synchronized void startConnect() {
        L.i("开始连接");
        stopReConnect();
        init(getInstance().chatToken);
    }

    private synchronized void reConnect() {
        L.i("重连");
        if (mTimer != null) {
            return;
        }
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                startConnect();
            }
        };
        if (mTimer == null) {
            mTimer = new Timer();
        }
        mTimer.schedule(task, time);//等待60s后去执行一次
    }

    private synchronized void stopReConnect() {
        if (mTimer != null) {
            L.i("停止重连");
            mTimer.cancel();
            mTimer = null;
        }
    }

    @Override
    public void onConnect() {
        L.i("");
        // 兼容4.0，5.0可不必处理
    }

    @Override
    public void onDisconnect(ECError error) {
        L.i("");
        // 兼容4.0，5.0可不必处理
    }

    /*登陆容云，断线，重连失败的回调，，*/
    /*1.不在线 :  a.被踢下线,发广播,给提示, b.自动断线,重连.  2.在线,停止重连*/
    @Override
    public void onConnectState(ECDevice.ECConnectState state, ECError error) {
        L.i("连接状态");
        getInstance().mConnect = state;
        if (state == ECDevice.ECConnectState.CONNECT_FAILED) {
            L.i("");
            if (error.errorCode == SdkErrorCode.SDK_KICKED_OFF) {
                L.i("");
                //账号异地登陆
                mKickOff = true;
                Intent intent = new Intent(ACTION_KICK_OFF);
                mContext.sendBroadcast(intent);
            } else {
                L.i("");
                //连接状态失败
                reConnect();
            }
        } else if (state == ECDevice.ECConnectState.CONNECT_SUCCESS) {
            L.i("登录成功，停止重连");
            stopReConnect();
        }
    }


    /*退出登陆的回掉*/
    @Override
    public void onLogout() {
        L.i("退出登陆的回掉");
        getInstance().mConnect = ECDevice.ECConnectState.CONNECT_FAILED;
        stopReConnect();
        if (mInitParams != null && mInitParams.getInitParams() != null) {
            mInitParams.getInitParams().clear();
        }
        mInitParams = null;
        ECDevice.unInitial();
        if (mContext != null) {
            /*退出登录的广播*/
            mContext.sendBroadcast(new Intent(ACTION_LOGOUT));
        }
    }


    /*推出登陆*/
    public static void logout() {
        L.i("推出登陆");
        getInstance().stopReConnect();
        if (getInstance().mContext != null) {
            ECDevice.logout(getInstance());
            release();
        }
    }

    /*推出登录，做释放工作*/
    public static void release() {
        getInstance().mKickOff = false;
        IMChattingHelper.getInstance().destroy();
//        ContactSqlManager.reset();
//        ConversationSqlManager.reset();
//        GroupMemberSqlManager.reset();
//        GroupNoticeSqlManager.reset();
//        GroupSqlManager.reset();
//        IMessageSqlManager.reset();
//        ImgInfoSqlManager.reset();
    }

    /**
     * 判断服务是否自动重启，也就是是否初始化了
     * @return 是否自动重启
     */
    public static boolean isUIShowing() {
        return ECDevice.isInitialized();
    }
}
