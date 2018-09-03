package com.block.vtCoin.mine.sale_manager.fragment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.block.vtCoin.R;
import com.block.vtCoin.mine.sale_manager.fragment.m.ALLEntity;
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
        View view = LayoutInflater.from(context).inflate(R.layout.item_buy_manager, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        ALLEntity entity = data.get(position);
        if (entity.type == 0) {
            holder.type.setText(context.getString(R.string.releasing));
            holder.type.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.administration_btb, 0, 0, 0);
        }
        holder.time.setText(entity.time);
        holder.amount.setText(entity.amount);
        holder.buyNumber.setText(entity.buyNumber);
        holder.needNumber.setText(entity.needNumber);
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null!=listener){
                    listener.onItemClick(v,position);
                }
            }
        });
    }

    public OnItemListener listener;
    public void setItemListener(OnItemListener itemListener){
        this.listener=itemListener;
    }
   public interface OnItemListener{
       void onItemClick(View v, int position);
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item)
        PercentLinearLayout item;
        @Bind(R.id.type)
        TextView type;
        @Bind(R.id.name)
        TextView name;
        @Bind(R.id.time)
        TextView time;
        @Bind(R.id.amount)
        TextView amount;
        @Bind(R.id.buy_number)
        TextView buyNumber;
        @Bind(R.id.need_number)
        TextView needNumber;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

