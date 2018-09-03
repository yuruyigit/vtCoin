package com.block.vtCoin.mine.buy_manager.fragment;

import android.os.Bundle;
import android.view.View;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.vtCoin.R;
import com.block.vtCoin.main.BaseActivity;

import java.util.List;

/**
 * @Description
 */
public class BuyDetailActivity extends BaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_detail);
        setUltimateBar(R.color.blue1);
        initView();
    }
    private void initView() {
    }

    @Override
    protected List<BasePresenter> getPresenter(List<BasePresenter> presenters) {
        return null;
    }

    @Override
    public View getTitleBar() {
            return getNormalTitleBar().setTitle(getString(R.string.buy_detail));
    }
}
