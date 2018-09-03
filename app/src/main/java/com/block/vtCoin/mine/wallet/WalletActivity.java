package com.block.vtCoin.mine.wallet;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.block.net.mvp.presenter.BasePresenter;
import com.block.net.util.L;
import com.block.vtCoin.R;
import com.block.vtCoin.entity.WalletEntity;
import com.block.vtCoin.main.BaseActivity;
import com.block.vtCoin.mine.buy_coin.TurnInActivity;
import com.block.vtCoin.mine.sale_coin.TurnOutActivity;
import com.block.vtCoin.mine.wallet.p.CapitalPresenter;
import com.block.vtCoin.mine.wallet.v.CapitalViewImpl;
import com.block.vtCoin.request.ApiAction;
import com.block.vtCoin.request.MyModel;
import com.block.vtCoin.widget.title.NormalTitleBar;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WalletActivity extends BaseActivity {

    @Bind(R.id.list_view)
    ListView listView;
    @Bind(R.id.refresh_layout)
    TwinklingRefreshLayout refreshLayout;
    private WalletAdapter mAdapter;
    private CapitalPresenter capitalPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        ButterKnife.bind(this);
        setUltimateBar(R.color.blue1);
        initView();
    }

    private void initView() {
        mAdapter = new WalletAdapter(this);
        listView.setAdapter(mAdapter);
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                        capitalPresenter.getCapital();
            }
            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
            }
        });
        refreshLayout.startRefresh();
    }

    @Override
    public View getTitleBar() {
        NormalTitleBar normalTitleBar = getNormalTitleBar().setTitle(getString(R.string.wallet));
        return normalTitleBar;
    }

    @Override
    protected List<BasePresenter> getPresenter(List<BasePresenter> presenters) {
        capitalPresenter = new CapitalPresenter();
        capitalPresenter.setModelView(new MyModel<WalletEntity>(), new CapitalViewImpl(this, ApiAction.Account_Capitals));
        presenters.add(capitalPresenter);
        return presenters;
    }

    public void setCapital(WalletEntity data) {
        L.i("setCapital");
        mAdapter.setData(data.getData());
        refreshLayout.isNoMore(false);
        refreshLayout.finishRefreshing();
    }

    public class WalletAdapter extends BaseAdapter {
        Context mContext;
        List<WalletEntity.DataBean> mData;


        public WalletAdapter(Context context) {
            this.mContext = context;
        }

        public void setData(List<WalletEntity.DataBean> data) {
            this.mData = data;
            notifyDataSetChanged();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHold = null;
            final WalletEntity.DataBean data = mData.get(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_wallet, parent, false);
                viewHold = new ViewHolder(convertView);
                convertView.setTag(viewHold);
            } else {
                viewHold = (ViewHolder) convertView.getTag();
            }
            viewHold.payName.setText(data.getCoinType());
            viewHold.available.setText(data.getAvalBanlance() + "");
            viewHold.freeze.setText(data.getFreezeBanlance() + "");
            viewHold.turnIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("from", 1);
                    map.put("coinType", data.getCoinType());
                    toActivity(TurnInActivity.class, map);
                }
            });
            viewHold.turnOut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("coinType", data.getCoinType());
                    toActivity(TurnOutActivity.class, map);
                }
            });
            return convertView;
        }

        @Override
        public int getCount() {
            if (mData == null) {
                return 0;
            }
            return mData.size();
        }

        @Override
        public Object getItem(int position) {
            if (mData != null && position < mData.size()) {
                return mData.size();
            } else {
                return null;
            }
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        class ViewHolder {
            @Bind(R.id.pay_name)
            TextView payName;
            @Bind(R.id.available)
            TextView available;
            @Bind(R.id.freeze)
            TextView freeze;
            @Bind(R.id.turn_in)
            TextView turnIn;
            @Bind(R.id.turn_out)
            TextView turnOut;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }
}
