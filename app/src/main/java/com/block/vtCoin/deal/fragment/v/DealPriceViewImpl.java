package com.block.vtCoin.deal.fragment.v;
import android.content.Context;
import com.block.vtCoin.deal.fragment.BuyFragment1;
import com.block.vtCoin.deal.fragment.SellFragment1;
import com.block.vtCoin.entity.DealPriceEntity;
import com.block.vtCoin.market.BaseFragment;
import com.block.vtCoin.market.v.IListView;
import java.util.List;
import java.util.Map;

/**
 * Created by liubin on 2017/11/8.
 */

public class DealPriceViewImpl implements IListView<DealPriceEntity> {
    private BaseFragment fragment;
    private int type;

    public DealPriceViewImpl(BaseFragment fragment,int type) {
        this.fragment = fragment;
        this.type=type;
    }

    @Override
    public void loading() {
        fragment.loadDialog();
    }

    @Override
    public void dismiss() {
        fragment.dismissDialog();
    }

    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public Map<String, Object> getParameters() {
        return null;
    }

    @Override
    public void onSuccess(List<DealPriceEntity> list) {
        if(type==0)
            ((BuyFragment1)fragment).setDealPrice(list);
        else if(type==1)
            ((SellFragment1)fragment).setDealPrice(list);
    }


    @Override
    public void onError(int code, String msg) {
        if(type==0)
            ((BuyFragment1)fragment).setDealPrice(null);
        else if(type==1)
            ((SellFragment1)fragment).setDealPrice(null);
    }

    @Override
    public Class<DealPriceEntity> getEClass() {
        return DealPriceEntity.class;
    }
}
