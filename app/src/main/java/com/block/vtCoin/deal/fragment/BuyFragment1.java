package com.block.vtCoin.deal.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.block.net.util.L;
import com.block.vtCoin.R;
import com.block.vtCoin.deal.adapter.DealAdapter1;
import com.block.vtCoin.deal.fragment.P.ChatTokenPresenter;
import com.block.vtCoin.deal.fragment.P.DealPricePresenter;
import com.block.vtCoin.deal.fragment.P.QueryOrderPresenter;
import com.block.vtCoin.deal.fragment.v.ChatTokenViewImpl;
import com.block.vtCoin.deal.fragment.v.DealPriceViewImpl;
import com.block.vtCoin.deal.fragment.v.QueryOrderViewImpl;
import com.block.vtCoin.entity.ChatTokenEntity;
import com.block.vtCoin.entity.DealPriceEntity;
import com.block.vtCoin.entity.Page;
import com.block.vtCoin.entity.PageEntity;
import com.block.vtCoin.entity.QueryOrder;
import com.block.vtCoin.main.MainActivity;
import com.block.vtCoin.main.fragment.DealFragment1;
import com.block.vtCoin.market.BaseFragment;
import com.block.vtCoin.request.MyModel;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lsxiao.apllo.annotations.Receive;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;

public class BuyFragment1 extends BaseFragment {
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    @Bind(R.id.refresh_layout)
    TwinklingRefreshLayout refreshLayout;
    private MainActivity activity;
    private QueryOrderPresenter queryOrderPresenter;
    private ChatTokenPresenter chatTokenPresenter;
    private static DealPricePresenter dealPricePresenter;
    private DealAdapter1 dealAdapter;

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
    }

    @Override
    public void dismissDialog() {
    }

    private void initPresenter() {
        queryOrderPresenter = new QueryOrderPresenter();
        queryOrderPresenter.setModelView(new MyModel<QueryOrder>(), new QueryOrderViewImpl(this, 0));
        chatTokenPresenter = new ChatTokenPresenter();
        chatTokenPresenter.setModelView(new MyModel<ChatTokenEntity>(), new ChatTokenViewImpl(this, 0));
        dealPricePresenter = new DealPricePresenter();
        dealPricePresenter.setModelView(new MyModel<DealPriceEntity>(), new DealPriceViewImpl(this, 0));
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_buy1;
    }

    @Override
    protected void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        dealAdapter = new DealAdapter1(getContext());
        dealAdapter.setOnItemClickListener(new DealAdapter1.OnItemClickListener() {
            @Override
            public void onItemClick(String orderId) {
//                Map<String, Object> map = new HashMap<>();
//                map.put("orderId", orderId);
//                activity.toActivity(DealDetailActivity1.class, map);
                  /*此处做聊天*/
                Map<String ,Object> map=new HashMap<>();
                map.put("name",orderId);//实际上传的是用户名
                activity.toActivity(ChatActivity.class,map);

            }
        });
        recyclerView.setAdapter(dealAdapter);
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                L.i("onRefresh");
                isRefresh = true;
                pageIndex = 1;
