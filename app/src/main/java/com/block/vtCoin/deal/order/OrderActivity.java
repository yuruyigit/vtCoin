package com.block.vtCoin.deal.order;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.vtCoin.R;
import com.block.vtCoin.deal.order.fragment.AllOrderFragment;
import com.block.vtCoin.main.BaseActivity;
import com.block.vtCoin.deal.order.fragment.BTCorderfragment;
import com.block.vtCoin.deal.order.fragment.ETHorderfragment;
import com.block.vtCoin.deal.order.fragment.LTCorderfragment;
import com.block.vtCoin.deal.order.fragment.VTSorderfragment;
import com.block.vtCoin.main.widget.TabAdapter;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * @Description
 * @Author lijie//复用三个订单界面，买入订单，卖出订单，仲裁订单
 * @Date 2017/8/23.
 */
public class OrderActivity extends BaseActivity{
    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.viewPager)
    ViewPager viewPager;
    private String ordertype;
    private AllOrderFragment allorderfragment;
    private BTCorderfragment btcorderfragment;
    private LTCorderfragment ltcorderfragment;
    private ETHorderfragment ethorderfragment;
    private VTSorderfragment vtsorderfragment;


    private List<Fragment> fragments = new ArrayList<>();
    private List<String> titles = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        setUltimateBar(R.color.blue1);
        initView();
    }

    public String getOrdertype(){
        if(getIntent().getStringExtra("ordertype")!=null)
            ordertype=getIntent().getStringExtra("ordertype");
            return ordertype;

    }


    private void initView() {
        allorderfragment=new AllOrderFragment();
        btcorderfragment=new BTCorderfragment();
        ltcorderfragment=new LTCorderfragment();
        ethorderfragment=new ETHorderfragment();
        vtsorderfragment=new VTSorderfragment();
        fragments.add(allorderfragment);
        fragments.add(btcorderfragment);
        fragments.add(ltcorderfragment);
        fragments.add(ethorderfragment);
        fragments.add(vtsorderfragment);
        titles.add("全部");
        titles.add("BTC");
        titles.add("LTC");
        titles.add("VTH");
        titles.add("VTS");

        tabLayout.addTab(tabLayout.newTab().setText(titles.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(titles.get(1)));
        tabLayout.addTab(tabLayout.newTab().setText(titles.get(2)));
        tabLayout.addTab(tabLayout.newTab().setText(titles.get(3)));
        tabLayout.addTab(tabLayout.newTab().setText(titles.get(4)));
        viewPager.setAdapter(new TabAdapter(getSupportFragmentManager(),fragments,titles));
        tabLayout.setupWithViewPager(viewPager);
//        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

    }

    @Override
    protected List<BasePresenter> getPresenter(List<BasePresenter> presenters) {
        return null;
    }

    @Override
    public View getTitleBar() {
        if(getOrdertype().equals("1")){
            return getNormalTitleBar().setTitle("买入订单");
        }else if(getOrdertype().equals("2")){
            return getNormalTitleBar().setTitle("卖出订单");
        }else {
            return getNormalTitleBar().setTitle("仲裁订单");
        }
    }
}
