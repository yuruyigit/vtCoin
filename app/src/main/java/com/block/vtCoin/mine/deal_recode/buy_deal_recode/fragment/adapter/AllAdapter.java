package com.block.vtCoin.mine.deal_recode.buy_deal_recode.fragment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.block.vtCoin.R;
import com.block.vtCoin.mine.deal_recode.buy_deal_recode.fragment.m.ALLEntity;
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
        View view = LayoutInflater.from(context).inflate(R.layout.item_buy_deal_recode, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        ALLEntity entity = data.get(position);
        if (entity.type == 0) {
            holder.payWay.setText("微信");
        }
        holder.name.setText(entity.name);
        holder.money.setText(entity.money);
        holder.amount.setText(entity.amount);
        holder.price.setText(entity.price);
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
        @Bind(R.id.name)
        TextView name;
        @Bind(R.id.money)
        TextView money;
        @Bind(R.id.amount)
        TextView amount;
        @Bind(R.id.price)
        TextView price;
        @Bind(R.id.pay_way)
        TextView payWay;
        @Bind(R.id.item)
        PercentLinearLayout item;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

