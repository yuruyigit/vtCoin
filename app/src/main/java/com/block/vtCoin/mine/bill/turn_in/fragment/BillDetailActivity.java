package com.block.vtCoin.mine.bill.turn_in.fragment;

import android.os.Bundle;
import android.view.View;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.vtCoin.R;
import com.block.vtCoin.main.BaseActivity;

import java.util.List;

/**
 * @Description
 * @Author lijie
 * @Date 2017/8/23.
 */
public class BillDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_detail);
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
        return getNormalTitleBar().setTitle("账单详情");
    }

}
