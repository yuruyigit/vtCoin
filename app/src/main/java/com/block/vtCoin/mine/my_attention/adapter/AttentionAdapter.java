package com.block.vtCoin.mine.my_attention.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.block.vtCoin.R;
import com.block.vtCoin.entity.AttentionEntity;
import com.block.vtCoin.widget.imageview.CircleImageView;
import com.bumptech.glide.Glide;
import com.zhy.android.percent.support.PercentRelativeLayout;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by liubin on 2017/11/6.
 */

public class AttentionAdapter extends Adapter<AttentionAdapter.ViewHolder> {

    private Context mContext;
    private List<AttentionEntity.DataBean> mData;

    public AttentionAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<AttentionEntity.DataBean> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_my_attention, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        AttentionEntity.DataBean attentionEntity = mData.get(position);
//        Glide.with(mContext).load(attentionEntity.imagePath).into(holder.avatar);
//        holder.tvName.setText(attentionEntity.name);
//        holder.tvScore.setText(attentionEntity.score + "");
//        holder.tvCooperate.setText(attentionEntity.cooperate + "");
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(v, position);
                }
            }
        });
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item)
        PercentRelativeLayout item;
        @Bind(R.id.avatar)
        CircleImageView avatar;
        @Bind(R.id.tv_name)
        TextView tvName;
        @Bind(R.id.tv_score)
        TextView tvScore;
        @Bind(R.id.tv_cooperate)
        TextView tvCooperate;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
