package com.block.vtCoin.mine.bill.turn_in.fragment_month;

import android.content.Context;
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
import com.block.vtCoin.mine.bill.turn_in.fragment_month.adapter.AllAdapter;
import com.block.vtCoin.mine.bill.turn_in.fragment_month.m.ALLEntity;
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
public class AllFragment extends Fragment{
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
    private MonthBillActivity activity;
    private AllAdapter adapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MonthBillActivity) context;
    }
    private List<ALLEntity> data=new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_btc, null);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }
    private void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new AllAdapter(getContext(), data);
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
        for (int i = 0; i <10 ; i++) {
            ALLEntity entity=new ALLEntity();
            entity.time="本月";
            entity.moneyIn=100.25;
            entity.moneyOut=21.02;
            entity.type=0;
            data.add(entity);
        }
        adapter.notifyDataSetChanged();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
