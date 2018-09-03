package com.block.vtCoin.mine.user;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.net.util.L;
import com.block.vtCoin.R;
import com.block.vtCoin.constant.ApolloAction;
import com.block.vtCoin.entity.PersonInfoEntity;
import com.block.vtCoin.entity.SmsEntity;
import com.block.vtCoin.login.phone_location.TelephoneLocationActivity;
import com.block.vtCoin.main.BaseActivity;
import com.block.vtCoin.mine.user.p.UploadImagePresenter;
import com.block.vtCoin.mine.user.v.UploadImageViewImpl;
import com.block.vtCoin.request.ApiAction;
import com.block.vtCoin.request.MyModel;
import com.block.vtCoin.util.AppManager;
import com.block.vtCoin.util.PhotoHelper;
import com.block.vtCoin.util.StringUtils;
import com.block.vtCoin.widget.dialog.PickPhotoDialog;
import com.block.vtCoin.widget.imageview.CircleImageView;
import com.bumptech.glide.Glide;
import com.lsxiao.apllo.annotations.Receive;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Description
 * @Author lijie
 * @Date 2017/7/15.
 */
public class UserManagerActivity extends BaseActivity {
    @Bind(R.id.iv_avatar)
    CircleImageView ivAvatar;
    @Bind(R.id.tv_area)
    TextView tvArea;
    @Bind(R.id.tv_nickname)
    TextView tvNickname;
    @Bind(R.id.tv_sign)
    TextView tvSign;
    @Bind(R.id.tv_language)
    TextView tvLanguage;
    @Bind(R.id.tv_login_history)
    TextView tvLoginHistory;
    private UploadImagePresenter uploadImagePresenter;
    private String imgPath;
    private PickPhotoDialog pickPhotoDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_manager);
        ButterKnife.bind(this);
        setUltimateBar(R.color.blue1);
        setBackground(R.color.gray7);
        intView();
    }

    private void intView() {
        PersonInfoEntity.DataBean personInfo = AppManager.getPersonInfo();
        if(personInfo!=null){
            String imagePath = StringUtils.checkImagePath(personInfo.getLogo());
            Glide.with(this).load(imagePath).into(ivAvatar);
            tvNickname.setText(personInfo.getNickName());
            tvSign.setText(personInfo.getComment());
            tvArea.setText(personInfo.getCountry());
            if (personInfo.getLang().equals("en_US"))
                tvLanguage.setText(getString(R.string.English));
            else {
                tvLanguage.setText(getString(R.string.chinese));
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PickPhotoDialog.REQUEST_LOAD_PHOTO_CAMERA://拍摄图片
                    imgPath = PhotoHelper.doCropPhoto(UserManagerActivity.this, pickPhotoDialog.getUri(), PickPhotoDialog.REQUEST_PHOTO_CROP, true);
                    break;
                case PickPhotoDialog.REQUEST_LOAD_PHOTO_PICKED://从相册拿
                    imgPath = PhotoHelper.doCropPhoto(UserManagerActivity.this, data.getData(),
                            PickPhotoDialog.REQUEST_PHOTO_CROP, true);
                    break;
                case PickPhotoDialog.REQUEST_PHOTO_CROP:
                    ivAvatar.setImageBitmap(BitmapFactory.decodeFile(imgPath));
                    L.i("上传图片,imagePath=" + imgPath);
                    Map<String, String> map = new HashMap<>();
                    map.put("ImageType", "0");
                    String[] imagePaths = new String[1];
                    imagePaths[0] = imgPath;
                    /*打印图片格式*/
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inJustDecodeBounds = true;
                    BitmapFactory.decodeFile(imgPath, options);
                    String type = options.outMimeType;
                    L.i("type=" + type);
                    uploadImagePresenter.uploadImage(map, imagePaths);
                    break;
                case 1://选择地区
                    tvArea.setText(data.getStringExtra("city"));
                    break;
            }
        }
    }


    @Override
    public View getTitleBar() {
        return getNormalTitleBar().setTitle(getString(R.string.person_info));
    }

    @Override
    protected List<BasePresenter> getPresenter(List<BasePresenter> presenters) {
        uploadImagePresenter = new UploadImagePresenter();
        uploadImagePresenter.setModelView(new MyModel<SmsEntity>(), new UploadImageViewImpl(this, ApiAction.Preference_UpdateImage));
        presenters.add(uploadImagePresenter);
        return presenters;
    }

    @OnClick({R.id.rl_avatar, R.id.rl_nickname, R.id.rl_sign, R.id.rl_area, R.id.more_language, R.id.tv_login_history})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_avatar:
                if (pickPhotoDialog == null) {
                    pickPhotoDialog = new PickPhotoDialog(UserManagerActivity.this);
                }
                if (!pickPhotoDialog.isShow())
                    pickPhotoDialog.show();
                break;
            case R.id.rl_nickname:
                toActivity(NickNameActivity.class);
                break;
            case R.id.rl_sign:
                toActivity(SignNameActivity.class);
                break;
            case R.id.rl_area:
                Intent intent = new Intent(this, TelephoneLocationActivity.class);
                intent.putExtra("from", 1);
                startActivityForResult(intent, 1);
                break;
            case R.id.more_language:
                toActivity(ChooseLanguageActivity.class);
                break;
            case R.id.tv_login_history:
                toActivity(LoginHistoryActivity.class);
                break;
        }
    }

    @Receive(tag = ApolloAction.Update_NickName)
    public void updateNickName() {
        tvNickname.setText(AppManager.getPersonInfo().getNickName());
    }

    @Receive(tag = ApolloAction.Update_Sign)
    public void updateSign() {
        tvSign.setText(AppManager.getPersonInfo().getComment());
    }

    @Receive(tag = ApolloAction.Update_Language)
    public void updateLanguage() {
        tvLanguage.setText(AppManager.getPersonInfo().getLang());
    }
}
