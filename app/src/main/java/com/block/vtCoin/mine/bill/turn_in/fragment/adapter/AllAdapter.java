package com.block.vtCoin.mine.bill.turn_in.fragment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.block.vtCoin.R;
import com.block.vtCoin.mine.bill.turn_in.fragment.m.ALLEntity;
import com.zhy.android.percent.support.PercentLinearLayout;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @Description
 * @Author lijie
 * @Date 2017/7/18.
 */
public class AllAdapter extends RecyclerView.Adapter<AllAdapter.ViewHolder> {
    private Context context;
    private List<ALLEntity> mData;

    public AllAdapter(Context context, List<ALLEntity> data) {
        this.context = context;
        this.mData = data;
        setHasStableIds(true);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_trun_in_bill, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        ALLEntity entity = mData.get(position);
        if (entity.type == 0) {
            holder.tvName.setText("转入莱特币");
            holder.ivLogo.setImageResource(R.mipmap.administration_ltc);
        }
        holder.tvTime.setText(parseTime(entity.time));
        holder.tvAmount.setText("+" + entity.amount + "LTC");
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != listener) {
                    listener.onItemClick(v, position);
                }
            }
        });
    }

    public String parseTime(String time) {//"2017-08-06 15:22"
        StringBuffer sb = new StringBuffer();
        String s1 = time.substring(5, time.length());
        String[] split = s1.split(" ");
        sb = sb.append(split[0]).append("\n").append(split[1]);
        return sb.toString();
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
        @Bind(R.id.iv_logo)
        ImageView ivLogo;
        @Bind(R.id.tv_name)
        TextView tvName;
        @Bind(R.id.tv_amount)
        TextView tvAmount;
        @Bind(R.id.item)
        PercentLinearLayout item;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setDataList(List<ALLEntity> datas) {
        notifyDataSetChanged();
    }
    public void clearDatas() {
        mData.clear();
        notifyDataSetChanged();
    }
    /**
     * 添加数据到前面
     */
    public void addItemsAtFront(List<ALLEntity> datas) {
        if (null == datas) return;
        mData.addAll(0, datas);
        notifyDataSetChanged();
    }
    /**
     * 添加数据到尾部
     */
    public void addItems() {
        notifyDataSetChanged();
    }
    /**
     * 添加单条数据
     */
    public void addItem(ALLEntity data) {
        if (null == data) return;
        mData.add(data);
        notifyDataSetChanged();
    }
    /**
     * 删除单条数据
     */
    public void deletItem(ALLEntity data) {
        mData.remove(data);
        notifyDataSetChanged();
    }
}

