package com.block.vtCoin.deal.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.vtCoin.R;
import com.block.vtCoin.main.BaseActivity;
import com.block.vtCoin.widget.dialog.BuyDialog;
import com.block.vtCoin.widget.dialog.NormalDialog;
import com.block.vtCoin.widget.dialog.password.PayPasswordDialog;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class DealDetailActivity extends BaseActivity {
    @Bind(R.id.surplus_amount)
    TextView surplusAmount;
    @Bind(R.id.trading_limit)
    TextView tradingLimit;
    @Bind(R.id.trading_limit_time)
    TextView tradingLimitTime;
    @Bind(R.id.deal_person)
    TextView dealPerson;
    @Bind(R.id.deal_remark)
    TextView dealRemark;
    @Bind(R.id.icon_price)
    TextView iconPrice;
    @Bind(R.id.icon_num)
    EditText iconNum;
    @Bind(R.id.coin_type)
    TextView coinType;
    @Bind(R.id.money_num)
    EditText moneyNum;
    @Bind(R.id.money_type)
    TextView moneyType;
    @Bind(R.id.deal_buy_way)
    TextView dealBuyWay;
    @Bind(R.id.deal_buy_into)
    TextView dealBuyInto;
    private PayPasswordDialog payPasswordDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal);
        setUltimateBar(R.color.blue1);
        payPasswordDialog=new PayPasswordDialog(this);
    }

    @Override
    protected List<BasePresenter> getPresenter(List<BasePresenter> presenters) {
        return null;
    }

    @Override
    public View getTitleBar() {
        return getNormalTitleBar().setTitle(getResources().getString(R.string.deal_detail));
    }

    @OnClick(R.id.deal_buy_into)
    public void onClick() {
        if (TextUtils.isEmpty(getCoinNum())) {
            showTip(getString(R.string.please_into_num));
            return;
        }
        if (TextUtils.isEmpty(getMoneyNum())) {
            showTip(getString(R.string.please_into_money));
            return;
        }
        showBuyDialog();
    }
    public String getCoinNum() {
        return iconNum.getText().toString();
    }

    public String getMoneyNum() {
        return moneyNum.getText().toString();
    }
    public  void showBuyDialog(){
        BuyDialog buyDialog=new BuyDialog(this);
        buyDialog.show();
    }
    public  void showPaypassword(){
        payPasswordDialog.show();
        payPasswordDialog.setOnPasswordChangedListener(new PayPasswordDialog.OnPasswordChangedListener() {
            @Override
            public void onTextChanged(String psw) {
            }
            @Override
            public void onInputFinish(String psw) {
                ShowTip();
            }
        });
    }

    public void  ShowTip(){
        payPasswordDialog.clear();
        payPasswordDialog.dismiss();
        NormalDialog dialog=new NormalDialog(this);
        dialog.setTitle(getString(R.string.Warm_prompt));
        dialog.setMsg(getString(R.string.tip_buyer_sure));
        dialog.setMsgdo(getString(R.string.go_pay));
        dialog.show();
    }
}
