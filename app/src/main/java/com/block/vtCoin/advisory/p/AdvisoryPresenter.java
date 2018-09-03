package com.block.vtCoin.advisory.p;

import android.view.View;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.net.mvp.view.ICommonView;
import com.block.vtCoin.advisory.m.AdvisoryEntity;
import com.block.vtCoin.request.MyModel;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @Description
 * @Author lijie
 * @Date 2017/8/17.
 */
public class AdvisoryPresenter extends BasePresenter<MyModel<AdvisoryEntity>,ICommonView<AdvisoryEntity>> {


    public Collection<? extends View> getViews(int[] data)
    {
        ArrayList<View> mViews = new ArrayList<>();
        return mViews;
    }
}
