package com.block.vtCoin.mine.issue_buy.pay_way;

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
import com.block.vtCoin.widget.title.NormalTitleBar;
import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;

public class PayWayActivity1 extends BaseActivity {

    @Bind(R.id.list_view)
    ListView listView;
    private List<String> mData;
    private ChoosePayAdapter mAdapter;
    private String chooseWay;
    private int selectPosition=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        setUltimateBar(R.color.blue1);
        init();
    }

    private void init() {
        chooseWay = "微信";
        mData = new ArrayList<>();
        mData.add("国内银行转账");
        mData.add("SEPA（欧盟）银行转账");
        mData.add("国际电汇");
        mData.add("支付宝");
        mData.add("微信");
        mData.add("Paypal");
        mData.add("Haicash");
        mData.add("MobilePayFi");
        mData.add("其它");
        mAdapter = new ChoosePayAdapter(this);
        listView.setAdapter(mAdapter);
        mAdapter.setData(mData);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectPosition=position;
                mAdapter.notifyDataSetChanged();
                chooseWay = mData.get(position);
            }
        });
    }


    /**
     * @return
     */
    @Override
    public View getTitleBar() {
        NormalTitleBar normalTitleBar = getNormalTitleBar().setTitle(getString(R.string.pay_way)).setRightText(getString(R.string.save));
        normalTitleBar.setRightListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                intent.putExtra("chooseWay", chooseWay);
                PayWayActivity1.this.setResult(RESULT_OK, intent);
                finish();
            }
        });
        return normalTitleBar;
    }

    @Override
    protected List<BasePresenter> getPresenter(List<BasePresenter> presenters) {
        return null;
    }


    private class ChoosePayAdapter extends BaseAdapter {
        Context mContext;
        List<String> saveList;

        public ChoosePayAdapter(Context context) {
            this.mContext = context;
        }

        public void setData(List<String> saveList) {
            this.saveList = saveList;
            notifyDataSetChanged();
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHold viewHold = null;
            String name = saveList.get(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_pay_way,null);
                viewHold = new ViewHold();
                viewHold.payName = (TextView) convertView.findViewById(R.id.pay_name);
                convertView.setTag(viewHold);
            } else {
                viewHold = (ViewHold) convertView.getTag();
            }
            viewHold.payName.setText(name);

            if (saveList.get(position).equals(chooseWay)||selectPosition==position) {
               setChecked(viewHold.payName,true);
            }else
                setChecked(viewHold.payName,false);
            return convertView;
        }

        public void setChecked(TextView tv,boolean isChecked){
            if(isChecked){
                tv.setCompoundDrawablesWithIntrinsicBounds(0,0,R.mipmap.release_tick,0);
                tv.setTextColor(getResources().getColor(R.color.blue1));
            }else {
                tv.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                tv.setTextColor(getResources().getColor(R.color.black1));
            }
        }
        @Override
        public int getCount() {
            if (saveList == null) {
                return 0;
            }
            return saveList.size();
        }

        @Override
        public Object getItem(int position) {
            if (saveList != null && position < saveList.size()) {
                return saveList.size();
            } else {
                return null;
            }
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        class ViewHold {
            TextView payName;
        }
    }
}
