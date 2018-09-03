package com.block.vtCoin.deal.order.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.block.vtCoin.R;
import com.block.vtCoin.deal.order.apply_detail.ApplyOrderDetailActivity;
import com.block.vtCoin.deal.order.buy_detail.BuyOrderDetailActivity;
import com.block.vtCoin.deal.order.OrderActivity;
import com.block.vtCoin.deal.order.adapter.DealOrderAdapter;
import com.block.vtCoin.deal.order.sale_detail.SaleOrderDetailActivity;
import com.block.vtCoin.deal.order.m.OrderEntity;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @Description
 * @Author lijie
 * @Date 2017/8/18.
 */
public class AllOrderFragment extends Fragment{

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    @Bind(R.id.refresh_layout)
    TwinklingRefreshLayout refreshLayout;
    @Bind(R.id.ivStatus)
    ImageView ivStatus;
    @Bind(R.id.tvStatus)
    TextView tvStatus;
    @Bind(R.id.null_layout)
    LinearLayout nullLayout;
    private OrderActivity activity;
    private DealOrderAdapter adapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (OrderActivity) context;
    }
    private List<OrderEntity> data=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_btc, null);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new DealOrderAdapter(getContext(), data);
        adapter.setOnItemClickListener(new DealOrderAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, int orderState) {
                if (activity.getOrdertype().equals("1")) {//买入订单
                    switch (orderState) {
                        case 0://未付款
                            startActivity(new Intent(activity, BuyOrderDetailActivity.class).putExtra("from", 10));
                            break;
                        case 1://待卖方通过
                            startActivity(new Intent(activity, BuyOrderDetailActivity.class).putExtra("from", 11));
                            break;
                        case 2://以成功
                            startActivity(new Intent(activity, BuyOrderDetailActivity.class).putExtra("from", 12));
                            break;
                    }
                } else if (activity.getOrdertype().equals("2")) {//卖出订单
                    switch (orderState) {
                        case 3://待买方付款
                            startActivity(new Intent(activity, SaleOrderDetailActivity.class).putExtra("from", 23));
                            break;
                        case 4://未通过
                            startActivity(new Intent(activity, SaleOrderDetailActivity.class).putExtra("from", 24));
                            break;
                    }
                } else if (activity.getOrdertype().equals("3")) {//仲裁订
                    startActivity(new Intent(activity, ApplyOrderDetailActivity.class));
                }
            }
        });
        recyclerView.setAdapter(adapter);
        refreshLayout.setOverScrollRefreshShow(true);//飞速滑动不加载
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        data.clear();
                        page=0;
                        initdata();
                        refreshLayout.isNoMore(false);
                        refreshLayout.finishRefreshing();
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                if (page >= 2) {//没有更多数据了
                    refreshLayout.isNoMore(true);
                } else
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            initdata();
                            page++;
                            refreshLayout.finishLoadmore();
                        }
                    }, 2000);
            }
        });
        refreshLayout.startRefresh();
    }

    private int page=0;
    private void initdata() {
        if (activity.getOrdertype().equals("1")) {//买入订单
            for (int i = 0; i < 10; i++) {
                OrderEntity entity = new OrderEntity();
                entity.orderNumber = "51656516Ffd456";
                entity.orderCreatTime = "8分钟";
                entity.orderPersonType = "买家";
                entity.orderPerson = "极速听听吧（6516516）";
                entity.orderUnitPrice = "¥ 26555.9";
                entity.orderNum = "0.001 BTC";
                entity.totalPrices = "¥ 26.66";
                if (i % 2 == 0) {
                    entity.orderState = 0;//未付款
                } else if (i % 3 == 0) {
                    entity.orderState = 1;//待卖方通过
                } else {
                    entity.orderState = 2;//交易成功
                }
                data.add(entity);
            }
        } else if (activity.getOrdertype().equals("2")) {//卖出订单
            for (int i = 0; i < 10; i++) {
                OrderEntity entity = new OrderEntity();
                entity.orderNumber = "51656516Ffd456";
                entity.orderCreatTime = "8分钟";
                entity.orderPersonType = "买家";
                entity.orderPerson = "极速听听吧（6516516）";
                entity.orderUnitPrice = "¥ 26555.9";
                entity.orderNum = "0.001 BTC";
                entity.totalPrices = "¥ 26.66";
                if (i % 2 == 0) {
                    entity.orderState = 3;//待买方付款
                } else {
                    entity.orderState = 4;//未通过
                }
                data.add(entity);
            }
        } else if (activity.getOrdertype().equals("3")) {//仲裁订单
            for (int i = 0; i < 10; i++) {
                OrderEntity entity = new OrderEntity();
                entity.orderNumber = "51656516Ffd456";
                entity.orderCreatTime = "8分钟";
                entity.orderPersonType = "买家";
                entity.orderPerson = "极速听听吧（6516516）";
                entity.orderUnitPrice = "¥ 26555.9";
                entity.orderNum = "0.001 BTC";
                entity.totalPrices = "¥ 26.66";
                entity.orderState = 5;//待买方付款
                data.add(entity);
            }
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        ButterKnife.unbind(this);
        super.onDestroyView();
    }
}
