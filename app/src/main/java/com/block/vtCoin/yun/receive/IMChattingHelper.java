package com.block.vtCoin.yun.receive;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.widget.ImageView;

import com.block.net.util.L;
import com.yuntongxun.ecsdk.ECChatManager;
import com.yuntongxun.ecsdk.ECDevice;
import com.yuntongxun.ecsdk.ECError;
import com.yuntongxun.ecsdk.ECMessage;
import com.yuntongxun.ecsdk.ECMessage.Type;
import com.yuntongxun.ecsdk.ECMessageBody;
import com.yuntongxun.ecsdk.OnChatReceiveListener;
import com.yuntongxun.ecsdk.SdkErrorCode;
import com.yuntongxun.ecsdk.im.ECFileMessageBody;
import com.yuntongxun.ecsdk.im.ECImageMessageBody;
import com.yuntongxun.ecsdk.im.ECMessageDeleteNotify;
import com.yuntongxun.ecsdk.im.ECMessageNotify;
import com.yuntongxun.ecsdk.im.ECMessageNotify.NotifyType;
import com.yuntongxun.ecsdk.im.ECMessageReadNotify;
import com.yuntongxun.ecsdk.im.ECMessageRevokeNotify;
import com.yuntongxun.ecsdk.im.ECPreviewMessageBody;
import com.yuntongxun.ecsdk.im.ECTextMessageBody;
import com.yuntongxun.ecsdk.im.ECUserStateMessageBody;
import com.yuntongxun.ecsdk.im.ECVideoMessageBody;
import com.yuntongxun.ecsdk.im.ECVoiceMessageBody;
import com.yuntongxun.ecsdk.im.friend.ECFriendMessageBody;
import com.yuntongxun.ecsdk.im.friend.FriendInner;
import com.yuntongxun.ecsdk.im.group.ECGroupNoticeMessage;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 一对一聊天的接口
 */
public class IMChattingHelper implements OnChatReceiveListener, ECChatManager.OnDownloadMessageListener {

    private static final String TAG = "IMChattingHelper";
    public static final String INTENT_ACTION_SYNC_MESSAGE = "com.yuntongxun.ecdemo_sync_message";
    public static final String INTENT_ACTION_CHAT_USER_STATE = "com.yuntongxun.ecdemo_chat_state";
    public static final String INTENT_ACTION_CHAT_EDITTEXT_FOUCU = "com.yuntongxun.ecdemo_chat_edit_foucs";
    public static final String INTENT_ACTION_ADD_GROUP_MEMBER = "com.yuntongxun.ecdemo_INTENT_ACTION_ADD_GROUP_MEMBER";
    public static final String INTENT_ACTION_CHANGE_ADMIN = "com.yuntongxun.ecdemo_INTENT_ACTION_CHANGE_ADMIN";
    public static final String ACTION_TRANS_OWNER = "com.yuntonxun.ecdemo.ACTION_TRANS_OWNER";
    public static final String USER_STATE = "chat_state";
    public static final String GROUP_PRIVATE_TAG = "@priategroup.com";
    private static HashMap<String, SyncMsgEntry> syncMessage = new HashMap<String, SyncMsgEntry>();
    private static IMChattingHelper sInstance;
    private boolean isSyncOffline = false;

    public static IMChattingHelper getInstance() {
        if (sInstance == null) {
            sInstance = new IMChattingHelper();
        }
        return sInstance;
    }

    /**
     * 云通讯SDK聊天功能接口
     */
    private ECChatManager mChatManager;
    /**
     * 全局处理所有的IM消息发送回调
     */
    private ChatManagerListener mListener;
    /**
     * 是否是同步消息
     */
    private boolean isFirstSync = false;

    private IMChattingHelper() {
        initManager();
        mListener = new ChatManagerListener();
    }

    public void initManager() {
        mChatManager = SDKCoreHelper.getECChatManager();
    }

    /**
     * 发送ECMessage 消息
     */
    public static void sendECMessage(ECMessage msg) {
        L.i("发送ECMessage 消息");
        //初始化聊天管理器
        getInstance().initManager();
        // 获取一个聊天管理器
        ECChatManager manager = getInstance().mChatManager;
        if (manager != null) {
            msg.setMsgTime(System.currentTimeMillis());
            manager.sendMessage(msg, getInstance().mListener);
        } else {
            msg.setMsgStatus(ECMessage.MessageStatus.FAILED);
        }
    }

