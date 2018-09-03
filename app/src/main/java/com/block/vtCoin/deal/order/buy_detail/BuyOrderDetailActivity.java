package com.block.vtCoin.deal.order.buy_detail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.vtCoin.R;
import com.block.vtCoin.main.BaseActivity;
import com.block.vtCoin.widget.dialog.NormalDialog;
import com.block.vtCoin.deal.order.apply_detail.ApplyActivity;
import com.block.vtCoin.util.ScreenUtils;
import com.block.vtCoin.widget.popupwindow.ListViewPopup;
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
public class BuyOrderDetailActivity extends BaseActivity {
    @Bind(R.id.order_number)
    TextView orderNumber;
    @Bind(R.id.service_charge)
    TextView serviceCharge;
    @Bind(R.id.deal_unit_price)
    TextView dealUnitPrice;
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
    @Bind(R.id.pay_way_text)
    TextView payWayText;
    @Bind(R.id.pay_image)
    ImageView payImage;
    @Bind(R.id.choose_pay_layout)
    LinearLayout choosePayLayout;
    @Bind(R.id.account)
    TextView account;
    @Bind(R.id.copy_account)
    TextView copyAccount;
    @Bind(R.id.name)
    TextView name;
    @Bind(R.id.copy_name)
    TextView copyName;
    @Bind(R.id.bank_name)
    TextView bankName;
    @Bind(R.id.copy_bank_name)
    TextView copyBankName;
    @Bind(R.id.bank_name_layout)
    PercentLinearLayout bankNameLayout;
    @Bind(R.id.bank_type)
    TextView bankType;
    @Bind(R.id.copy_bank_type)
    TextView copyBankType;
    @Bind(R.id.bank_type_layout)
    PercentLinearLayout bankTypeLayout;
    @Bind(R.id.pay_money)
    TextView payMoney;
    @Bind(R.id.copy_pay_money)
    TextView copyPayMoney;
    @Bind(R.id.Transfer_postscript)
    TextView TransferPostscript;
    @Bind(R.id.copy_Transfer_postscript)
    TextView copyTransferPostscript;
    @Bind(R.id.qr_layout)
    PercentRelativeLayout qrLayout;
    @Bind(R.id.pay_describe1)
    TextView payDescribe1;
    @Bind(R.id.pay_describe2)
    TextView payDescribe2;
    @Bind(R.id.buy_layout)
    PercentLinearLayout buyLayout;
    @Bind(R.id.nick_name)
    TextView nickName;
    @Bind(R.id.trader)
    TextView trader;
    @Bind(R.id.evaluate)
    TextView evaluate;
    @Bind(R.id.trader_describe)
    TextView traderDescribe;
    @Bind(R.id.pay_evaluate)
    TextView payEvaluate;
    @Bind(R.id.ll_wait)
    LinearLayout llWait;
    private int from;
    private ListViewPopup payTypePopup;
    private String[] items = {"支付宝", "微信", "银行卡"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_order_detail);
        ButterKnife.bind(this);
        setUltimateBar(R.color.blue1);
        handIntent();
    }

    @Override
    protected List<BasePresenter> getPresenter(List<BasePresenter> presenters) {
        return null;
    }

    private void handIntent() {
        from = getIntent().getIntExtra("from", 0);
        switch (from) {
            case 10://买入订单，未付款
                orderState.setText("未付款");
                bankNameLayout.setVisibility(View.GONE);
                bankTypeLayout.setVisibility(View.GONE);
                traderDescribe.setVisibility(View.GONE);
                payEvaluate.setText(getString(R.string.confirm_pay));
                payEvaluate.setVisibility(View.VISIBLE);
                llWait.setVisibility(View.GONE);
                break;
            case 11://买入订单，未付款
                orderState.setText("待卖方通过");
                bankNameLayout.setVisibility(View.GONE);
                bankTypeLayout.setVisibility(View.GONE);
                payDescribe1.setVisibility(View.GONE);
                payDescribe2.setVisibility(View.GONE);
                traderDescribe.setVisibility(View.GONE);
                payEvaluate.setVisibility(View.GONE);
                llWait.setVisibility(View.VISIBLE);
                break;
            case 12://买入订单，未付款
                orderState.setText("卖方已通过");
                bankNameLayout.setVisibility(View.GONE);
                bankTypeLayout.setVisibility(View.GONE);
                payDescribe1.setVisibility(View.GONE);
                payDescribe2.setVisibility(View.GONE);
                payEvaluate.setText(getString(R.string.go_evaluate));
                payEvaluate.setVisibility(View.VISIBLE);
                llWait.setVisibility(View.GONE);
                findViewById(R.id.trader_view).setVisibility(View.GONE);
                findViewById(R.id.time_view).setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public View getTitleBar() {
        return getNormalTitleBar().setTitle(getString(R.string.buy_detail));
    }

    public void ShowTip() {
        NormalDialog dialog = new NormalDialog(this, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyOrderDetailActivity.this, ApplyActivity.class));
            }
        });
        dialog.setTitle(getString(R.string.Warm_prompt));
        dialog.setMsg(getString(R.string.arbitration_msg));
        dialog.show();
    }

    private void showPopup(View view, int width, int height) {
        payImage.setImageResource(R.mipmap.business_down_back);
        if (payTypePopup == null) {
            payTypePopup = new ListViewPopup(this,items,width,height);
            payTypePopup.setDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    payImage.setImageResource(R.mipmap.business_down);
                }
            });
            payTypePopup.setOnItemListener(new ListViewPopup.OnItemListener() {
                @Override
                public void onItemClick(int position) {
                    payWayText.setText(items[position]);
                    switch (position) {
                        case 0://支付宝
                            bankNameLayout.setVisibility(View.GONE);
                            bankTypeLayout.setVisibility(View.GONE);
                            qrLayout.setVisibility(View.VISIBLE);
                            break;
                        case 1://微  信

                            break;
                        case 2://银行卡
                            bankNameLayout.setVisibility(View.VISIBLE);
                            bankTypeLayout.setVisibility(View.VISIBLE);
                            qrLayout.setVisibility(View.GONE);
                            break;
                    }
                }
            });
        }
        payTypePopup.show(view, 0, 0);
    }

    @OnClick({R.id.choose_pay_layout, R.id.copy_account, R.id.copy_name, R.id.copy_bank_name, R.id.copy_bank_type,
            R.id.copy_pay_money, R.id.copy_Transfer_postscript, R.id.qr_layout, R.id.have_pay, R.id.apply, R.id.pay_evaluate})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.choose_pay_layout:
                /*获取偏移的宽度*/
                int width= ScreenUtils.dip2px(155);//px
                int height=ScreenUtils.dip2px(40*3+12);//px
                showPopup(choosePayLayout,width,height);
                break;
            case R.id.copy_account:
                break;
            case R.id.copy_name:
                break;
            case R.id.copy_bank_name:
                break;
            case R.id.copy_bank_type:
                break;
            case R.id.copy_pay_money:
                break;
            case R.id.copy_Transfer_postscript:
                break;
            case R.id.qr_layout:
                startActivity(new Intent(this, QrPictureActivity.class));
                break;
            case R.id.pay_evaluate:
                if (from == 10) {//确认支付

                } else if (from == 12) {
                    startActivity(new Intent(this, EvaluateActivity.class));
                }
                break;
            case R.id.have_pay:
                break;
            case R.id.apply:
                ShowTip();
                break;
        }
    }
}
