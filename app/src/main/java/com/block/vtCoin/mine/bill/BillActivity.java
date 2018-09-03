package com.block.vtCoin.mine.bill;

import android.os.Bundle;
import android.view.View;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.vtCoin.R;
import com.block.vtCoin.main.BaseActivity;
import com.block.vtCoin.mine.bill.turn_in.TurnInBillActivity;
import com.block.vtCoin.mine.bill.turn_out.TurnOutBillActivity;
import com.block.vtCoin.widget.title.NormalTitleBar;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class BillActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        ButterKnife.bind(this);
        setUltimateBar(R.color.blue1);
    }


    @Override
    public View getTitleBar() {
        NormalTitleBar normalTitleBar = getNormalTitleBar().setTitle("账单").setRightDrawable(R.mipmap.wallet_ansfer).setRightListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toActivity(TurnInBillActivity.class);
            }
        });
        return normalTitleBar;
    }

    @Override
    protected List<BasePresenter> getPresenter(List<BasePresenter> presenters) {
        return null;
    }

    @OnClick({R.id.turn_in_bill, R.id.turn_out_bill})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.turn_in_bill:
                toActivity(TurnInBillActivity.class);
                break;
            case R.id.turn_out_bill:
                toActivity(TurnOutBillActivity.class);
                break;
        }
    }
}
