package com.block.vtCoin.mine.setting.trader_ad;

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
import com.block.vtCoin.constant.Constant;
import com.block.vtCoin.R;
import com.block.vtCoin.main.BaseActivity;
import com.block.vtCoin.widget.title.NormalTitleBar;
import java.util.HashMap;
import java.util.List;
import butterknife.Bind;

public class TraderAdActivity extends BaseActivity {

    @Bind(R.id.list_view)
    ListView listView;
    private PayWayAdapter mAdapter;
    /*保存的支付list*/
    private List<String> saveList;
    private int selectPosition=0;
    private String currentName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        setUltimateBar(R.color.blue1);
        setBackground(R.color.gray7);
        initView();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_FIRST_USER && resultCode == RESULT_OK) {
            String addName = data.getStringExtra("addName");
            currentName = addName;
            if (!saveList.contains(addName)) {
                saveList.add(addName.trim());
//                SPUtils.putList(this,Constant.TRADER_AD,saveList);
                selectPosition=saveList.size()-1;
                mAdapter.setData(saveList);
            }
        }
    }

    private void initView() {
//        saveList = SPUtils.getList(this, Constant.TRADER_AD, "");
        mAdapter = new PayWayAdapter(this);
        listView.setAdapter(mAdapter);
        if (saveList.size() == 0) {
            saveList.add("工作日收币");
            saveList.add("合作愉快");
            saveList.add("快速交易");
            mAdapter.setData(saveList);
        } else {
            mAdapter.setData(saveList);
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position!=selectPosition){
                    selectPosition=position;
                    currentName=saveList.get(position);
                    mAdapter.notifyDataSetChanged();
                }

            }
        });
        currentName=saveList.get(selectPosition);
    }

    @Override
    public View getTitleBar() {
        NormalTitleBar normalTitleBar = getNormalTitleBar().setTitle(getString(R.string.trader_ad)).setRightDrawable(R.mipmap.release_plus);
        normalTitleBar.setRightListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toAddAd();
            }
        });
        normalTitleBar.setLeftListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });
        return normalTitleBar;
    }

    public void toAddAd() {
        Intent it = new Intent(this, AddAdActivity.class);
        startActivityForResult(it, RESULT_FIRST_USER);
    }

    public void back() {
        /*保存自己的所有支付方式*/
//        SPUtils.putList(this, Constant.TRADER_AD, saveList);
        /*把结果带回去*/
        Intent intent = getIntent();
        intent.putExtra("chooseName", currentName);
        this.setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    protected List<BasePresenter> getPresenter(List<BasePresenter> presenters) {
        return null;
    }


    public class PayWayAdapter extends BaseAdapter {
        Context mContext;
        List<String> saveList;
        private  HashMap<Integer,Boolean> isSelected;

        public PayWayAdapter(Context context) {
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
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_pay_way, parent, false);
                viewHold = new ViewHold();
                viewHold.payName = (TextView) convertView.findViewById(R.id.pay_name);
                convertView.setTag(viewHold);
            } else {
                viewHold = (ViewHold) convertView.getTag();
            }
            viewHold.payName.setText(name.trim());
            if (selectPosition==position) {
                setChecked(viewHold.payName, true);
            } else {
                setChecked(viewHold.payName, false);
            }
            return convertView;
        }

        public void setChecked(TextView tv, Boolean isChecked) {
            if (isChecked) {
                tv.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.release_lected_click, 0, 0, 0);
            } else
                tv.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.release_chec, 0, 0, 0);
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
    }

    class ViewHold {
        TextView payName;
    }
}
