package com.block.vtCoin.advisory.adapter;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.block.vtCoin.R;
import com.block.vtCoin.advisory.m.VRecyclerEntity;
import com.bumptech.glide.Glide;

import java.util.List;
public class VAdapter extends RecyclerView.Adapter<VAdapter.ViewHolder> {
    private Context context;
    private List<VRecyclerEntity> data;

    public VAdapter(Context context, List<VRecyclerEntity> data) {
        this.context = context;
        this.data = data;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_v_recycler, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        VRecyclerEntity vRecyclerEntity = data.get(position);
        Glide.with(context).load(vRecyclerEntity.imagePath).into(holder.image);
        holder.tvTitle.setText(vRecyclerEntity.title);
        holder.tvName.setText(vRecyclerEntity.name);
        holder.tvTime.setText(vRecyclerEntity.time);
    }
    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView tvTitle,tvName,tvTime;
        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
            tvTitle= (TextView) itemView.findViewById(R.id.tv_title);
            tvName= (TextView) itemView.findViewById(R.id.tv_name);
            tvTime= (TextView) itemView.findViewById(R.id.tv_time);
        }
    }
}

