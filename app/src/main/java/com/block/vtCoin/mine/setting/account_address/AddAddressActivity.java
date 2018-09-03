package com.block.vtCoin.mine.setting.account_address;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.vtCoin.R;
import com.block.vtCoin.main.BaseActivity;
import com.block.vtCoin.widget.dialog.ListViewDialog;
import com.block.vtCoin.widget.base.OnMyItemClickListener;
import com.block.vtCoin.widget.title.NormalTitleBar;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddAddressActivity extends BaseActivity {
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_coin_type)
    TextView tvCoinType;
    @Bind(R.id.et_name)
    EditText etName;
    @Bind(R.id.et_address)
    EditText etAddress;
    @Bind(R.id.commit)
    TextView commit;
    private ListViewDialog currencyDialog;
    private String[] data = {"全部", "BTC比特币", "LTC莱特币", "ETH以太坊", "VTC微特币", "ETC以太币", "SC区块盾"};

    private String noteName, address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        ButterKnife.bind(this);
        setUltimateBar(R.color.blue1);
        initView();
    }

    private void initView() {

    }

    private void showCoinDialog() {
        if (currencyDialog == null) {
            currencyDialog = new ListViewDialog(this, data);
            currencyDialog.setOnItemClickListener(new OnMyItemClickListener() {
                @Override
                public void onItemClick(View parent, View view, int position) {
                    tvCoinType.setText(data[position]);
                }
            });
        }
        currencyDialog.show();
    }

    @Override
    protected void onDestroy() {
        if (currencyDialog != null)
            currencyDialog.unbind();
        super.onDestroy();
    }

    @Override
    public View getTitleBar() {
        NormalTitleBar normalTitleBar = getNormalTitleBar().setTitle(getString(R.string.add_address));
        return normalTitleBar;
    }

    @Override
    protected List<BasePresenter> getPresenter(List<BasePresenter> presenters) {
        return null;
    }

    @OnClick({R.id.rl_coin_type, R.id.commit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_coin_type:
                showCoinDialog();
                break;
            case R.id.commit:
                if (isAdd()) {
                    showTip("添加成功");
                }
                break;
        }
    }

    private boolean isAdd() {
        noteName = etName.getText().toString().trim();
        address = etAddress.getText().toString().trim();
        if (!noteName.isEmpty() && !address.isEmpty()) {
            return true;
        } else
            return false;
    }
}
