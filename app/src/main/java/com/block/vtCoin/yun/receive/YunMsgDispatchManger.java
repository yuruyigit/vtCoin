package com.block.vtCoin.yun.receive;

import com.yuntongxun.ecsdk.ECMessage;
import com.yuntongxun.ecsdk.OnChatReceiveListener;
import com.yuntongxun.ecsdk.im.ECMessageNotify;
import com.yuntongxun.ecsdk.im.group.ECGroupNoticeMessage;

import java.util.List;

/**
 * 云推送自定义消息分发处理
 * Created by zdc on 2015/10/22.
 */
public class YunMsgDispatchManger implements OnChatReceiveListener {

	private static YunMsgDispatchManger sInstance;
    public static YunMsgDispatchManger getInstance(){
        if(sInstance == null) {
            sInstance = new YunMsgDispatchManger();
        }
        return sInstance;
    }
    
//    /**
//     * 处理接收消息
//     * @param msg
//     */
//    private synchronized void postReceiveMessage(ECMessage msg , MessageBean mBean, String userData) {
//    	if (msg == null || msg.getBody() == null || mBean == null || mBean.getData() == null) {
//    		return;
//    	}
//    	switch (0) {
//    	//:1：微信-->app发送消息推送
//		case 1:
//		//2：app-->微信发送消息
//		case 2:
//		//2：app-app
//		case 3:
//		//3：app动态推送
//		case 4:
//		//接收图文
//		case 5:
//			ChatMsgManger.getInstance().sendDataProcess(mBean.getData());
//			break;
//		//系统后台-->app推送消息
//		case 6:
//			ChatMsgManger.getInstance().serverPushMsg(mBean.getData());
//			break;
//		//7： 导购端app -->消费端app
//		case 7:
//			DealerMessageBean dealerBean = HttpUtil.toBean(DealerMessageBean.class, userData);
//			DLog.i("yunmsg", "-------->postReceiveMessage DealerMessageBean " + dealerBean);
//
//			if (dealerBean == null) return;
//
//			DLog.i("yunmsg", "-------->postReceiveMessage data " + dealerBean.getData());
//			DealerMessageData msgData = dealerBean.getData();
//
//			if (msgData == null) return;
//
//			int msgType = msgData.getMsgType();
//			DLog.d("yunmsg", "--------导购端app -->消费端app category:" + category + " _ msgType:"+ msgType);
//
//			if (msgType == 1) {
//				if (msg.getBody() instanceof ECTextMessageBody) {
//					msgData.setDescription(((ECTextMessageBody)msg.getBody()).getMessage());
//				}
//
//				ChatMsgManger.getInstance().msgDataProcess(msgData);
//			} else if (msgType == 4) {
//				if (msg.getBody() instanceof ECImageMessageBody) {
//					msgData.setDescription(((ECImageMessageBody)msg.getBody()).getRemoteUrl());
//				}
//
//				ChatMsgManger.getInstance().msgDataProcess(msgData);
//			} else if (msgType == 2) {
//				if (msg.getBody() instanceof ECVoiceMessageBody) {
//					msgData.setDescription(((ECVoiceMessageBody)msg.getBody()).getRemoteUrl());
//				}
//
//				ChatMsgManger.getInstance().msgDataProcess(msgData);
//			}
//			break;
//		case 8: //系统后台-->app 抢购提醒
//			ChatMsgManger.getInstance().sendDataNotice(mBean.getData(),KeyConstants.NOTICE_PANIC_BUYING);
//			break;
//		case 9: //系统后台-->app 降价提醒
//			ChatMsgManger.getInstance().sendDataNotice(mBean.getData(),KeyConstants.NOTICE_CUT_PRICE);
//			break;
//
//		default:
//			break;
//		}
//    }
//
//	@Override
//	public void OnReceivedMessage(ECMessage msg) {
//		DLog.i("yunmsg", "-------->OnReceivedMessage msg " + msg);
//		if(msg == null) {
//            return ;
//        }
//		String userData = msg.getUserData();
//		DLog.i("yunmsg", "-------->OnReceivedMessage userData " + userData);
//		if (userData == null) {
//			return;
//		}
//		MessageBean mBean = HttpUtil.toBean(MessageBean.class, userData);
//		DLog.i("yunmsg", "-------->OnReceivedMessage MessageBean " + mBean);
//		if (mBean == null) {
//			return;
//		}
//
//        postReceiveMessage(msg, mBean, userData);
//
//	}
//
//	@Override
//	public void onOfflineMessageCount(int count) {
//		DLog.i("yunmsg", "-------->onOfflineMessageCount count " + count);
//
//	}
//
//	@Override
//	public void onReceiveOfflineMessage(List<ECMessage> msgs) {
//		DLog.i("yunmsg", "-------->onReceiveOfflineMessage msgs size " + msgs.size());
//
//		for (ECMessage msg : msgs) {
//			OnReceivedMessage(msg);
//		}
//
//	}
//
//	@Override
//	public void onReceiveOfflineMessageCompletion() {
//		DLog.i("yunmsg", "-------->onReceiveOfflineMessageCompletion");
//
//	}
//
//	@Override
//	public int onGetOfflineMessage() {
//		DLog.i("yunmsg", "-------->onGetOfflineMessage");
//		return ECDevice.SYNC_OFFLINE_MSG_ALL;
//	}
//
//
//	public void destroy() {
//		DLog.i("yunmsg", "-------->destroy");
//	}

	@Override
	public void OnReceiveGroupNoticeMessage(ECGroupNoticeMessage notice) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onOfflineMessageCount(int i) {

	}

	@Override
	public int onGetOfflineMessage() {
		return 0;
	}

	@Override
	public void onReceiveOfflineMessage(List<ECMessage> list) {

	}

	@Override
	public void onReceiveOfflineMessageCompletion() {

	}

	@Override
	public void onReceiveDeskMessage(ECMessage msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onServicePersonVersion(int version) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSoftVersion(String version, int sUpdateMode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void OnReceivedMessage(ECMessage ecMessage) {

	}

	@Override
	public void onReceiveMessageNotify(ECMessageNotify arg0) {
		// TODO Auto-generated method stub
		
	}
	
	

}