//                queryOrderPresenter.queryOrder(new PageEntity(0, new Page(pageIndex, pageList)), 0);
                queryOrderPresenter.queryOrder(new PageEntity(5, new Page(pageIndex, pageList)), 0);
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                isLoadMore = true;
//                queryOrderPresenter.queryOrder(new PageEntity(0, new Page(pageIndex, pageList)), 0);
                queryOrderPresenter.queryOrder(new PageEntity(5, new Page(pageIndex, pageList)), 0);
            }
        });
        if (DealFragment1.isBuyCurrent)
            refreshLayout.startRefresh();
    }

    private boolean isRefresh = true;
    private boolean isLoadMore = true;
    private List<QueryOrder.DataBean.OrdersBean> mOrders;
    private boolean isTokenSuccess = false;
    private boolean isPriceSuccess = false;
    private boolean isPriceChanged = false;
    private int pageIndex = 1;
    private int pageList = 10;
    private int totalOrder = 0;
    private Timer timer;
    private MyTimerTask timerTask;

    /*当刷新结束后，延迟5s再去刷新*/
    private void refreshPrice() {
        timer = new Timer();
        timerTask = new MyTimerTask();
        timer.schedule(timerTask, 5000);
    }

    /**
     * 静态内部内，解决内存泄漏
     */
    private static class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            if (DealFragment1.isBuyCurrent)
                L.i("MyTimerTask");
                dealPricePresenter.getDealPrice();
        }
    }

    public void cancel() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        if (timerTask != null) {
            timerTask.cancel();
            timerTask = null;
        }
    }

    /*返回订单信息*/
    public void setQueryOrder(QueryOrder entity) {
        if (entity != null) {
            QueryOrder.DataBean data = entity.getData();
            /*处理加载问题*/
            totalOrder = data.getRows();
            if (pageIndex * pageList <= totalOrder) {
                refreshLayout.setEnableLoadmore(true);
                pageIndex++;
            } else {
                refreshLayout.setEnableLoadmore(false);
            }
            /*刷新列表*/
            if (isRefresh) {
                mOrders = data.getOrders();
            } else if (isLoadMore) {
                mOrders.addAll(data.getOrders());
            }
            /*加载价格*/
            isPriceSuccess = false;
            L.i("refreshPrice");
            cancel();
            dealPricePresenter.getDealPrice();
            /*拿去重，加载是否在线*/
            String[] names = notRepeat(data.getOrders());
            isTokenSuccess = false;
            L.i("拿Token");
            chatTokenPresenter.chatToken(names);
        }
    }

    /*返回的是是否在线*/
    public void setChatToken(ChatTokenEntity entity) {
        if (entity != null) {
            for (int i = 0; i < mOrders.size(); i++) {
                for (int j = 0; j < entity.getData().size(); j++) {
                    if (mOrders.get(i).getNickName().equals(entity.getData().get(j).getUserName())) {
                        mOrders.get(i).setOnline(entity.getData().get(j).isIsOnline());
                        break;
                    }
                }
            }
        }
        isTokenSuccess = true;
        refreshUi();
    }

    /*返回价格*/
    public void setDealPrice(List<DealPriceEntity> list) {
        if (list != null) {
            for (int i = 0; i < mOrders.size(); i++) {
                for (int j = 0; j < list.size(); j++) {
                    String type = list.get(j).getType().substring(4, list.get(j).getType().length());
                    if (mOrders.get(i).getCurt().equalsIgnoreCase(type)) {
                        if (isRefresh || isLoadMore) {
                            mOrders.get(i).setBeforeBuyPrice(list.get(j).getData().getBuy());
                            isPriceChanged = true;
                        } else {
                            mOrders.get(i).setNowBuyPrice(list.get(j).getData().getBuy());
                            if (mOrders.get(i).getBeforeBuyPrice() != mOrders.get(i).getNowBuyPrice()) {
                                isPriceChanged = true;
                            } else {
                                isPriceChanged = false;
                            }
                        }
                    }
                }
            }
        }
        isPriceSuccess = true;
        if(DealFragment1.isBuyCurrent){
            refreshPrice();
        }
        refreshUi();
    }

    public void refreshUi() {
        if (DealFragment1.isBuyCurrent && isTokenSuccess && isPriceSuccess && isPriceChanged) {
            dealAdapter.setData(mOrders, isRefresh || isLoadMore);
            if (refreshLayout != null) {//view没有被销毁
                if (isRefresh) {
                    refreshLayout.finishRefreshing();
                    isRefresh = false;
                } else if (isLoadMore) {
                    refreshLayout.finishLoadmore();
                    isLoadMore = false;
                }
            }
        }
    }

    @Receive(tag = "buy")//未初始化是接收不到的
    public void loadBuyData() {
        L.i("");
        loadData();
    }

    private void loadData() {
        if (DealFragment1.isBuyCurrent) {
            L.i("加载数据");
            refreshLayout.startRefresh();
        } else {
            L.i("停止加载");
            cancel();
        }
    }

    /*给数组去重，拿到不重复的nickName*/
    public String[] notRepeat(List<QueryOrder.DataBean.OrdersBean> data) {
        //给数组赋值
        String[] names = new String[data.size()];
        for (int i = 0; i < data.size(); i++) {
            names[i] = data.get(i).getNickName();
        }
        //给数组去重
        Arrays.sort(names);
        List<String> list = new ArrayList<>();
        list.add(names[0]);
        for (int i = 1; i < names.length; i++) {
            if (!names[i].equals(list.get(list.size() - 1))) {
                list.add(names[i]);
            }
        }
        String[] arrayResult = list.toArray(new String[list.size()]);
        return arrayResult;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        if (queryOrderPresenter != null)
            queryOrderPresenter.unRegisterRx();
        if (chatTokenPresenter != null)
            chatTokenPresenter.unRegisterRx();
        if (dealPricePresenter != null)
            dealPricePresenter.unRegisterRx();
        cancel();
        super.onDestroy();
    }
}