    public void destroy() {
        if (syncMessage != null) {
            syncMessage.clear();
        }
        mListener = null;
        mChatManager = null;
        isFirstSync = false;
        sInstance = null;
    }

    public static void sendImageMessage(ECMessage message) {
        ECChatManager manager = getInstance().mChatManager;
        if (manager != null) {
            manager.sendMessage(message, getInstance().mListener);
        }
    }


    private class ChatManagerListener implements ECChatManager.OnSendMessageListener {
        @Override
        public void onSendMessageComplete(ECError error, ECMessage message) {
            L.i("处理消息发送结果" + error.errorCode);
            L.i("处理消息发送结果:  from=" + message.getForm() + "   to=" + message.getTo() + "   body=" + message.getBody().toString());
            ECMessageBody body = message.getBody();
            if (message == null) {
                L.i("");
                return;
            }
            if (error == null) {
                L.i("");
                return;
            }
            if (error.errorCode == SdkErrorCode.REQUEST_SUCCESS) {
                L.i("发送消息成功");
            }

            if ((SdkErrorCode.SPEAK_LIMIT_FILE == error.errorCode || SdkErrorCode.SPEAK_LIMIT_TEXT == error.errorCode)) {
                // 成员被禁言
                return;
            }
            if ((SdkErrorCode.NON_GROUPMEMBER == error.errorCode)) {
                // 文件上传发送者不在群组内
                return;
            }

            if (SdkErrorCode.SDK_TEXT_LENGTH_LIMIT == error.errorCode) {
                // 文本长度超过限制
            }
            // 将发送的消息更新到本地数据库并刷新UI
            // 处理ECMessage的发送状态
        }

        @Override
        public void onProgress(String msgId, int total, int progress) {
            // 处理发送文件IM消息的时候进度回调
            L.i("进度回调,progress=" + progress*100 / total);
        }
    }

    /**
     * 消息发送报告
     */
    private OnMessageCallback mOnMessageCallback;

    public static void setOnMessageCallback(OnMessageCallback callback) {
        getInstance().mOnMessageCallback = callback;
    }

    public interface OnMessageCallback {
        void onTextMessage(String text);

        void onImageMessage(String imagePath);
    }

    /**
     * 收到新的IM文本和附件消息
     */
    @Override
    public void OnReceivedMessage(ECMessage msg) {
        if (msg == null) {
            return;
        }
        L.i("收到新的IM文本和附件消息:  from=" + msg.getForm() + "   to=" + msg.getTo() + "   body=" + msg.getBody().toString());
        postReceiveMessage(msg, !msg.isNotify());
    }

