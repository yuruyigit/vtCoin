package com.block.vtCoin.mine.user.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.block.vtCoin.R;
import com.block.vtCoin.entity.LoginHistoryEntity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginHistoryAdapter extends RecyclerView.Adapter<LoginHistoryAdapter.ViewHolder> {
    private Context context;
    private List<LoginHistoryEntity.DataBean.HisBean> mData;

    public LoginHistoryAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<LoginHistoryEntity.DataBean.HisBean> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_login_history, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        LoginHistoryEntity.DataBean.HisBean hisBean = mData.get(position);
        String time = hisBean.getDate();
        String[] split = time.split("\\.");
        time = split[0];
        if (time.contains("T"))
            time = time.replace("T", " ");
        holder.tvTime.setText(time);
        holder.tvIp.setText(hisBean.getIp());
        if (hisBean.getType() == null) {
            holder.tvWay.setText("--");
        } else
            holder.tvWay.setText(hisBean.getType() + "");
    }

    public OnItemListener listener;

    public void setItemListener(OnItemListener itemListener) {
        this.listener = itemListener;
    }

    public interface OnItemListener {
        void onItemClick(View v, int position);
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_time)
        TextView tvTime;
        @Bind(R.id.tv_way)
        TextView tvWay;
        @Bind(R.id.tv_ip)
        TextView tvIp;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

