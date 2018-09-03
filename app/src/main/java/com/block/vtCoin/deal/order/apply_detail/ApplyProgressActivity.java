package com.block.vtCoin.deal.order.apply_detail;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.vtCoin.R;
import com.block.vtCoin.main.BaseActivity;
import com.block.vtCoin.widget.dialog.NormalDialog;
import com.block.vtCoin.widget.time_line.TimeLineMarkerView;
import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;
import butterknife.OnClick;

public class ApplyProgressActivity extends BaseActivity {

    @Bind(R.id.apply_listView)
    ListView applyListView;
    @Bind(R.id.tv_apply_content)
    TextView tvApplyContent;
    private ApplyProgressAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_progress);
        setUltimateBar(R.color.blue1);
        initView();
    }

    @Override
    protected List<BasePresenter> getPresenter(List<BasePresenter> presenters) {
        return null;
    }


    private void initView() {
        List<ApplyEntity> datas=new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            ApplyEntity ae=new ApplyEntity();
            ae.time="2017-06-22  18:32";
            ae.content="买方提出仲裁";
            datas.add(ae);
        }
        adapter=new ApplyProgressAdapter(this,datas);
        applyListView.setAdapter(adapter);
    }
    public class ApplyEntity{
        String time;
        String content;
    }

    @Override
    public View getTitleBar() {
        return getNormalTitleBar().setTitle(getString(R.string.apply_detail));
    }

    public void ShowTip() {
        NormalDialog dialog = new NormalDialog(this, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTip("您已取消仲裁");
            }
        });
        dialog.setTitle(getString(R.string.Warm_prompt));
        dialog.setMsg(getString(R.string.tip_cancel_apply));
        dialog.show();
    }

    /**
     c* @param view
     */
    @OnClick({R.id.order_detail, R.id.cancel_apply})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.order_detail:
                toActivity(ApplyOrderDetailActivity.class);
                break;
            case R.id.cancel_apply:
                ShowTip();
                break;
        }
    }

    public class ApplyProgressAdapter extends BaseAdapter {
        Context mContext;
        List<ApplyEntity> mData;
        public ApplyProgressAdapter(Context context,List<ApplyEntity> datas) {
            this.mContext = context;
            this.mData=datas;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHold viewHold = null;
            ApplyEntity apply = mData.get(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_apply_progress, parent, false);
                viewHold = new ViewHold();
                viewHold.timeLine= (TimeLineMarkerView) convertView.findViewById(R.id.time_line);
                viewHold.tvTime = (TextView) convertView.findViewById(R.id.tv_time);
                viewHold.tvContent = (TextView) convertView.findViewById(R.id.tv_content);
                convertView.setTag(viewHold);
            } else {
                viewHold = (ViewHold ) convertView.getTag();
            }
            if(position==3){
                viewHold.timeLine.setEndLine(null);
                viewHold.timeLine.setMarkerDrawable(getResources().getDrawable(R.mipmap.arbitration_chno_02));
            }else if(position==0){
                viewHold.timeLine.setBeginLine(null);
                viewHold.timeLine.setMarkerDrawable(getResources().getDrawable(R.mipmap.arbitration_chno));
            }else {
                viewHold.timeLine.setMarkerDrawable(getResources().getDrawable(R.mipmap.arbitration_chno_01));
            }
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
    }

    class ViewHold {
        TimeLineMarkerView timeLine;
        TextView tvTime,tvContent;
    }

}
