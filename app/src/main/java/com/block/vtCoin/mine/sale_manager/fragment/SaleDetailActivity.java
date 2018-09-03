package com.block.vtCoin.mine.sale_manager.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.vtCoin.R;
import com.block.vtCoin.main.BaseActivity;
import com.block.vtCoin.widget.dialog.NormalDialog;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * @Description
 * @Author lijie
 * @Date 2017/8/23.
 */
public class SaleDetailActivity extends BaseActivity {
    @Bind(R.id.type)
    TextView type;
    @Bind(R.id.name)
    TextView name;
    @Bind(R.id.amount)
    TextView amount;
    @Bind(R.id.buy_number)
    TextView buyNumber;
    @Bind(R.id.need_number)
    TextView needNumber;
    @Bind(R.id.textView2)
    TextView textView2;
    @Bind(R.id.tv_currency)
    TextView tvCurrency;
    @Bind(R.id.tv_float_money)
    TextView tvFloatMoney;
    @Bind(R.id.tv_max_money)
    TextView tvMaxMoney;
    @Bind(R.id.tv_pay_way)
    TextView tvPayWay;
    @Bind(R.id.issue_time)
    TextView issueTime;
    @Bind(R.id.refresh_time)
    TextView refreshTime;
    @Bind(R.id.trader_ad)
    TextView traderAd;
    @Bind(R.id.tv_information)
    TextView tvInformation;
    @Bind(R.id.tv_deal)
    TextView tvDeal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_detail);
        setUltimateBar(R.color.blue1);
        initView();
    }

    private void initView() {
        tvInformation.setText(R.string.cancel_deal);
        tvDeal.setText(R.string.start_deal);
    }

    @Override
    protected List<BasePresenter> getPresenter(List<BasePresenter> presenters) {
        return null;
    }

    @Override
    public View getTitleBar() {
        return getNormalTitleBar().setTitle(getString(R.string.sale_detail));
    }

    @OnClick({R.id.tv_information, R.id.tv_deal})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_information:
                showDialog();
                break;
            case R.id.tv_deal:
                break;
        }
    }

    public void showDialog() {
        NormalDialog dialog = new NormalDialog(this, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTip("您已取消交易");
            }
        });
        dialog.setMsg(getString(R.string.tip_is_start_deal));
        dialog.setRightText(getString(R.string.setting_right_now));
        dialog.setLeftButtonColor(R.color.black4);
        dialog.show();
    }
}
