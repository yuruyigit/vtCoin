package com.block.vtCoin.mine.setting.account_address;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.vtCoin.R;
import com.block.vtCoin.main.BaseActivity;
import com.block.vtCoin.widget.dialog.password.PayPasswordDialog;
import com.block.vtCoin.widget.dialog.ListViewDialog;
import com.block.vtCoin.widget.base.OnMyItemClickListener;
import com.block.vtCoin.widget.title.NormalTitleBar;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class EditAddressActivity extends BaseActivity {
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_coin_type)
    TextView tvCoinType;
    @Bind(R.id.et_name)
    EditText etName;
    @Bind(R.id.et_address)
    EditText etAddress;
    private ListViewDialog currencyDialog;
    private String[] data = {"全部", "BTC比特币", "LTC莱特币", "ETH以太坊", "VTC微特币", "ETC以太币", "SC区块盾"};
    private PayPasswordDialog psDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_address);
        setUltimateBar(R.color.blue1);
        initView();
    }

    private void initView() {

    }

    private void showCoinDialog() {
        currencyDialog = new ListViewDialog(this, data);
        currencyDialog.setOnItemClickListener(new OnMyItemClickListener() {
            @Override
            public void onItemClick(View parent, View view, int position) {
                tvCoinType.setText(data[position]);
            }
        });
        currencyDialog.show();
    }

    private void showPsDialog() {
        if (psDialog == null) {
            psDialog = new PayPasswordDialog(this);
            psDialog.setOnPasswordChangedListener(new PayPasswordDialog.OnPasswordChangedListener() {
                @Override
                public void onTextChanged(String psw) {
                }

                @Override
                public void onInputFinish(String psw) {
                    showTip("保存成功");
                    finish();
                }
            });
        }
        psDialog.show();
    }

    @Override
    public View getTitleBar() {
        NormalTitleBar normalTitleBar = getNormalTitleBar().setTitle(getString(R.string.edit_address)).setRightText(getString(R.string.save));
        normalTitleBar.setRightListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPsDialog();
            }
        });
        return normalTitleBar;
    }

    @Override
    protected List<BasePresenter> getPresenter(List<BasePresenter> presenters) {
        return null;
    }

    @OnClick(R.id.rl_coin_type)
    public void onClick() {
        showCoinDialog();
    }
}
