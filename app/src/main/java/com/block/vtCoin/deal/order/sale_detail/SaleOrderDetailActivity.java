package com.block.vtCoin.deal.order.sale_detail;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.vtCoin.R;
import com.block.vtCoin.main.BaseActivity;
import com.block.vtCoin.widget.dialog.NormalDialog;
import com.block.vtCoin.deal.order.apply_detail.ApplyActivity;
import com.block.vtCoin.deal.order.buy_detail.EvaluateActivity;
import com.zhy.android.percent.support.PercentLinearLayout;
import com.zhy.android.percent.support.PercentRelativeLayout;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Description
 * @Author lijie
 * @Date 2017/8/24.
 */
public class SaleOrderDetailActivity extends BaseActivity {
    @Bind(R.id.order_number)
    TextView orderNumber;
    @Bind(R.id.service_charge)
    TextView serviceCharge;
    @Bind(R.id.deal_unit_price)
    TextView dealUnitPrice;
    @Bind(R.id.textView2)
    TextView textView2;
    @Bind(R.id.deal_num)
    TextView dealNum;
    @Bind(R.id.service_charge_precent)
    TextView serviceChargePrecent;
    @Bind(R.id.pay_way)
    TextView payWay;
    @Bind(R.id.deal_total_prices)
    TextView dealTotalPrices;
    @Bind(R.id.order_state)
    TextView orderState;
    @Bind(R.id.deal_item_ly)
    PercentLinearLayout dealItemLy;
    @Bind(R.id.nick_name)
    TextView nickName;
    @Bind(R.id.trader)
    TextView trader;
    @Bind(R.id.evaluate)
    TextView evaluate;
    @Bind(R.id.trader_describe)
    TextView traderDescribe;
    @Bind(R.id.order_create)
    TextView orderCreate;
    @Bind(R.id.order_confirm)
    TextView orderConfirm;
    @Bind(R.id.buyer_pay)
    TextView buyerPay;
    @Bind(R.id.sale_commit)
    TextView saleCommit;
    @Bind(R.id.go_evaluate)
    TextView goEvaluate;
    @Bind(R.id.apply)
    TextView apply;
    @Bind(R.id.pass)
    TextView pass;
    @Bind(R.id.ll_is_pass)
    LinearLayout llIsPass;
    @Bind(R.id.bottom_rl)
    PercentRelativeLayout bottomRl;
    private int from;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_order_detail);
        ButterKnife.bind(this);
        setUltimateBar(R.color.blue1);
        handIntent();
    }

    private void handIntent() {
        from = getIntent().getIntExtra("from", 0);
        switch (from) {
            case 23://待买方付款
                orderState.setText("待买方付款");
                bottomRl.setVisibility(View.GONE);
                findViewById(R.id.trader_view).setBackgroundResource(R.color.bg_activity);
                break;
            case 24://未通过
                orderState.setText("未通过");
                traderDescribe.setText("请在90分钟内传处理此单");
                buyerPay.setVisibility(View.VISIBLE);
                bottomRl.setVisibility(View.VISIBLE);
                findViewById(R.id.trader_view).setBackgroundResource(R.color.bg_activity);
                break;
        }
    }

    @Override
    protected List<BasePresenter> getPresenter(List<BasePresenter> presenters) {
        return null;
    }

    @Override
    public View getTitleBar() {
        return getNormalTitleBar().setTitle(getString(R.string.sale_detail));
    }

    public void ShowTip() {
        NormalDialog dialog = new NormalDialog(this, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderState.setText(getString(R.string.sale_have_pay));
                saleCommit.setVisibility(View.VISIBLE);
                llIsPass.setVisibility(View.GONE);
                goEvaluate.setVisibility(View.VISIBLE);

            }
        });
        dialog.setTitle(getString(R.string.Warm_prompt));
        dialog.setMsg(getString(R.string.tip_sale_sure));
        dialog.setMsgdo(getString(R.string.is_sure_pass));
        dialog.show();
    }

    @OnClick({R.id.apply, R.id.pass,R.id.go_evaluate})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.apply:
                toActivity(ApplyActivity.class);
                break;
            case R.id.pass:
                ShowTip();
                break;
            case R.id.go_evaluate:
                toActivity(EvaluateActivity.class);
                break;
        }
    }
}
