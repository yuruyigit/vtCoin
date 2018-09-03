package com.block.vtCoin.mine.bill.turn_in.fragment;

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
import com.block.vtCoin.mine.bill.turn_in.MonthBillActivity;
import com.block.vtCoin.mine.bill.turn_in.TurnInBillActivity;
import com.block.vtCoin.mine.bill.turn_in.fragment.adapter.AllAdapter;
import com.block.vtCoin.mine.bill.turn_in.fragment.adapter.BillHeaderAdapter;
import com.block.vtCoin.mine.bill.turn_in.fragment.m.ALLEntity;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import recyclerview.eowise.recycleview.library.OnHeaderClickListener;
import recyclerview.eowise.recycleview.library.StickyHeadersBuilder;
import recyclerview.eowise.recycleview.library.StickyHeadersItemDecoration;

/**
 * @Description
 * @Author lijie
 * @Date 2017/8/18.
 */
public class AllFragment extends Fragment {

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
    private int Page = 1;
    private TurnInBillActivity activity;
    private AllAdapter adapter;
    private StickyHeadersItemDecoration top;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        activity = (TurnInBillActivity) context;
    }

    private List<ALLEntity> data = new ArrayList<>();
    ;
    private List<String> listActions = new ArrayList<>();
    ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bill_all, null);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new AllAdapter(getContext(), data);
        adapter.setItemListener(new AllAdapter.OnItemListener() {
            @Override
            public void onItemClick(View v, int position) {
                startActivity(new Intent(getContext(), BillDetailActivity.class));
            }
        });
        top = new StickyHeadersBuilder()
                .setAdapter(adapter)
                .setRecyclerView(recyclerView)
                .setStickyHeadersAdapter(new BillHeaderAdapter(listActions))
                .setOnHeaderClickListener(new OnHeaderClickListener() {
                    @Override
                    public void onHeaderClick(View view, long headerId) {
                        startActivity(new Intent(getContext(), MonthBillActivity.class));
                    }
                })
                .build();
        recyclerView.addItemDecoration(top);
        recyclerView.setAdapter(adapter);
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refresh();
                        refreshLayout.isNoMore(false);
                        refreshLayout.finishRefreshing();
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                if (page >= 3) {//没有更多数据了
                    refreshLayout.isNoMore(true);
                } else
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            loadMore();
                            refreshLayout.finishLoadmore();
                        }
                    }, 2000);
            }
        });
        refreshLayout.startRefresh();
    }

    private int page = 0;

    private void loadMore() {
        page++;
        for (int i = 0; i < 20; i++) {
            ALLEntity entity = new ALLEntity();
            entity.time = "2017-08-06 15:22";
            entity.amount = 3.7541;
            entity.type = 0;
            if (i <= 5)
                entity.decoration = "2017年8月";
            else if (i <= 10)
                entity.decoration = "2017年7月";
            else if (i <= 13)
                entity.decoration = "2017年6月";
            else if (i <= 15)
                entity.decoration = "2017年5月";
            else
                entity.decoration = "2017年4月";
            data.add(entity);
        }
        setPullAction(data);
        adapter.notifyDataSetChanged();
    }

    private void refresh() {
        page = 0;
        data.clear();
        for (int i = 0; i < 20; i++) {
            ALLEntity entity = new ALLEntity();
            entity.time = "2017-08-06 15:22";
            entity.amount = 3.7541;
            entity.type = 0;
            if (i <= 5)
                entity.decoration = "2017年8月";
            else if (i <= 10)
                entity.decoration = "2017年7月";
            else if (i <= 13)
                entity.decoration = "2017年6月";
            else if (i <= 15)
                entity.decoration = "2017年5月";
            else
                entity.decoration = "2017年4月";
            data.add(entity);
        }
        setPullAction(data);
        adapter.notifyDataSetChanged();
    }

    private void setPullAction(List<ALLEntity> data) {
        for (int i = 0; i < data.size(); i++) {
            String month = data.get(i).decoration;
            listActions.add(month);
        }
    }

    @Override
    public void onDestroyView() {
        ButterKnife.unbind(this);
        super.onDestroyView();
    }

}
