package com.block.vtCoin.mine.deal_recode;

import android.os.Bundle;
import android.view.View;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.vtCoin.R;
import com.block.vtCoin.main.BaseActivity;
import com.block.vtCoin.mine.deal_recode.buy_deal_recode.BuyDealActivity;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Description
 * @Author lijie
 * @Date 2017/8/23.
 */
public class DealRecodeActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal_recode);
        ButterKnife.bind(this);
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
        return getNormalTitleBar().setTitle(getString(R.string.deal_recode));
    }

    @OnClick({R.id.buy_recode, R.id.sale_recode})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buy_recode:
            toActivity(BuyDealActivity.class);
                break;
            case R.id.sale_recode:
                break;
        }
    }
}
