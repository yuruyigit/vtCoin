package com.block.vtCoin.market.v;

import android.content.Context;

import com.block.net.util.L;
import com.block.vtCoin.constant.CoinType;
import com.block.vtCoin.entity.MarketEntity;
import com.block.vtCoin.market.BTCFragment;
import com.block.vtCoin.market.BaseFragment;
import com.block.vtCoin.market.ETHFragment;
import com.block.vtCoin.market.LTCFragment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author lijie
 * @Date 2017/7/13.
 */
public class MarketViewImpl implements IListView<MarketEntity> {

    private BaseFragment fragment;
    private CoinType type;

    public MarketViewImpl(BaseFragment fragment, CoinType type) {
        this.fragment = fragment;
        this.type = type;
    }

    @Override
    public void loading() {
    }

    @Override
    public void dismiss() {
    }

    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public Map<String, Object> getParameters() {
        HashMap<String, Object> map = new HashMap<>();
        String coin = "";
        if (type == CoinType.BTC)
            coin = "btc";
        else if (type == CoinType.LTC)
            coin = "ltc";
        else if (type == CoinType.ETH)
            coin = "eth";
        map.put("coin", coin);
        map.put("currency", "cny");
        return map;
    }

    @Override
    public void onError(int code, String msg) {
        L.i("onError"+msg);
        if(type==CoinType.BTC)
            ((BTCFragment)fragment).setMarket(null,1);
        if(type==CoinType.LTC)
            ((LTCFragment)fragment).setMarket(null,1);
        if(type==CoinType.ETH)
            ((ETHFragment)fragment).setMarket(null,1);
    }

    @Override
    public Class<MarketEntity> getEClass() {
        return null;
    }

    @Override
    public void onSuccess(List<MarketEntity> list) {
        if(type==CoinType.BTC)
            ((BTCFragment)fragment).setMarket(list,1);
        if(type==CoinType.LTC)
            ((LTCFragment)fragment).setMarket(list,1);
        if(type==CoinType.ETH)
            ((ETHFragment)fragment).setMarket(list,1);
    }
}
