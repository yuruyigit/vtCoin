package com.block.vtCoin.mine.deal_recode.buy_deal_recode;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.vtCoin.R;
import com.block.vtCoin.main.BaseActivity;
import com.block.vtCoin.main.widget.TabAdapter;
import com.block.vtCoin.mine.deal_recode.buy_deal_recode.fragment.AllFragment;
import com.block.vtCoin.mine.deal_recode.buy_deal_recode.fragment.BTCFragment;
import com.block.vtCoin.mine.deal_recode.buy_deal_recode.fragment.ETHFragment;
import com.block.vtCoin.mine.deal_recode.buy_deal_recode.fragment.LTCFragment;
import com.block.vtCoin.mine.deal_recode.buy_deal_recode.fragment.VTSFragment;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * @Description
 * @Author lijie
 * @Date 2017/8/23.
 */
public class BuyDealActivity extends BaseActivity{
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
        setContentView(R.layout.activity_order);
        setUltimateBar(R.color.blue1);
        initView();
    }
    private void initView() {
        allFragment=new AllFragment();
        btcFragment=new BTCFragment();
        ltcFragment=new LTCFragment();
        ethFragment=new ETHFragment();
        vtsFragment=new VTSFragment();
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
        viewPager.setAdapter(new TabAdapter(getSupportFragmentManager(),fragments,titles));
        tabLayout.setupWithViewPager(viewPager);

    }
    @Override
    protected List<BasePresenter> getPresenter(List<BasePresenter> presenters) {
        return null;
    }

    @Override
    public View getTitleBar() {
            return getNormalTitleBar().setTitle(getString(R.string.buy_deal_recode));
    }
}
