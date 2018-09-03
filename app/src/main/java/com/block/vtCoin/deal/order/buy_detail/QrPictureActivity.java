package com.block.vtCoin.deal.order.buy_detail;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.vtCoin.R;
import com.block.vtCoin.main.BaseActivity;
import com.block.vtCoin.util.PermissionsUtils;
import com.block.vtCoin.util.PhotoHelper;
import com.block.vtCoin.widget.dialog.ListViewDialog;
import com.block.vtCoin.widget.base.OnMyItemClickListener;

import java.util.List;

import butterknife.Bind;

/**
 * @Description
 * @Author lijie
 * @Date 2017/8/26.
 */
public class QrPictureActivity extends BaseActivity {
    @Bind(R.id.image_qr)
    ImageView imageQr;
    private ListViewDialog savePhotoDialog;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_picture);
        setUltimateBar(R.color.blue1);
    }

    @Override
    protected List<BasePresenter> getPresenter(List<BasePresenter> presenters) {
        return null;
    }

    private void showDialog() {
        if(savePhotoDialog==null){
            String[] items=new String[]{getString(R.string.save_picture_pay)};
            savePhotoDialog = new ListViewDialog(this,items);
            savePhotoDialog.setOnItemClickListener(new OnMyItemClickListener() {
                @Override
                public void onItemClick(View parent, View view, int position) {
                    imageQr.setDrawingCacheEnabled(true);//开启catch，开启之后才能获取ImageView中的bitmap
                    bitmap = imageQr.getDrawingCache();//获取imageview中的图像
                    PermissionsUtils.requestReadWriteStorage(QrPictureActivity.this, new Runnable() {
                        @Override
                        public void run() {
                            if(PhotoHelper.saveImageToGallery(QrPictureActivity.this,bitmap)){
                                showTip("保存成功");
                            }
                        }
                    });
                }
            });
        }
        savePhotoDialog.show();
    }

    @Override
    public View getTitleBar() {
        return getNormalTitleBar().setTitle(getString(R.string.qr_picture)).setRightDrawable(R.mipmap.or_code_moree).setRightListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
    }
}
