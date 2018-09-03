package com.block.vtCoin.mine.setting.phone_num;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.vtCoin.R;
import com.block.vtCoin.main.BaseActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Description
 * @Author lijie
 * @Date 2017/7/15.
 */
public class BindPhoneActivity extends BaseActivity {

    @Bind(R.id.tv_phone)
    TextView tvPhone;
    @Bind(R.id.change)
    TextView change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_phone);
        ButterKnife.bind(this);
        setUltimateBar(R.color.blue1);
        intView();
    }

    private void intView() {

    }


    @Override
    public View getTitleBar() {
        return getNormalTitleBar().setTitle(getString(R.string.bind_phone));
    }

    @Override
    protected List<BasePresenter> getPresenter(List<BasePresenter> presenters) {
        return null;
    }

    @OnClick(R.id.change)
    public void onClick() {
        toActivity(ChangePhoneNumActivity.class);
    }
}