    /**
     * 处理接收消息
     */
    private synchronized void postReceiveMessage(ECMessage msg, boolean showNotice) {
        // 接收到的IM消息，根据IM消息类型做不同的处理
        // IM消息类型：ECMessage.Type
        if (msg.getType() == Type.FRIEND) {//好友消息
            L.i("好友消息");
            ECFriendMessageBody body = (ECFriendMessageBody) msg.getBody();
            if (body != null) {
                FriendInner inner = body.getInner();
                String userId = msg.getForm();
                String nick = msg.getNickName();
                String content = inner.getMsg();
                String to = msg.getTo();
                ECFriendMessageBody.FriendNoticeType type = inner.getSubType();
                if (type == ECFriendMessageBody.FriendNoticeType.IS_FRIENDLY) {//直接添加通过了好友
                } else if (type == ECFriendMessageBody.FriendNoticeType.DELETE_FRIEND) {//删除好友
                } else if (type == ECFriendMessageBody.FriendNoticeType.ADD_FRIEND) {//添加好友请求
                } else if (type == ECFriendMessageBody.FriendNoticeType.AGREE_ADD_FRIEND) {//同意好友
                } else if (type == ECFriendMessageBody.FriendNoticeType.REFUSE_ADD_FRIEND) {//拒绝好友
                }
            }
            return;
        } else if (msg.getType() == Type.STATE) { //状态消息
            L.i("状态消息");
            String msgTo = msg.getTo();
            ECUserStateMessageBody stateBody = (ECUserStateMessageBody) msg.getBody();
            String state = stateBody.getMessage();
            Intent intent = new Intent();
            intent.putExtra(USER_STATE, state);
            intent.setAction(INTENT_ACTION_CHAT_USER_STATE);
            //通知用户状态
            return;
        }else if (msg.isMultimediaBody()) {//如果是多媒体消息内容（带附件）
            L.i("如果是多媒体消息内容");
            if (msg.getType() != Type.CALL) {//呼叫类型
                L.i("呼叫类型");
                ECFileMessageBody body = (ECFileMessageBody) msg.getBody();
                if (!TextUtils.isEmpty(body.getRemoteUrl())) {
                    boolean thumbnail = false;
                    if (msg.getType() == ECMessage.Type.VOICE) {
                        L.i("语音消息");
                    } else if (msg.getType() == ECMessage.Type.IMAGE) {
                        L.i("图片消息");
                        // 在这里处理图片消息
                        ECImageMessageBody imageMsgBody = (ECImageMessageBody) msg.getBody();
                        // 获得缩略图地址
                        String thumbPath = imageMsgBody.getThumbnailFileUrl();
                        // 获得原图地址
                        String remotePath = imageMsgBody.getRemoteUrl();
                        L.i("原图="+remotePath+"\n缩略图="+thumbPath);
                        if (mOnMessageCallback != null) {
                            mOnMessageCallback.onImageMessage(remotePath);
                        }
                    } else if (msg.getType() == Type.TXT) {
                        L.i("文本");
                        ECTextMessageBody textMessageBody = (ECTextMessageBody) msg.getBody();
                        textMessageBody.getMessage();
                        if (mOnMessageCallback != null) {
                            mOnMessageCallback.onTextMessage(textMessageBody.getMessage());
                        }
                    } else if (msg.getType() == Type.RICH_TEXT) {//富文本
                        L.i("富文本");
                        ECPreviewMessageBody previewMessageBody = (ECPreviewMessageBody) body;
                        thumbnail = !TextUtils.isEmpty(previewMessageBody.getThumbnailFileUrl());
                    } else {
                        if (msg.getBody() instanceof ECVideoMessageBody) {
                            ECVideoMessageBody videoBody = (ECVideoMessageBody) body;

                            thumbnail = !TextUtils.isEmpty(videoBody
                                    .getThumbnailUrl());
                            StringBuilder builder = new StringBuilder(
                                    videoBody.getFileName());
                            builder.append("_thum.png");

                        } else {
                        }
                        if (syncMessage != null) {
                            syncMessage.put(msg.getMsgId(), new SyncMsgEntry(
                                    showNotice, thumbnail, msg));
                        }
                        if (mChatManager != null) {
                            if (thumbnail) {
                                mChatManager.downloadThumbnailMessage(msg, this);
                            } else {
                                mChatManager.downloadMediaMessage(msg, this);
                            }
                        }
                    }
                }
            }
        }
    }


    @Override
    public void OnReceiveGroupNoticeMessage(ECGroupNoticeMessage notice) {
        L.i("");
        if (notice == null) {
            return;
        }
        // 接收到的群组消息，根据群组消息类型做不同处理
        // 群组消息类型：ECGroupMessageType
    }

    private int mHistoryMsgCount = 0;

    @Override
    public void onOfflineMessageCount(int count) {
        L.i("下线消息数量count=" + count);
        mHistoryMsgCount = count;
    }

    @Override
    public int onGetOfflineMessage() {
        L.i("");
        // 获取全部的离线历史消息
        return ECDevice.SYNC_OFFLINE_MSG_ALL;
    }

    private ECMessage mOfflineMsg = null;

    @Override
    public void onReceiveOfflineMessage(List<ECMessage> msgs) {
        for (ECMessage msg : msgs) {
            L.i("下线消息：from=" + msg.getForm() + "   to=" + msg.getTo() + "   body=" + msg.getBody().toString());
        }
        // 离线消息的处理可以参考 void OnReceivedMessage(ECMessage msg)方法
        // 处理逻辑完全一样
        // 参考 IMChattingHelper.java
        if (msgs != null && !msgs.isEmpty() && !isFirstSync)
            isFirstSync = true;
        for (ECMessage msg : msgs) {
            mOfflineMsg = msg;
            postReceiveMessage(msg, false);
        }
    }

