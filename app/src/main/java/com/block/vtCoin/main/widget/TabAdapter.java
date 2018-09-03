package com.block.vtCoin.main.widget;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.block.vtCoin.main.fragment.LazyLoadFragment;
import com.block.vtCoin.market.BaseFragment;

import java.util.List;

/**
 * @Description
 * @Author lijie
 * @Date 2017/7/12.
 */
public class TabAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;                         //fragment列表
    private List<String> titles;                              //tab名的列表
    public TabAdapter(FragmentManager fm, List<Fragment> fragments, List<String> titles)
    {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
    }
    @Override
    public Fragment getItem(int position)
    {
        return fragments.get(position);
    }

    @Override
    public int getCount()
    {
        return fragments.size();
    }
    //此方法用来显示tab上的名字
    @Override
    public CharSequence getPageTitle(int position) {
        if (titles != null)
            return titles.get(position);
        return null;
    }
}
