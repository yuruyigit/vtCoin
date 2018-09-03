package com.block.vtCoin.main.fragment;

import android.content.Context;
import android.content.res.Resources;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

import com.block.net.util.L;
import com.block.vtCoin.R;
import com.block.vtCoin.deal.fragment.BuyFragment1;
import com.block.vtCoin.deal.fragment.SellFragment;
import com.block.vtCoin.deal.fragment.SellFragment1;
import com.block.vtCoin.main.MainActivity;
import com.block.vtCoin.main.widget.TabAdapter;
import com.lsxiao.apllo.Apollo;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 交易，买卖
 */
public class DealFragment1 extends LazyLoadFragment {
    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.viewPager)
    ViewPager viewPager;
    private MainActivity activity;
    private BuyFragment1 buyFragment;
    private SellFragment1 sellfragment;
    private List<Fragment> fragments;
    private List<String> titles;
    private int currentPosition = 0;
    public static boolean isBuyCurrent = false;
    public static boolean isSellCurrent = false;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_deal1;
    }

    @Override
    protected void initView() {
        fragments = new ArrayList<>();
        titles = new ArrayList<>();
        buyFragment = new BuyFragment1();
        sellfragment = new SellFragment1();
        fragments.add(buyFragment);
        fragments.add(sellfragment);

        titles.add(getString(R.string.buy_into));
        titles.add(getString(R.string.sell_out));
        tabLayout.addTab(tabLayout.newTab().setText(titles.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(titles.get(1)));
        viewPager.setAdapter(new TabAdapter(getChildFragmentManager(), fragments, titles));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                setIndicator(tabLayout, 50, 50);
            }
        });
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
            isBuyCurrent = true;
            isSellCurrent = false;
            Apollo.get().send("buy");
            L.i("+++++++send_buy");
        } else if (currentPosition == 1) {
            isBuyCurrent = false;
            isSellCurrent = true;
            L.i("+++++++send_sell");
            Apollo.get().send("sell");
        }
    }

    @Override
    protected void onWakeOrCurrent() {
        L.i("onWakeOrCurrent");
        send();
    }

    @Override
    protected void notCurrent() {
        isBuyCurrent = false;
        isSellCurrent = false;
    }

    public void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }
    }
}
