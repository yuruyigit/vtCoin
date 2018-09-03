package com.block.vtCoin.widget.dialog;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.block.vtCoin.R;
import com.block.vtCoin.util.PermissionsUtils;
import com.block.vtCoin.util.PhotoHelper;
import com.block.vtCoin.widget.dialog.base.DialogViewHolder;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * @Description
 * @Author lijie
 * @Date 2017/8/8.
 */
public class PickPhotoDialog extends DialogViewHolder {

    public static final int REQUEST_LOAD_PHOTO_PICKED = 101;//打开相册的请求码
    public static final int REQUEST_LOAD_PHOTO_CAMERA = 102;//打开相册拍照的请求码
    public static final int REQUEST_PHOTO_CROP = 103;//对图片进行裁剪的请求码
    @Bind(R.id.tvTitle)
    TextView tvTitle;
    @Bind(R.id.tvLeft)
    TextView tvLeft;
    @Bind(R.id.tvRight)
    TextView tvRight;
    private Uri uri;

    public PickPhotoDialog(Context context) {
        super(context);
        Window window = getWindow();
        window.setWindowAnimations(R.style.dialog_bottom_to_up);
    }

    @OnClick({R.id.tvLeft, R.id.tvRight, R.id.tvTitle})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvLeft:
                startPicture();
                break;
            case R.id.tvRight:
                startAlbum();
                break;
            case R.id.tvTitle:
                dismiss();
                break;
        }
    }

    /**
     * 从相册中选择照片
     */
    private void startAlbum() {
        dismiss();
        PermissionsUtils.requestReadWriteStorage((Activity) getContext(), new Runnable() {
            @Override
            public void run() {
                PhotoHelper.selectMyPhotoFromGallery((Activity) getContext(), REQUEST_LOAD_PHOTO_PICKED);
            }
        });

    }

    /**
     * 打开相机
     */
    private void startPicture() {
        dismiss();
        PermissionsUtils.requestCamera((Activity) getContext(), new Runnable() {
            @Override
            public void run() {
                uri = PhotoHelper.selectMyPForCamera((Activity) getContext(), null, REQUEST_LOAD_PHOTO_CAMERA);
            }
        });
    }

    @Override
    protected int getWidth() {
        return WindowManager.LayoutParams.MATCH_PARENT;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_user_photo;
    }


    @Override
    protected int getGravity() {
        return Gravity.BOTTOM;
    }

    public Uri getUri() {
        return uri;
    }
}
