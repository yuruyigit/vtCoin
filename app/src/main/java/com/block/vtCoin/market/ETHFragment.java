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
import com.block.vtCoin.main.fragment.MarketFragment;
import com.block.vtCoin.market.adapter.CoinAdapter;
import com.block.vtCoin.market.p.ConvertPresenter;
import com.block.vtCoin.market.p.MarketPresenter;
import com.block.vtCoin.market.v.ConvertViewImpl;
import com.block.vtCoin.market.v.MarketViewImpl;
import com.block.vtCoin.request.MyModel;
import com.block.vtCoin.util.AppManager;
import com.block.vtCoin.widget.decoration.RecycleViewDivider;
import com.lsxiao.apllo.annotations.Receive;

import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;

public class ETHFragment extends BaseFragment {

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    private MarketPresenter marketPresenter;
    private ConvertPresenter convertPresenter;
    private List<MarketEntity> mMarketList;
    private double rate = 0;
    private CoinAdapter adapter;
    private MainActivity activity;
    public boolean isFirstLoad;//被销毁，第一次进时需要加载进度条，其他时候不要
    public boolean isConvertSuc = false, isMarketSuc = false;

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
        if (isFirstLoad)
            activity.loadDialog();
    }

    @Override
    public void dismissDialog() {
        if (isFirstLoad) {
            isFirstLoad = false;
            activity.dismissDialog();
        }
    }

    private void initPresenter() {
        L.i("fragment   Presenter初始化");
        convertPresenter = new ConvertPresenter();
        convertPresenter.setModelView(new MyModel<MarketEntity>(), new ConvertViewImpl(this, CoinType.ETH));
        marketPresenter = new MarketPresenter();
        marketPresenter.setModelView(new MyModel<MarketEntity>(), new MarketViewImpl(this, CoinType.ETH));
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_market_btc;
    }

    @Override
    public void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new CoinAdapter(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new RecycleViewDivider(getContext()));
        if (mMarketList != null && rate != 0) {
            adapter.setData(mMarketList, rate);
        } else
            isFirstLoad = true;
    }

    @Receive(tag = "eth")
    public void loadEth() {
        L.i("");
        loadData();
    }

    public void loadData() {
        if (MarketFragment.isEthCurrent) {
            L.i("加载数据");
            loadDialog();
            convertPresenter.requestVtc();
            marketPresenter.requestMarket();
        } else {
            L.i("停止加载");
        }
    }

    public void setMarket(List<MarketEntity> list, int type) {
        if (list == null) {
            dismissDialog();
            return;
        } else if (type == 0) {//返回的是换算的
//            L.i("返回的是换算的");
            rate = list.get(0).getData().getBuy();
            isConvertSuc = true;
        } else if (type == 1) {//返回的是行情
//            L.i("返回的是行情");
            setBeforeBuy(list);
            isMarketSuc = true;
        }
        if (isMarketSuc && isConvertSuc) {//都成功了，去刷新ui
            L.i("都成功了");
            isMarketSuc = false;
            isConvertSuc = false;
            dismissDialog();
            adapter.setData(sortData(), rate);
            refreshMarket();
        }
    }

    /*当刷新结束后，延迟5s再去刷新*/
    private void refreshMarket() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                loadData();
            }
        }, 5000);
    }

    /*排序*/
    private LinkedList<MarketEntity> sortData() {
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