    @Override
    public void onReceiveOfflineMessageCompletion() {
        L.i("");
        if (mOfflineMsg == null) {
            return;
        }
        // SDK离线消息拉取完成之后会通过该接口通知应用
        // 应用可以在此做类似于Loading框的关闭，Notification通知等等
        // 如果已经没有需要同步消息的请求时候，则状态栏开始提醒
        ECMessage lastECMessage = mOfflineMsg;
        try {
            if (lastECMessage != null && mHistoryMsgCount > 0 && isFirstSync) {
                // lastECMessage.setSessionId(lastECMessage.getTo().startsWith("G")?lastECMessage.getTo():lastECMessage.getForm());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        isFirstSync = isSyncOffline = false;
        // 无需要同步的消息
        mOfflineMsg = null;
    }

    public int mServicePersonVersion = 0;

    @Override
    public void onServicePersonVersion(int version) {
        L.i("version=" + version);
        mServicePersonVersion = version;
    }

    /**
     * 客服消息
     */
    @Override
    public void onReceiveDeskMessage(ECMessage msg) {
        L.i("");
        OnReceivedMessage(msg);
    }


    @Override
    public void onSoftVersion(String version, int sUpdateMode) {
        L.i("");
        // Deprecated
    }

    public static boolean isSyncOffline() {
        return getInstance().isSyncOffline;
    }

    /**
     * 下载
     */
    @Override
    public void onDownloadMessageComplete(ECError e, ECMessage message) {
        L.i("");
        if (e.errorCode == SdkErrorCode.REQUEST_SUCCESS) {
            if (message == null)
                return;
            // 处理发送文件IM消息的时候进度回调
            postDowloadMessageResult(message);
        } else {
            // 重试下载3次
            SyncMsgEntry remove = syncMessage.remove(message.getMsgId());
            if (remove == null) {
                return;
            }
            retryDownload(remove);
        }
    }

    @Override
    public void onProgress(String msgId, int totalByte, int progressByte) {
        L.i("");
        // 处理发送文件IM消息的时候进度回调 //download
        L.d("[IMChattingHelper - onProgress] msgId: " + msgId
                + " , totalByte: " + totalByte + " , progressByte:"
                + progressByte);

    }

    /**
     * 重试下载3次
     *
     * @param entry
     */
    private void retryDownload(SyncMsgEntry entry) {
        if (entry == null || entry.msg == null || entry.isRetryLimit()) {
            return;
        }
        entry.increase();
        // download ..
        if (mChatManager != null) {
            if (entry.thumbnail) {
                mChatManager.downloadThumbnailMessage(entry.msg, this);
            } else {
                mChatManager.downloadMediaMessage(entry.msg, this);
            }
        }
        syncMessage.put(entry.msg.getMsgId(), entry);
    }

    private synchronized void postDowloadMessageResult(ECMessage message) {
        if (message == null) {
            return;
        }
        if (message.getType() == ECMessage.Type.VOICE) {
            ECVoiceMessageBody voiceBody = (ECVoiceMessageBody) message
                    .getBody();
        } else if (message.getType() == ECMessage.Type.IMAGE) {
        }
    }

    public class SyncMsgEntry {
        // 是否是第一次初始化同步消息
        boolean showNotice = false;
        boolean thumbnail = false;

        // 重试下载次数
        private int retryCount = 1;
        ECMessage msg;

        public SyncMsgEntry(boolean showNotice, boolean thumbnail,
                            ECMessage message) {
            this.showNotice = showNotice;
            this.msg = message;
            this.thumbnail = thumbnail;
        }

        public void increase() {
            retryCount++;
        }

        public boolean isRetryLimit() {
            return retryCount >= 3;
        }
    }

    @Override
    public void onReceiveMessageNotify(ECMessageNotify msg) {
        L.i("");
        if (msg.getNotifyType() == NotifyType.DELETE) {
            ECMessageDeleteNotify deleteMsg = (ECMessageDeleteNotify) msg;
        } else if (msg.getNotifyType() == NotifyType.REVOKE) {
            ECMessageRevokeNotify revokeNotify = (ECMessageRevokeNotify) msg;
        } else if (msg.getNotifyType() == NotifyType.READ) {
            ECMessageReadNotify readNotify = (ECMessageReadNotify) msg;
            if (readNotify.getSessionId().toLowerCase().startsWith("g")) {
            }
        }
    }
}
