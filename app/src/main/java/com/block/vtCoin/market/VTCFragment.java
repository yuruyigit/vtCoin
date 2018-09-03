package com.block.vtCoin.market;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.block.net.util.L;
import com.block.vtCoin.R;
import com.block.vtCoin.constant.CoinType;
import com.block.vtCoin.entity.MarketEntity;
import com.block.vtCoin.main.MainActivity;
import com.block.vtCoin.market.adapter.CoinAdapter;
import com.block.vtCoin.market.p.ConvertPresenter;
import com.block.vtCoin.market.p.MarketPresenter;
import com.block.vtCoin.market.v.ConvertViewImpl;
import com.block.vtCoin.market.v.MarketViewImpl;
import com.block.vtCoin.request.MyModel;
import com.block.vtCoin.widget.decoration.RecycleViewDivider;

import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;

public class VTCFragment extends BaseFragment {
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    private MarketPresenter marketPresenter;
    private ConvertPresenter convertPresenter;
    private List<MarketEntity> mMarketList;
    private CoinAdapter adapter;
    private MainActivity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPresenter();
    }

    @Override
    public void loadDialog() {
        if (isNeedLoadProgress)
            activity.loadDialog();
    }

    @Override
    public void dismissDialog() {
        if (isNeedLoadProgress)
            activity.dismissDialog();
    }

    private void initPresenter() {
        convertPresenter = new ConvertPresenter();
        convertPresenter.setModelView(new MyModel<MarketEntity>(), new ConvertViewImpl(this,CoinType.VTC));
        marketPresenter = new MarketPresenter();
        marketPresenter.setModelView(new MyModel<MarketEntity>(), new MarketViewImpl(this, CoinType.VTC));
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_market_btc;
    }

    @Override
    public void initView() {
        isNeedLoadProgress = true;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new CoinAdapter(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new RecycleViewDivider(getContext()));
        convertPresenter.requestVtc();
        marketPresenter.requestMarket();
    }


    public boolean isCurrent = false;
    public boolean isNeedLoadProgress;//被销毁，第一次进时需要加载进度条，其他时候不要
    public boolean isConvertSuc = false, isMarketSuc = false;

    private double rate = 0;

    public void setMarket(List<MarketEntity> list, int type) {
        if (list == null) {
            dismissDialog();
            return;
        } else if (type == 0) {//返回的是换算的
            L.i("返回的是换算的");
            rate = list.get(0).getData().getBuy();
            isConvertSuc = true;
        } else if (type == 1) {//返回的是行情
            L.i("返回的是行情");
            setBeforeBuy(list);
            isMarketSuc = true;
        }
        if (isMarketSuc && isConvertSuc) {//都成功了，去刷新ui
            L.i("都成功了");
            isMarketSuc = false;
            isConvertSuc = false;
            if (isCurrent) {//界面在当前采取刷新和加载
                dismissDialog();
                isNeedLoadProgress = false;
                L.i("都成功了，界面在当前，去刷新ui");
                adapter.setData(sortData(), rate);
                refreshMarket();
            }
        }
    }

    /*当刷新结束后，延迟5s再去刷新*/
    private void refreshMarket() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                convertPresenter.requestVtc();
                marketPresenter.requestMarket();
            }
        }, 5000);
    }

    /*排序*/
    private LinkedList<MarketEntity> sortData() {
        L.i("排序");
        LinkedList<MarketEntity> mData = new LinkedList<>();
        for (int i = 0; i < mMarketList.size(); i++) {
            if (mMarketList.get(i).getProvierName().equals("OKCoin")) {
                mData.add(0, mMarketList.get(i));
            } else if (mMarketList.get(i).getProvierName().equals("威特区块")) {
                mData.add(1, mMarketList.get(i));
            } else if (mMarketList.get(i).getProvierName().equals("火币网")) {
                mData.add(2, mMarketList.get(i));
            } else {
                mData.add(mMarketList.get(i));
            }
        }
        return mData;
    }

    /*把前一次买入的值赋值给这一次*/
    private void setBeforeBuy(List<MarketEntity> list) {
        L.i("把前一次买入的值赋值给这一次");
        if (mMarketList != null) {//之前已经赋值过
            for (int i = 0; i < mMarketList.size(); i++) {//for循环考虑哪个在里面，哪个在外面
                for (int j = 0; j < list.size(); j++) {
                    if (mMarketList.get(i).getProvierName().equals(list.get(j).getProvierName())) {
                        if (mMarketList.get(i).getData() != null) {
                            list.get(j).getData().setBeforeBuy(mMarketList.get(i).getData().getBuy());
                        }
                    }
                }
            }
        }
        mMarketList = list;
    }

    public void cancelRx() {
        L.i("取消加载");
        if (convertPresenter != null)
            convertPresenter.unRegisterRx(true);
        if (marketPresenter != null) {
            marketPresenter.unRegisterRx(true);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        cancelRx();
        super.onDestroy();
    }
}
