package com.block.vtCoin.deal.order.apply_detail;

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
import com.block.vtCoin.deal.order.buy_detail.QrPictureActivity;
import com.block.vtCoin.util.ScreenUtils;
import com.block.vtCoin.widget.popupwindow.ListViewPopup;
import com.zhy.android.percent.support.PercentLinearLayout;
import com.zhy.android.percent.support.PercentRelativeLayout;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ApplyOrderDetailActivity extends BaseActivity {
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
    @Bind(R.id.textView5)
    TextView textView5;
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
    @Bind(R.id.order_create)
    TextView orderCreate;
    @Bind(R.id.order_confirm)
    TextView orderConfirm;
    @Bind(R.id.buyer_pay)
    TextView buyerPay;
    @Bind(R.id.sale_commit)
    TextView saleCommit;
    private ListViewPopup payTypePopup;
    private String[] items = {"支付宝", "微信", "银行卡"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_order_detail);
        ButterKnife.bind(this);
        setUltimateBar(R.color.blue1);
        initView();
    }

    @Override
    protected List<BasePresenter> getPresenter(List<BasePresenter> presenters) {
        return null;
    }


    private void initView() {
        orderState.setText("仲裁中");
        payDescribe1.setVisibility(View.GONE);
        payDescribe2.setVisibility(View.GONE);
        traderDescribe.setVisibility(View.GONE);

    }

    @Override
    public View getTitleBar() {
        return getNormalTitleBar().setTitle(getString(R.string.apply_detail));
    }

    public void ShowTip() {
        NormalDialog dialog = new NormalDialog(this, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTip("您已取消仲裁");
            }
        });
        dialog.setTitle(getString(R.string.Warm_prompt));
        dialog.setMsg(getString(R.string.tip_cancel_apply));
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

    @OnClick({R.id.choose_pay_layout, R.id.copy_account, R.id.copy_name, R.id.copy_pay_money,
            R.id.copy_Transfer_postscript, R.id.qr_layout, R.id.check_progress, R.id.cancel_apply})
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
            case R.id.copy_pay_money:
                break;
            case R.id.copy_Transfer_postscript:
                break;
            case R.id.qr_layout:
                startActivity(new Intent(this, QrPictureActivity.class));
                break;
            case R.id.check_progress:
                startActivity(new Intent(this, ApplyProgressActivity.class));
                break;
            case R.id.cancel_apply:
                ShowTip();
                break;
        }
    }
}
