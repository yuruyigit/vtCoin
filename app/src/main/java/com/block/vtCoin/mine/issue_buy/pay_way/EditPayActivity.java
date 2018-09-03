package com.block.vtCoin.mine.issue_buy.pay_way;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.net.util.L;
import com.block.vtCoin.R;
import com.block.vtCoin.main.BaseActivity;
import com.block.vtCoin.widget.dialog.password.PayPasswordDialog;
import com.block.vtCoin.util.PermissionsUtils;
import com.block.vtCoin.util.PhotoHelper;
import com.block.vtCoin.widget.dialog.ListViewDialog;
import com.block.vtCoin.widget.base.OnMyItemClickListener;
import com.block.vtCoin.widget.title.NormalTitleBar;
import java.io.File;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class EditPayActivity extends BaseActivity {
    @Bind(R.id.pay_name)
    TextView payName;
    @Bind(R.id.rl_pay_way)
    RelativeLayout rlPayWay;
    @Bind(R.id.address)
    EditText address;
    @Bind(R.id.account)
    EditText account;
    @Bind(R.id.user)
    EditText user;
    @Bind(R.id.phone)
    EditText phone;
    @Bind(R.id.name)
    EditText name;
    @Bind(R.id.code)
    ImageView code;
    private View aliView, wxView, bankView;
    private String initWay;
    private PayPasswordDialog payPasswordDialog;
    private ListViewDialog photoDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pay);
        setUltimateBar(R.color.blue1);
        initView();
    }

    private void initView() {
        aliView = findViewById(R.id.alipay_view);
        wxView = findViewById(R.id.weixin_view);
        bankView = findViewById(R.id.bank_view);
        initWay = getIntent().getStringExtra("initWay");
        showView(initWay);
        payPasswordDialog = new PayPasswordDialog(this);
    }

    private void showView(String initWay) {
        if(null==initWay)
            initWay="";
        if (initWay.equals("微信")) {
            aliView.setVisibility(View.GONE);
            wxView.setVisibility(View.VISIBLE);
            bankView.setVisibility(View.GONE);
            payName.setText(initWay);
        } else if (initWay.equals("支付宝")) {
            aliView.setVisibility(View.VISIBLE);
            wxView.setVisibility(View.GONE);
            bankView.setVisibility(View.GONE);
            payName.setText(initWay);
        } else {
            aliView.setVisibility(View.GONE);
            wxView.setVisibility(View.GONE);
            bankView.setVisibility(View.VISIBLE);
            payName.setText(initWay);
        }
    }

    /**
     * @return
     */
    @Override
    public View getTitleBar() {
        NormalTitleBar normalTitleBar = getNormalTitleBar().setTitle(getString(R.string.edit_way));
        normalTitleBar.setRightText(getString(R.string.save));
        normalTitleBar.setRightListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
        return normalTitleBar;
    }

    public void save() {
        showPaypassword();
    }

    public void showPaypassword() {
        payPasswordDialog.show();
        payPasswordDialog.setOnPasswordChangedListener(new PayPasswordDialog.OnPasswordChangedListener() {
            @Override
            public void onTextChanged(String psw) {
            }

            @Override
            public void onInputFinish(String psw) {
                ShowTip();
            }
        });
    }

    public void ShowTip() {
        payPasswordDialog.clear();
        payPasswordDialog.dismiss();
        showTip("输入密码完成");
        String addName = payName.getText().toString().trim();
        Intent intent = getIntent();
        intent.putExtra("addName", addName);
        this.setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    protected List<BasePresenter> getPresenter(List<BasePresenter> presenters) {
        return null;
    }


    @OnClick({R.id.rl_pay_way, R.id.up_code, R.id.code})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_pay_way:
                Intent intent = new Intent(this, PayWayActivity1.class);
                startActivityForResult(intent, RESULT_FIRST_USER);
                break;
            case R.id.up_code:
                showPopup();
                break;
            case R.id.code:
                Intent it = new Intent(this, ShowImageActivity.class);
                it.putExtra("imagePath", imgPath1);
                startActivityForResult(it, SHOW_IMAGE_CODE);
                break;
        }
    }

    private void showPopup() {
        String[] items = new String[]{getString(R.string.take_pictures), getString(R.string.select_from_album)};
        if (photoDialog == null) {
            photoDialog = new ListViewDialog(this, items);
        }
        photoDialog.setOnItemClickListener(new OnMyItemClickListener() {
            @Override
            public void onItemClick(View parent, View view, int position) {
                if (position == 0) {//拍照
                    startPicture();
                } else if (position == 1) {//从相册选择
                    startAlbum();
                }
            }
        });
        photoDialog.show();
    }

    private Uri uri;
    /*原始大小*/
    private String imgPath0;
    /*剪切后的*/
    private String imgPath1;
    /*打开相机*/
    public static final int CAMERA_REQUEST_CODE = 1024;
    /*剪切图片*/
    public static final int CROP_CAMERA_PHOTO = 2048;
    /*从相册中选择照片*/
    public static final int GALLERY_REQUEST_CODE = 1023;
    /*点开图片显示*/
    public static final int SHOW_IMAGE_CODE = 1025;

    /**
     * 打开相机
     */
    private void startPicture() {
        PermissionsUtils.requestCamera(this, new Runnable() {
            @Override
            public void run() {
                uri = PhotoHelper.selectMyPhotoForCamera(EditPayActivity.this, null, CAMERA_REQUEST_CODE);
            }
        });
    }

    /**
     * 从相册中选择照片
     */
    private void startAlbum() {
        PermissionsUtils.requestReadWriteStorage(this, new Runnable() {
            @Override
            public void run() {
                PhotoHelper.selectMyPhotoFromGallery(EditPayActivity.this, GALLERY_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case RESULT_FIRST_USER://切换收款方式的回调
                    String chooseWay = data.getStringExtra("chooseWay");
                    showView(chooseWay);
                    break;
                case CAMERA_REQUEST_CODE://打开相机的回掉
                    imgPath0 = uri.toString();
                    imgPath1 = PhotoHelper.doCropPhoto(this, uri, CROP_CAMERA_PHOTO, true);
                    L.i("打开相机的回掉，imagePath=" + imgPath0);
//                    showImage();
                    break;
                case GALLERY_REQUEST_CODE://从相册中选择图片的回掉
                    imgPath0 = data.getData().toString();
                    imgPath1 = PhotoHelper.doCropPhoto(this, data.getData(), CROP_CAMERA_PHOTO, true);
                    L.i("从相册中选择图片的回掉，imagePath=" + imgPath0);
//                    showImage();
                    break;
                case CROP_CAMERA_PHOTO:
                    L.i("裁剪完成，imagePath=" + imgPath1);
                    showImage();
                    break;
                case SHOW_IMAGE_CODE:
                    L.i("删除照片");
                    deleteImage();
                    break;
            }
        }
    }

    private void deleteImage() {
        if (initWay.trim().equals("微信")) {
            L.i("删除照片0");
            code.setImageDrawable(null);
            code.setClickable(false);
        }
    }

    private void showImage() {
        if (initWay.trim().equals("微信")) {
            code.setImageURI(Uri.fromFile(new File(imgPath1)));
            code.setClickable(true);
        }
    }

    @Override
    protected void onDestroy() {
        if (photoDialog != null)
            photoDialog.unbind();
        super.onDestroy();
    }
}
