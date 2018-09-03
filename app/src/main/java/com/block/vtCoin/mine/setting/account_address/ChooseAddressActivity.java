package com.block.vtCoin.mine.setting.account_address;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.vtCoin.R;
import com.block.vtCoin.main.BaseActivity;
import com.block.vtCoin.mine.setting.account_address.adapter.ChooseAddressAdapter;
import com.block.vtCoin.mine.setting.account_address.m.AccountAddressEntity;
import com.block.vtCoin.widget.title.NormalTitleBar;
import java.util.ArrayList;
import java.util.List;

public class ChooseAddressActivity extends BaseActivity {
    private static final String TAG = "zxt";
    private RecyclerView mRv;
    private ChooseAddressAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private List<AccountAddressEntity> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_address);
        setUltimateBar(R.color.blue1);
        mRv = (RecyclerView) findViewById(R.id.rv);
        initDatas();
        mAdapter = new ChooseAddressAdapter(this, mDatas);
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRv.setLayoutManager(mLayoutManager);
        mRv.setAdapter(mAdapter);
    }

    @Override
    public View getTitleBar() {
        NormalTitleBar normalTitleBar=new NormalTitleBar(this).setTitle(getString(R.string.choose_address));
        normalTitleBar.setRightDrawable(R.mipmap.set_up_plus).setRightListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toActivity(AddAddressActivity.class);
            }
        });
        return normalTitleBar;
    }

    @Override
    protected List<BasePresenter> getPresenter(List<BasePresenter> presenters) {
        return null;
    }

    private void initDatas() {
        mDatas = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            AccountAddressEntity entity = new AccountAddressEntity();
            entity.type = 0;
            entity.name = "阳刚听听";
            entity.address = "sdasdfasldfalsdfkadsfd";
            mDatas.add(entity);
        }
    }
}
