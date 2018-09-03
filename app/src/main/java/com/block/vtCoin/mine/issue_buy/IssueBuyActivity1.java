package com.block.vtCoin.mine.issue_buy;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.TextView;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.vtCoin.R;
import com.block.vtCoin.main.BaseActivity;
import com.block.vtCoin.widget.base.OnMyItemClickListener;
import com.block.vtCoin.widget.dialog.ListViewDialog;
import com.block.vtCoin.widget.editText.ClearEditText;
import com.block.vtCoin.widget.editText.MyTextWatcher;
import com.block.vtCoin.widget.title.NormalTitleBar;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class IssueBuyActivity1 extends BaseActivity {
    @Bind(R.id.et_amount)
    ClearEditText etAmount;
    @Bind(R.id.et_in_price)
    ClearEditText etInPrice;
    @Bind(R.id.et_min_money)
    ClearEditText etMinMoney;
    @Bind(R.id.et_max_money)
    ClearEditText etMaxMoney;
    @Bind(R.id.next)
    TextView next;
    @Bind(R.id.tv_type)
    TextView tvType;
    @Bind(R.id.tv_currency)
    TextView tvCurrency;
    private String amount, inPrice, minMoney, maxMoney;
    private String type, currency;
    private ListViewDialog typeDialog, currencyDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue_buy1);
        setUltimateBar(R.color.blue1);
        initView();
    }

    private void initView() {
        createPopup();
        etAmount.setText("5");
        etInPrice.setText("5");
        etMinMoney.setText("5");
        etMaxMoney.setText("5");
        setClickable(next, true);
        etAmount.addTextChangedListener(new MyTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                handNext();
            }
        });
        etInPrice.addTextChangedListener(new MyTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                handNext();
            }
        });
        etMinMoney.addTextChangedListener(new MyTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                handNext();
            }
        });
        etMaxMoney.addTextChangedListener(new MyTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                handNext();
            }
        });
    }

    private void createPopup() {
        final String[] typeItem = new String[]{"BTC", "LTC", "ETH", "VTS", "SC"};
        typeDialog = new ListViewDialog(this, typeItem, false);
        typeDialog.setOnItemClickListener(new OnMyItemClickListener() {
            @Override
            public void onItemClick(View parent, View view, int position) {
                type = typeItem[position];
                tvType.setText(type);
            }
        });
        final String[] currencyItem = new String[]{"CNY", "USD"};
        currencyDialog = new ListViewDialog(this, currencyItem, false);
        currencyDialog.setOnItemClickListener(new OnMyItemClickListener() {
            @Override
            public void onItemClick(View parent, View view, int position) {
                currency = currencyItem[position];
                tvCurrency.setText(currency);
            }
        });
    }

    public void handNext() {
        amount = etAmount.getText().toString().trim();
        inPrice = etInPrice.getText().toString().trim();
        minMoney = etMinMoney.getText().toString().trim();
        maxMoney = etMaxMoney.getText().toString().trim();
        if (!amount.isEmpty() && !inPrice.isEmpty() && !minMoney.isEmpty() && !maxMoney.isEmpty())
            setClickable(next, true);
        else
            setClickable(next, false);
    }

    /**
     * @return
     */
    @Override
    public View getTitleBar() {
        NormalTitleBar normalTitleBar = getNormalTitleBar().setTitle(getString(R.string.issue_buy));
        return normalTitleBar;
    }

    @Override
    protected List<BasePresenter> getPresenter(List<BasePresenter> presenters) {
        return null;
    }

    @OnClick({R.id.ll_type, R.id.ll_currency, R.id.next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_type:
                typeDialog.show();
                break;
            case R.id.ll_currency:
                currencyDialog.show();
                break;
            case R.id.next:
                toActivity(IssueBuyActivity2.class);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        if (typeDialog != null)
            typeDialog.unbind();
        if (currencyDialog != null)
            currencyDialog.unbind();
        super.onDestroy();
    }
}
