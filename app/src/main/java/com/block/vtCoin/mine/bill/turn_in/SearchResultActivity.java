package com.block.vtCoin.mine.bill.turn_in;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.vtCoin.R;
import com.block.vtCoin.main.BaseActivity;
import com.block.vtCoin.mine.bill.turn_in.adapter.SearchResultAdapter;
import com.block.vtCoin.mine.bill.turn_in.fragment.m.ALLEntity;
import com.block.vtCoin.widget.title.NormalTitleBar;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;

/**
 * @Description
 * @Author lijie
 * @Date 2017/8/18.
 */
public class SearchResultActivity extends BaseActivity{

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
    private SearchResultAdapter adapter;
    private List<ALLEntity> data=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_btc);
        setUltimateBar(R.color.blue1);
        initView();
    }

    /**
     * 获取标题组件 为空则不带有标题
     *
     * @return view
     */
    @Override
    public View getTitleBar() {
        NormalTitleBar normalTitle = getNormalTitleBar().setTitle("搜索结果").setRightDrawable(R.mipmap.wallet_bottom_search).setRightListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toActivity(SearchBillActivity.class);
            }
        });
        return normalTitle;
    }
    @Override
    protected List<BasePresenter> getPresenter(List<BasePresenter> presenters) {
        return null;
    }

    private void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new SearchResultAdapter(this, data);
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
            entity.time="2017-08-06 15:22";
            entity.amount=3.7541;
            entity.type=0;
            data.add(entity);
        }
        adapter.notifyDataSetChanged();
    }
}
