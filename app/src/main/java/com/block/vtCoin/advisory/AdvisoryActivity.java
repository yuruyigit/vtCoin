package com.block.vtCoin.advisory;

import android.os.Bundle;
import android.view.View;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.vtCoin.R;
import com.block.vtCoin.main.BaseActivity;

import java.util.List;

public class AdvisoryActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advisory);
        setUltimateBar(R.color.blue1);
    }

    @Override
    protected List<BasePresenter> getPresenter(List<BasePresenter> presenters) {
        return null;
    }

    @Override
    public View getTitleBar() {
        return getNormalTitleBar().setTitle("新闻资讯");
    }
}
