package com.block.vtCoin.advisory.v;

import android.content.Context;

import com.block.net.mvp.view.ICommonView;
import com.block.vtCoin.advisory.m.AdvisoryEntity;
import com.block.vtCoin.main.fragment.AdvisoryFragment;

import java.util.Map;

/**
 * @Description
 * @Author lijie
 * @Date 2017/8/17.
 */
public class AdvisoryImpl implements ICommonView<AdvisoryEntity> {

    private AdvisoryFragment advisoryfragment;

    public AdvisoryImpl(AdvisoryFragment advisoryfragment) {
        this.advisoryfragment = advisoryfragment;
    }

    @Override
    public void loading() {

    }

    @Override
    public void dismiss() {

    }

    @Override
    public Context getContext() {
        return advisoryfragment.getActivity();
    }

    @Override
    public Map<String, Object> getParameters() {
        return null;
    }

    @Override
    public void onCompleted(AdvisoryEntity entity) {

    }

    @Override
    public void onError(int code, String msg) {

    }

    @Override
    public String getUrlAction() {
        return null;
    }

    @Override
    public Class<AdvisoryEntity> getEClass() {
        return null;
    }
}
