package com.block.vtCoin.mine.deal_recode.buy_deal_recode.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.vtCoin.R;
import com.block.vtCoin.main.BaseActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * @Description
 * @Author lijie
 * @Date 2017/8/23.
 */
public class BuyDealDetailActivity extends BaseActivity {
    @Bind(R.id.tv_trader)
    TextView tvTrader;
    @Bind(R.id.tv_fee)
    TextView tvFee;
    @Bind(R.id.tv_price)
    TextView tvPrice;
    @Bind(R.id.tv_amount)
    TextView tvAmount;
    @Bind(R.id.tv_pay_way)
    TextView tvPayWay;
    @Bind(R.id.tv_fee_rate)
    TextView tvFeeRate;
    @Bind(R.id.tv_total_price)
    TextView tvTotalPrice;
    @Bind(R.id.tv_trade_time)
    TextView tvTradeTime;
    @Bind(R.id.tv_show_review)
    TextView tvShowReview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_deal_detail);
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
        return getNormalTitleBar().setTitle(getString(R.string.deal_detail));
    }

    @OnClick(R.id.tv_show_review)
    public void onClick() {
        toActivity(EvaluatedetailActivity.class);
    }
}
