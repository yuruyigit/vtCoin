package com.block.vtCoin.yun.service;

import android.app.LauncherActivity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import com.block.net.util.L;
import com.block.vtCoin.yun.receive.SDKCoreHelper;
import com.block.vtCoin.yun.receive.YunMsgDispatchManger;
import com.yuntongxun.ecsdk.ECDevice;
import com.yuntongxun.ecsdk.ECMessage;
import com.yuntongxun.ecsdk.booter.ECNotifyReceiver;
import com.yuntongxun.ecsdk.im.ECMessageNotify;
import com.yuntongxun.ecsdk.im.group.ECGroupNoticeMessage;

/**
 * SDK通知会分三种提醒方式
 * <p>1、直接通过设置的回调接口（OnChatReceiveListener）Push给应用
 * <p>2、如果应用没有设置回调接口则采用（BroadcastReceiver）广播通知（v5.1.8版本）此时如果应用处于未运行状态则会直接唤醒应用处理
 * <p>3、如果应用未处于运行状态并且不想被唤醒应用运行则采用状态栏通知处理（SDK直接提醒，不会通知应用）,比如调用
 * {@link ECDevice#logout(ECDevice.NotifyMode, ECDevice.OnLogoutListener)}退出接口传入后台接收消息才会有提醒
 * @author 容联•云通讯
 * @version 5.1.8
 * @since 2015-10-23
 * Created by zdc on 2016/3/8.
 */
public class YuntxNotifyReceiver extends ECNotifyReceiver {

    public static final String SERVICE_OPT_CODE = "service_opt_code";
    public static final String MESSAGE_SUB_TYPE  = "message_type";

    /** 消息推送 */
    public static final int EVENT_TYPE_MESSAGE = 2;

    /**
     * 创建一个服务Intent
     * @return Intent
     */
    public Intent buildServiceAction(int optCode) {
    	L.i("yun>>YuntxRec buildServiceAction");
        Intent notifyIntent = new Intent(getContext(), NotifyService.class);
        notifyIntent.putExtra("service_opt_code" , optCode);
        return notifyIntent;
    }
    
  

    /**
     * 创建一个服务Intent
     * @return Intent
     */
    public Intent buildMessageServiceAction(int subOptCode) {
    	L.i("yun>>YuntxRec buildMessageServiceAction");
        Intent intent = buildServiceAction(EVENT_TYPE_MESSAGE);
        intent.putExtra(MESSAGE_SUB_TYPE, subOptCode);
        return intent;
    }

    @Override
    public void onReceivedMessage(Context context, ECMessage msg) {
    	L.i("yun>>YuntxRec onReceivedMessage");
        Intent intent = buildMessageServiceAction(OPTION_SUB_NORMAL);
        intent.putExtra(EXTRA_MESSAGE, msg);
        context.startService(intent);
    }

    @Override
    public void onCallArrived(Context context, Intent intent) {

    }

    @Override
    public void onReceiveGroupNoticeMessage(Context context, ECGroupNoticeMessage notice) {
    	L.i("yun>>YuntxRec onReceiveGroupNoticeMessage");
        Intent intent = buildMessageServiceAction(OPTION_SUB_GROUP);
        intent.putExtra(EXTRA_MESSAGE , notice);
        context.startService(intent);
    }

    @Override
    public void onNotificationClicked(Context context, int notifyType, String sender) {
        L.i( "onNotificationClicked notifyType " + notifyType + " ,sender " + sender);
        Intent intent = new Intent(context, LauncherActivity.class);
        intent.putExtra("Main_Session", sender);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    @Override
    public void onReceiveMessageNotify(Context context, ECMessageNotify notify) {
    	L.i("ddhlyun", "yun>>YuntxRec onReceiveMessageNotify");
        Intent intent = buildMessageServiceAction(OPTION_SUB_MESSAGE_NOTIFY);
        intent.putExtra(EXTRA_MESSAGE , notify);
        context.startService(intent);
    }
    
    @Override
    public void onSoftVersion(Context context, String version , String softDesc, boolean force) {
    	L.i("yun>>YuntxRec onSoftVersion");
    	// TODO Auto-generated method stub
    	super.onSoftVersion(context, version, softDesc, force);
    	SDKCoreHelper.setSoftUpdate(version, force);
    }

    public static class NotifyService extends Service {
        public static final String TAG = "ECSDK_Demo.NotifyService";
        @Override
        public IBinder onBind(Intent intent) {
        	L.i( "yun>>NotifyService onBind");
            return null;
        }
        private void receiveImp(Intent intent) {
        	L.i("yun>>NotifyService receiveImp");
            if(intent == null) {
                L.e("receiveImp receiveIntent == null");
                return ;
            }
            L.i("intent:action " + intent.getAction());
            int optCode = intent.getIntExtra(SERVICE_OPT_CODE, 0);
            if(optCode == 0) {
                L.e("receiveImp invalid opcode.");
                return ;
            }
            if(!SDKCoreHelper.isUIShowing()) {
            }
            switch (optCode) {
                case EVENT_TYPE_MESSAGE:
                    dispatchOnReceiveMessage(intent);
                    break;
            }
        }

        // Android Version 2.0以下版本
        @Override
        public void onStart(Intent intent, int startId) {
        	L.i("yun>>NotifyService onStart");
            super.onStart(intent, startId);
            if(Build.VERSION.SDK_INT < Build.VERSION_CODES.ECLAIR) {
                receiveImp(intent);
            }
        }

        // Android 2.0以上版本回调/同时会执行onStart方法
        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
        	L.i( "yun>>NotifyService onStartCommand");
            L.v(String.format("onStartCommand flags :%d startId :%d intent %s", flags, startId, intent));
            receiveImp(intent);
            return super.onStartCommand(intent, flags, startId);
        }


        private void dispatchOnReceiveMessage(Intent intent) {
        	L.i("yun>>NotifyService dispatchOnReceiveMessage");
            if(intent == null) {
                L.i("dispatch receive message fail.");
                return ;
            }
            int subOptCode = intent.getIntExtra(MESSAGE_SUB_TYPE , -1);
            if(subOptCode == -1) {
                return ;
            }
            switch (subOptCode){
                case OPTION_SUB_NORMAL:
                    ECMessage message = intent.getParcelableExtra(EXTRA_MESSAGE);
                    YunMsgDispatchManger.getInstance().OnReceivedMessage(message);
                    break;
                case OPTION_SUB_GROUP:
                    ECGroupNoticeMessage notice = intent.getParcelableExtra(EXTRA_MESSAGE);
                    YunMsgDispatchManger.getInstance().OnReceiveGroupNoticeMessage(notice);
                    break;
                case OPTION_SUB_MESSAGE_NOTIFY:
                    ECMessageNotify notify = intent.getParcelableExtra(EXTRA_MESSAGE);
                    YunMsgDispatchManger.getInstance().onReceiveMessageNotify(notify);
                    break;
            }
        }
    }
}
