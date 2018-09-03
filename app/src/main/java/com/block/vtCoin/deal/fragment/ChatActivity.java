package com.block.vtCoin.deal.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.net.util.L;
import com.block.vtCoin.R;
import com.block.vtCoin.deal.fragment.P.ChatIdPresenter;
import com.block.vtCoin.deal.fragment.v.ChatIdViewImpl;
import com.block.vtCoin.entity.ChatIdEntity;
import com.block.vtCoin.main.BaseActivity;
import com.block.vtCoin.request.MyModel;
import com.block.vtCoin.util.PhotoHelper;
import com.block.vtCoin.widget.dialog.PickPhotoDialog;
import com.block.vtCoin.yun.receive.IMChattingHelper;
import com.bumptech.glide.Glide;
import com.yuntongxun.ecsdk.ECError;
import com.yuntongxun.ecsdk.ECMessage;
import com.yuntongxun.ecsdk.im.ECImageMessageBody;
import com.yuntongxun.ecsdk.im.ECTextMessageBody;

import java.io.File;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChatActivity extends BaseActivity {
    @Bind(R.id.user)
    TextView user;
    @Bind(R.id.et_message)
    EditText etMessage;
    @Bind(R.id.iv)
    ImageView iv;
    private ChatIdPresenter chatIdPresenter;
    private String chatId;
    private String sendMessage, getMessage;

    private PickPhotoDialog pickPhotoDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);
        setUltimateBar(R.color.blue1);
        init();

    }

    private void init() {
        String name = getIntent().getStringExtra("name");
        user.setText(name);
        chatIdPresenter.chatId(name);
        IMChattingHelper.setOnMessageCallback(new IMChattingHelper.OnMessageCallback() {
            @Override
            public void onTextMessage(String text) {
                L.i("收到文字消息="+text);
            }

            @Override
            public void onImageMessage(String imagePath) {
                L.i("收到图片="+imagePath);
                Glide.with(ChatActivity.this).load(imagePath).into(iv);
            }
        });
    }

    @Override
    protected List<BasePresenter> getPresenter(List<BasePresenter> presenters) {
        chatIdPresenter = new ChatIdPresenter();
        chatIdPresenter.setModelView(new MyModel<ChatIdEntity>(), new ChatIdViewImpl(this));
        presenters.add(chatIdPresenter);
        return presenters;
    }

    @Override
    public View getTitleBar() {
        return getNormalTitleBar().setTitle("聊天");
    }

    @OnClick({R.id.send_text, R.id.send_image})
    public void onClick(View view) {
        L.i("");
        switch (view.getId()) {
            case R.id.send_text:
                if (isOk) {
                    sendMessage = etMessage.getText().toString().trim();
                    if (!TextUtils.isEmpty(sendMessage)) {
                        sendTextMessage(sendMessage);
                    }
                } else
                    showTip("还没拿到对方id");
                break;
            case R.id.send_image:
                if (isOk) {
                    if (pickPhotoDialog == null) {
                        pickPhotoDialog = new PickPhotoDialog(this);
                    }
                    if (!pickPhotoDialog.isShow())
                        pickPhotoDialog.show();
                } else
                    showTip("还没拿到对方id");
                break;
        }
    }

    private String imgPath;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PickPhotoDialog.REQUEST_LOAD_PHOTO_CAMERA://拍摄图片
                    imgPath = PhotoHelper.getRealFilePath(this, pickPhotoDialog.getUri());
                    sendImageMessage(imgPath);
                    break;
                case PickPhotoDialog.REQUEST_LOAD_PHOTO_PICKED://从相册拿
                    Uri data1 = data.getData();
                    imgPath = PhotoHelper.getRealFilePath(this, data1);
                    sendImageMessage(imgPath);
                    break;
            }
        }
    }

    private void sendTextMessage(CharSequence text) {
        L.i("");
        // 组建一个待发送的ECMessage
        ECMessage msg = ECMessage.createECMessage(ECMessage.Type.TXT);
        // 设置消息接收者
        msg.setTo("15007253417");
        ECTextMessageBody msgBody = new ECTextMessageBody(text.toString());
        msg.setBody(msgBody);
        IMChattingHelper.sendECMessage(msg);
    }

    public void sendImageMessage(String filePath) {
        if (new File(filePath).exists()) {
            // 组建一个待发送的ECMessage
            ECMessage msg = ECMessage.createECMessage(ECMessage.Type.IMAGE);
            // 设置接收者
            msg.setTo("15007253417");
            // 设置附件包体（图片也是相当于附件）
            ECImageMessageBody msgBody = new ECImageMessageBody();
            //是否原图
            msgBody.setIsHPicture(true);
            // 设置附件名
            msgBody.setFileName("my");
            // 设置附件扩展名
            msgBody.setFileExt("image");
            // 设置附件本地路径
            msgBody.setLocalUrl(filePath);
            msg.setBody(msgBody);
            IMChattingHelper.sendImageMessage(msg);

        }
    }


    private boolean isOk = false;

    public void setChatId(ChatIdEntity entity) {
        if (entity != null) {
            chatId = entity.getData().getUserId();
            isOk = true;
        }
    }
}
