package com.block.vtCoin.mine.setting.receipt_setting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.vtCoin.R;
import com.block.vtCoin.main.BaseActivity;
import com.block.vtCoin.mine.issue_buy.pay_way.EditPayActivity;
import com.block.vtCoin.widget.title.NormalTitleBar;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import butterknife.Bind;

public class ReceiptSettingActivity extends BaseActivity {
    @Bind(R.id.list_view)
    ListView listView;
    private PayWayAdapter mAdapter;
    private List<String> mData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        setUltimateBar(R.color.blue1);
        setBackground(R.color.gray7);
        initView();
    }

    private void initView() {
        mData.add(getString(R.string.alipay));
        mData.add(getString(R.string.weixin));
        mData.add(getString(R.string.bank_card));

        mAdapter = new PayWayAdapter(this);
        listView.setAdapter(mAdapter);
        mAdapter.setData(mData);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                toEdit(position);
            }
        });
    }

    private void toEdit(int position) {
        startActivity(new Intent(this,EditPayActivity.class).putExtra("initWay",mData.get(position)));
    }

    @Override
    public View getTitleBar() {
        NormalTitleBar normalTitleBar = getNormalTitleBar().setTitle(getString(R.string.receipt_set)).setRightDrawable(R.mipmap.release_plus);
        normalTitleBar.setRightListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toActivity(EditPayActivity.class);
            }
        });
        return normalTitleBar;
    }

    @Override
    protected List<BasePresenter> getPresenter(List<BasePresenter> presenters) {
        return null;
    }

    public class PayWayAdapter extends BaseAdapter {
        Context mContext;
        List<String> mDatas;
        private HashMap<Integer, Boolean> isSelected;

        public PayWayAdapter(Context context) {
            this.mContext = context;
        }

        public void setData(List<String> mDatas) {
            this.mDatas = mDatas;
            notifyDataSetChanged();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHold viewHold = null;
            String name = mDatas.get(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_pay_way, parent, false);
                viewHold = new ViewHold();
                viewHold.payName = (TextView) convertView.findViewById(R.id.pay_name);
                convertView.setTag(viewHold);
            } else {
                viewHold = (ViewHold) convertView.getTag();
            }
            viewHold.payName.setText(name.trim());
            viewHold.payName.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.right_02, 0);
            return convertView;
        }


        @Override
        public int getCount() {
            if (mDatas == null) {
                return 0;
            }
            return mDatas.size();
        }

        @Override
        public Object getItem(int position) {
            if (mDatas != null && position < mDatas.size()) {
                return mDatas.size();
            } else {
                return null;
            }
        }

        @Override
        public long getItemId(int position) {
            return position;
        }
    }

    class ViewHold {
        TextView payName;
    }
}
