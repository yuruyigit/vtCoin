package com.block.vtCoin.mine.issue_buy.pay_way;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.net.util.L;
import com.block.vtCoin.R;
import com.block.vtCoin.main.BaseActivity;
import com.block.vtCoin.widget.title.NormalTitleBar;
import java.io.File;
import java.util.List;

import butterknife.Bind;

public class ShowImageActivity extends BaseActivity {

    @Bind(R.id.image)
    ImageView image;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);
        setUltimateBar(R.color.blue1);
        init();
    }

    private void init() {
         intent = getIntent();
        String imagePath = intent.getStringExtra("imagePath");
        L.i("imagePath="+imagePath);
        if (!imagePath.isEmpty()) {
            image.setImageURI(Uri.fromFile(new File(imagePath)));
        }
    }

    /**
     * @return
     */
    @Override
    public View getTitleBar() {
        NormalTitleBar normalTitleBar = getNormalTitleBar().setTitle(getString(R.string.qr_code)).setRightText(getString(R.string.delete));
        normalTitleBar.setRightListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK,intent);
                finish();
            }
        });
        return normalTitleBar;
    }

    @Override
    protected List<BasePresenter> getPresenter(List<BasePresenter> presenters) {
        return null;
    }
}
