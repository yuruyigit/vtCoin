package com.block.vtCoin.mine.deal_recode.buy_deal_recode.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.block.vtCoin.R;

import butterknife.ButterKnife;

/**
 * @Description
 * @Author lijie
 * @Date 2017/8/18.
 */
public class ETHFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_btc, null);
        ButterKnife.bind(this, view);

        return view;
    }
}
