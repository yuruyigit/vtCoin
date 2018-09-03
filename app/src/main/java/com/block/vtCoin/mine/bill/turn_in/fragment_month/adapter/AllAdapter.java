package com.block.vtCoin.mine.bill.turn_in.fragment_month.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.block.vtCoin.R;
import com.block.vtCoin.mine.bill.turn_in.fragment_month.m.ALLEntity;
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
    private List<ALLEntity> data;

    public AllAdapter(Context context, List<ALLEntity> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_month_bill, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        ALLEntity entity = data.get(position);
        if (entity.type == 0) {
            holder.tvName.setText("LTC莱特币");
            holder.tvName.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.administration_ltc, 0, 0, 0);
        }
        if(position%2==0){
            holder.tvTime.setText(entity.time);
            holder.tvTime.setVisibility(View.VISIBLE);
        }else holder.tvTime.setVisibility(View.GONE);
        holder.moneyIn.setText(entity.moneyIn + "");
        holder.moneyOut.setText(entity.moneyOut + "");
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != listener) {
                    listener.onItemClick(v, position);
                }
            }
        });
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
        return data != null ? data.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_time)
        TextView tvTime;
        @Bind(R.id.tv_name)
        TextView tvName;
        @Bind(R.id.money_in)
        TextView moneyIn;
        @Bind(R.id.money_out)
        TextView moneyOut;
        @Bind(R.id.item)
        PercentLinearLayout item;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

