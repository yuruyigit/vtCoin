package com.block.vtCoin.mine.my_attention;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.block.net.mvp.presenter.BasePresenter;
import com.block.vtCoin.R;
import com.block.vtCoin.entity.Page;
import com.block.vtCoin.main.BaseActivity;
import com.block.vtCoin.entity.AttentionEntity;
import com.block.vtCoin.mine.my_attention.adapter.AttentionAdapter;
import com.block.vtCoin.mine.my_attention.p.AttentionPresenter;
import com.block.vtCoin.mine.my_attention.v.AttentionViewImpl;
import com.block.vtCoin.request.ApiAction;
import com.block.vtCoin.request.MyModel;
import com.block.vtCoin.widget.title.NormalTitleBar;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;

public class MyAttentionActivity extends BaseActivity {
    @Bind(R.id.tv_no_data)
    TextView tvNoData;
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    @Bind(R.id.refresh_layout)
    TwinklingRefreshLayout refreshLayout;
    private AttentionPresenter attentionPresenter;
    private AttentionAdapter adapter;
    private List<AttentionEntity> mData = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        ButterKnife.bind(this);
        setUltimateBar(R.color.blue1);
        initView();
    }

    private void initView() {
        adapter = new AttentionAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new AttentionAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                toEdit(position);
            }
        });
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                attentionPresenter.getAttention(new Page(1, 10));
            }
            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
            }
        });
        refreshLayout.startRefresh();
    }

    private void toEdit(int position) {
        toActivity(MyHomeActivity.class);
    }

    @Override
    public View getTitleBar() {
        NormalTitleBar normalTitleBar = getNormalTitleBar().setTitle(getString(R.string.my_attention));
        return normalTitleBar;
    }

    @Override
    protected List<BasePresenter> getPresenter(List<BasePresenter> presenters) {
        attentionPresenter = new AttentionPresenter();
        attentionPresenter.setModelView(new MyModel<AttentionEntity>(), new AttentionViewImpl(this, ApiAction.Preference_ListFollowUser));
        presenters.add(attentionPresenter);
        return presenters;
    }

    public void setAttention(AttentionEntity entity) {
        refreshLayout.finishRefreshing();
        if (entity.getData().getData() == null) {
            tvNoData.setVisibility(View.VISIBLE);
            refreshLayout.setEnableLoadmore(false);
        } else {
//            adapter.setData(entity.getData().getData());
            if (entity.getData().getTotal() > 10) {
                refreshLayout.setEnableLoadmore(true);
            } else
                refreshLayout.setEnableLoadmore(false);
        }
    }
}
