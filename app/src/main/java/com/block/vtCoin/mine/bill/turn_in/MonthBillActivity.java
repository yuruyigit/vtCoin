package com.block.vtCoin.mine.bill.turn_in;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.vtCoin.R;
import com.block.vtCoin.main.BaseActivity;
import com.block.vtCoin.main.widget.TabAdapter;
import com.block.vtCoin.mine.bill.turn_in.fragment_month.AllFragment;
import com.block.vtCoin.mine.bill.turn_in.fragment_month.BTCFragment;
import com.block.vtCoin.mine.bill.turn_in.fragment_month.ETHFragment;
import com.block.vtCoin.mine.bill.turn_in.fragment_month.LTCFragment;
import com.block.vtCoin.mine.bill.turn_in.fragment_month.VTSFragment;
import com.block.vtCoin.widget.title.NormalTitleBar;
import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @Description
 * @Author lijie
 * @Date 2017/8/23.
 */
public class MonthBillActivity extends BaseActivity {
    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.viewPager)
    ViewPager viewPager;
    private AllFragment allFragment;
    private BTCFragment btcFragment;
    private LTCFragment ltcFragment;
    private ETHFragment ethFragment;
    private VTSFragment vtsFragment;


    private List<Fragment> fragments = new ArrayList<>();
    private List<String> titles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month_bill);
        ButterKnife.bind(this);
        setUltimateBar(R.color.blue1);
        initView();
    }

    @Override
    protected List<BasePresenter> getPresenter(List<BasePresenter> presenters) {
        return null;
    }

    private void initView() {
        allFragment = new AllFragment();
        btcFragment = new BTCFragment();
        ltcFragment = new LTCFragment();
        ethFragment = new ETHFragment();
        vtsFragment = new VTSFragment();
        fragments.add(allFragment);
        fragments.add(btcFragment);
        fragments.add(ltcFragment);
        fragments.add(ethFragment);
        fragments.add(vtsFragment);
        titles.add(getString(R.string.all));
        titles.add("BTC");
        titles.add("LTC");
        titles.add("ETH");
        titles.add("VTS");
        tabLayout.addTab(tabLayout.newTab().setText(titles.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(titles.get(1)));
        tabLayout.addTab(tabLayout.newTab().setText(titles.get(2)));
        tabLayout.addTab(tabLayout.newTab().setText(titles.get(3)));
        tabLayout.addTab(tabLayout.newTab().setText(titles.get(4)));
        viewPager.setAdapter(new TabAdapter(getSupportFragmentManager(), fragments, titles));
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public View getTitleBar() {
        NormalTitleBar normalTitle = getNormalTitleBar().setTitle("月账单");
        return normalTitle;
    }
}
