package com.block.vtCoin.market;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.block.vtCoin.constant.CoinType;
import com.block.vtCoin.entity.MarketEntity;
import com.lsxiao.apllo.Apollo;
import com.lsxiao.apllo.entity.SubscriptionBinder;

import java.util.List;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {
    private SubscriptionBinder mBinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinder = Apollo.get().bind(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), null);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    public abstract int getLayoutId();

    protected abstract void initView();

    public abstract void loadDialog();

    public abstract void dismissDialog();


    @Override
    public void onDestroyView() {
        ButterKnife.unbind(this);
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        if (null != mBinder)
            mBinder.unbind();
        super.onDestroy();
    }
}
