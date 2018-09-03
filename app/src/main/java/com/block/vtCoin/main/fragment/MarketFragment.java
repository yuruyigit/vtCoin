package com.block.vtCoin.main.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.block.net.util.L;
import com.block.vtCoin.R;
import com.block.vtCoin.constant.ApolloAction;
import com.block.vtCoin.main.MainActivity;
import com.block.vtCoin.market.BTCFragment;
import com.block.vtCoin.market.BaseFragment;
import com.block.vtCoin.market.ETHFragment;
import com.block.vtCoin.market.LTCFragment;
import com.block.vtCoin.market.adapter.TabAdapter;
import com.block.vtCoin.request.ApiAction;
import com.lsxiao.apllo.Apollo;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * @Description 区块
 * @Author lijie
 * @Date 2017/7/12.
 */
public class MarketFragment extends LazyLoadFragment {
    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.viewPager)
    ViewPager viewPager;
    private MainActivity activity;
    private BTCFragment btcfragment;
    private LTCFragment ltcfragment;
    private ETHFragment ethfragment;
    private List<BaseFragment> fragments;
    private List<String> titles;
    private int currentPosition = 0;
    public static boolean isBtcCurrent = false;
    public static boolean isLtcCurrent = false;
    public static boolean isEthCurrent = false;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_market;
    }

    @Override
    protected void initView() {
        fragments = new ArrayList<>();
        titles = new ArrayList<>();
        btcfragment = new BTCFragment();
        ltcfragment = new LTCFragment();
        ethfragment = new ETHFragment();

        fragments.add(btcfragment);
        fragments.add(ltcfragment);
        fragments.add(ethfragment);
        titles.add("BTC");
        titles.add("LTC");
        titles.add("ETH");

        tabLayout.addTab(tabLayout.newTab().setText(titles.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(titles.get(1)));
        tabLayout.addTab(tabLayout.newTab().setText(titles.get(2)));
        viewPager.setAdapter(new TabAdapter(getChildFragmentManager(), fragments, titles));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                currentPosition = tab.getPosition();
               send();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

    }

    public void send() {
        if (currentPosition == 0) {
            isBtcCurrent = true;
            isEthCurrent = false;
            isLtcCurrent = false;
            Apollo.get().send("btc");
        } else if (currentPosition == 1) {
            isBtcCurrent = false;
            isEthCurrent = false;
            isLtcCurrent = true;
            Apollo.get().send("ltc");
        } else if (currentPosition == 2) {
            isBtcCurrent = false;
            isEthCurrent = true;
            isLtcCurrent = false;
            Apollo.get().send("eth");
        }
    }

    @Override
    protected void onWakeOrCurrent() {
        send();
    }

    @Override
    protected void notCurrent() {
        isLtcCurrent = false;
        isBtcCurrent = false;
        isEthCurrent = false;
    }

}
