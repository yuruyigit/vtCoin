package com.block.vtCoin.main;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.View;
import com.block.net.mvp.presenter.BasePresenter;
import com.block.vtCoin.R;
import com.block.vtCoin.main.fragment.AdvisoryFragment;
import com.block.vtCoin.main.fragment.DealFragment1;
import com.block.vtCoin.main.fragment.MarketFragment;
import com.block.vtCoin.main.fragment.MessageFragment;
import com.block.vtCoin.main.fragment.MineFragment;
import com.block.vtCoin.main.widget.MainViewPager;
import com.block.vtCoin.main.widget.TabAdapter;
import com.block.vtCoin.main.widget.TabView;
import com.block.vtCoin.main.widget.ViewPagerScroller;
import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;

public class MainActivity extends BaseActivity {
    @Bind(R.id.viewPager)
    MainViewPager viewPager;
    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private List<String> mTitles =new ArrayList<>();
    private TabAdapter adapter;
    private AdvisoryFragment advisoryfragment;
    private MarketFragment marketfragment;
    private DealFragment1 dealfragment1;
    private MessageFragment messagefragment;
    private MineFragment minefragment;
    private int[] mIconUnSelectIds = {R.mipmap.icon_information,R.mipmap.icon_market, R.mipmap.icon_deal, R.mipmap.icon_news,R.mipmap.icon_mine};
    private int[] mIconSelectIds = {R.mipmap.icon_information_click,R.mipmap.icon_market_click, R.mipmap.icon_deal_click, R.mipmap.icon_news_click,R.mipmap.icon_mine_click};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUltimateBar(R.color.blue1);
        initData();
        tabLayout.setupWithViewPager(viewPager);//在前面调用，防止tabLayout隐藏
        adapter=new TabAdapter(getSupportFragmentManager(),mFragments,mTitles);
        viewPager.setAdapter(adapter);
        adapter.notifyDataSetChanged();

//        防止viewpager滚动时带动画效果
        viewPager.setHorizontalScrollBarEnabled(false);
        ViewPagerScroller scroller = new ViewPagerScroller(this);
        scroller.setScrollDuration(0);
        scroller.initViewPagerScroll(viewPager);

        for (int i = 0;i<tabLayout.getTabCount();i++){
            TabView tabView = new TabView(this,mIconSelectIds[i], mIconUnSelectIds[i],mTitles.get(i));
            if (i == 0){
                tabView.check(true);
                viewPager.setCurrentItem(i);}
            tabLayout.getTabAt(i).setCustomView(tabView);
        }

//        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                ((TabView)tabLayout.getTabAt(position).getCustomView()).check(true);
//                for (int i = 0;i < mFragments.size();i++){
//                    if (i != position)
//                        ((TabView)tabLayout.getTabAt(i).getCustomView()).check(false);
//                }
//            }
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//
//        });
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                ((TabView) tab.getCustomView()).check(true);
                changeStatus(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                ((TabView)tab.getCustomView()).check(false);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    @Override
    protected List<BasePresenter> getPresenter(List<BasePresenter> presenters) {
        return null;
    }

    @Override
    public View getTitleBar() {
        return null;
    }

    private void changeStatus(int position) {
        if (position == 0 || position == 1 || position == 2||position==3) {
            setUltimateBar(R.color.blue1);
        } else if (position == 4) {
            setUltimateBar(R.color.blue3);
        }
    }

    private void initData() {
        mTitles.add(getString(R.string.advisory));
        mTitles.add(getString(R.string.market));
        mTitles.add(getString(R.string.deal));
        mTitles.add(getString(R.string.message));
        mTitles.add(getString(R.string.mine));
        advisoryfragment=new AdvisoryFragment();
        marketfragment=new MarketFragment();
        dealfragment1=new DealFragment1();
        messagefragment =new MessageFragment();
        minefragment=new MineFragment();

        mFragments.add(advisoryfragment);
        mFragments.add(marketfragment);
        mFragments.add(dealfragment1);

        mFragments.add(messagefragment);
        mFragments.add(minefragment);

    }
}
