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
import com.block.net.util.L;
import com.block.vtCoin.constant.Constant;
import com.block.vtCoin.R;
import com.block.vtCoin.main.BaseActivity;
import com.block.vtCoin.util.SPUtils;
import com.block.vtCoin.widget.dialog.NormalDialog;
import com.block.vtCoin.widget.title.NormalTitleBar;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;

public class PayWayActivity extends BaseActivity {

    @Bind(R.id.list_view)
    ListView listView;
    private PayWayAdapter mAdapter;
    /*保存的支付list*/
    private List<String> saveList=new ArrayList<>();
    /*选择的支付list*/
    private List<String> chooseList = new ArrayList<>();
    private String initWay = "";
    private Map<Integer,Boolean> checkMap=new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        setUltimateBar(R.color.blue1);
        setBackground(R.color.gray7);
        initView();
    }


    public void toEditPay() {
        Intent it = new Intent(this, EditPayActivity.class);
        it.putExtra("initWay", getString(R.string.weixin));
        startActivityForResult(it, RESULT_FIRST_USER);
    }

    public void showDialog() {
        NormalDialog dialog = new NormalDialog(this, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toEditPay();
            }
        });
        dialog.setTitle(getString(R.string.Warm_prompt));
        dialog.setMsg(getString(R.string.have_not_set_pay_way));
        dialog.setRightText(getString(R.string.setting_right_now));
        dialog.show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_FIRST_USER && resultCode == RESULT_OK) {
            String addName = data.getStringExtra("addName");
            initWay = addName;
            if (!saveList.contains(addName)) {
                saveList.add(addName.trim());
                chooseList.add(addName);
                checkMap.put(saveList.size()-1,true);
                mAdapter.setData(saveList);
            }
        }
    }

    private void initView() {
        listView.setBackgroundResource(R.color.gray6);
        initWay = "微信";
        mAdapter = new PayWayAdapter(this);
        listView.setAdapter(mAdapter);
        checkMap.put(0,true);
        if (saveList.size() == 0) {
            showDialog();
        } else {
            mAdapter.setData(saveList);
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ViewHold viewHold = (ViewHold) view.getTag();
                if(checkMap.get(position)){//已经被点击
                    mAdapter.setChecked(viewHold.payName,false);
                    checkMap.put(position,false);
                    chooseList.remove(saveList.get(position));
                }else {
                    mAdapter.setChecked(viewHold.payName,true);
                    checkMap.put(position,true);
                    chooseList.add(saveList.get(position));
                }
            }
        });

    }

    @Override
    public View getTitleBar() {
        NormalTitleBar normalTitleBar = getNormalTitleBar().setTitle(getString(R.string.pay_way)).setRightDrawable(R.mipmap.release_plus);
        normalTitleBar.setRightListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toEditPay();
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

    public void back() {
        /*把结果带回去*/
        Intent intent = getIntent();
        intent.putExtra("chooseList", chooseList.toString());
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
            if (checkMap.get(position)!=null&&checkMap.get(position)) {
                setChecked(viewHold.payName, true);
                checkMap.put(position,true);
            } else {
                setChecked(viewHold.payName, false);
                checkMap.put(position,false);
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
