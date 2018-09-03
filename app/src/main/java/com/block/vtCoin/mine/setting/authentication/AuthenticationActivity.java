package com.block.vtCoin.mine.setting.authentication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.net.util.L;
import com.block.vtCoin.R;
import com.block.vtCoin.main.BaseActivity;
import com.block.vtCoin.util.AppUtil;
import com.block.vtCoin.util.PhotoHelper;
import com.block.vtCoin.widget.dialog.ListViewDialog;
import com.block.vtCoin.widget.dialog.PickPhotoDialog;
import com.block.vtCoin.widget.base.OnMyItemClickListener;
import com.bumptech.glide.Glide;
import com.zhy.android.percent.support.PercentRelativeLayout;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * @Description
 * @Author lijie
 * @Date 2017/7/15.
 */
public class AuthenticationActivity extends BaseActivity {

    @Bind(R.id.tv_id_name)
    TextView tvIdName;
    @Bind(R.id.rl_id_type)
    PercentRelativeLayout rlIdType;
    @Bind(R.id.tv_change_login_password)
    TextView tvChangeLoginPassword;
    @Bind(R.id.iv_front_image)
    ImageView ivFrontImage;
    @Bind(R.id.iv_back_image)
    ImageView ivBackImage;
    @Bind(R.id.et_auth_name)
    EditText etAuthName;
    @Bind(R.id.et_id_num)
    EditText etIdNum;
    @Bind(R.id.commit)
    TextView commit;
    private PickPhotoDialog pickPhotoDialog;
    private boolean isFrontImage = true;
    private String imgPath;
    private ListViewDialog idTypeDialog;
    String[] datas;

    //    private String[] datas = {"全部", "BTC比特币", "LTC莱特币", "ETH以太坊", "VTC微特币", "ETC以太币", "SC区块盾"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        setUltimateBar(R.color.blue1);
        setBackground(R.color.gray7);
        /*默认不弹出软键盘*/
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        intView();
    }

    private void intView() {
        datas = new String[]{getString(R.string.authentication), getString(R.string.other_card), getString(R.string.taiwan_card), getString(R.string.back_card)};
    }

    @Override
    public View getTitleBar() {
        return getNormalTitleBar().setTitle(getString(R.string.authentication));
    }

    @Override
    protected List<BasePresenter> getPresenter(List<BasePresenter> presenters) {
        return null;
    }

    @OnClick({R.id.rl_id_type, R.id.iv_front_image, R.id.iv_back_image, R.id.commit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_id_type:
                showIdTypeDialog();
                break;
            case R.id.iv_front_image:
                isFrontImage = true;
                showPhotoDialog();
                break;
            case R.id.iv_back_image:
                isFrontImage = false;
                showPhotoDialog();
                break;
            case R.id.commit:
                showTip("认证成功");
                break;
        }
    }

    public void showIdTypeDialog() {
        if (idTypeDialog == null) {
            idTypeDialog = new ListViewDialog(this, datas);
            idTypeDialog.setOnItemClickListener(new OnMyItemClickListener() {
                @Override
                public void onItemClick(View parent, View view, int position) {
                    tvIdName.setText(datas[position]);
                }
            });
        }
        idTypeDialog.show();
    }

    public void showPhotoDialog() {
        if (null == pickPhotoDialog)
            pickPhotoDialog = new PickPhotoDialog(this);
        pickPhotoDialog.show();
    }

    @Override
    protected void onDestroy() {
        if (idTypeDialog != null)
            idTypeDialog.unbind();
        if (pickPhotoDialog != null)
            pickPhotoDialog.unbind();
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PickPhotoDialog.REQUEST_LOAD_PHOTO_CAMERA://拍照返回
                    if (pickPhotoDialog.getUri() != null) {
                        //file:///storage/emulated/0/pocket/Users/Face/cc0a585abee6433932fc0b89114dff81.jpg
                        L.i("photo" + pickPhotoDialog.getUri().toString());
                    }
                    showImage(pickPhotoDialog.getUri());
                    break;
                case PickPhotoDialog.REQUEST_LOAD_PHOTO_PICKED://相册返回
                    if (null != data) {
                        //content://media/external/images/media/23409
                        L.i("photo data" + data.getData().toString());
                        // /storage/emulated/0/pocket/Users/Face/b5d2e37a472a4e67bed2696953904504.jpg
                        L.i("photo imgpath" + PhotoHelper.UriToImagePath(this, data.getData()));
                    }
                    showImage(data.getData());
                    break;
            }
        }
    }

    /**
     * @param uri 用框架来加载，否在大图片的时候容易出现oom
     */
    public void showImage(Uri uri) {
        if (isFrontImage)
            Glide.with(this).load(uri).into(ivFrontImage);
        else
            Glide.with(this).load(uri).into(ivBackImage);
    }

    /**
     * @return 点击空白，软键盘隐藏
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            // 获得当前得到焦点的View，一般情况下就是EditText（特殊情况就是轨迹求或者实体案件会移动焦点）
            View v = getCurrentFocus();
            if (AppUtil.isShouldHideInput(v, ev)) {
                AppUtil.hideKeyboard(v);
            }
        }
        return super.dispatchTouchEvent(ev);
    }
}
