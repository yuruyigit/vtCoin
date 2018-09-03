package com.block.vtCoin.main.fragment;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.block.vtCoin.R;
import com.block.vtCoin.deal.fragment.SellFragment;
import com.block.vtCoin.widget.dialog.OrderTypeDialog;
import com.block.vtCoin.deal.fragment.BuyFragment;
import com.block.vtCoin.main.MainActivity;
import com.block.vtCoin.main.widget.TabAdapter;
import com.block.vtCoin.util.ScreenUtils;
import com.block.vtCoin.widget.popupwindow.ListViewPopup;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 交易，买卖
 */
public class DealFragment extends Fragment {

    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.viewPager)
    ViewPager viewPager;
    @Bind(R.id.money_type)
    TextView moneyType;
    @Bind(R.id.choose_image_money)
    ImageView chooseImageMoney;
    @Bind(R.id.choose_layout)
    LinearLayout chooseLayout;
    @Bind(R.id.tv_order)
    TextView tvOrder;
    private List<Fragment> fragments;
    private List<String> titles;
    private BuyFragment buyfragment;
    private SellFragment sellfragment;
    private ListViewPopup coinPopup;
    private String[] data = {"CNY", "USD", "HKD", "UER", "SEK", "PHP"};
    private OrderTypeDialog orderTypeDialog;
    private MainActivity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_deal, null);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        /*订单popup*/
        orderTypeDialog = new OrderTypeDialog(getContext());
        fragments = new ArrayList<>();
        titles = new ArrayList<>();
        buyfragment = new BuyFragment();
        sellfragment = new SellFragment();
        fragments.add(buyfragment);
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
    }


    private void showPopup(View view,int width,int height,int xoff) {
        chooseImageMoney.setImageResource(R.mipmap.business_down_back);
        if (coinPopup == null) {
            coinPopup = new ListViewPopup(getContext(),data,width,height);
            coinPopup.setOnItemListener(new ListViewPopup.OnItemListener() {
                @Override
                public void onItemClick(int position) {
                    moneyType.setText(data[position]);
                }
            });
            coinPopup.setDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    chooseImageMoney.setImageResource(R.mipmap.business_down);
                }
            });
        }
        coinPopup.show(view,-xoff,0);
    }

    @OnClick({R.id.choose_layout, R.id.tv_order})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.choose_layout:
                /*获取偏移的宽度*/
                int width=ScreenUtils.dip2px(160);//px
                int height=ScreenUtils.dip2px(40*5+12);//px
                int[] widthAndHeight1 = ScreenUtils.getWidthAndHeight1(chooseLayout);//px
                int xoff=width/2-widthAndHeight1[0]/2;
                showPopup(chooseLayout,width,height,xoff);
                break;
            case R.id.tv_order:
                orderTypeDialog.show();
                break;
        }
    }


    @Override
    public void onDestroyView() {
        ButterKnife.unbind(this);
        if(coinPopup!=null)
            coinPopup.unbind();
        super.onDestroyView();
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
