package com.block.vtCoin.deal.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.block.vtCoin.R;
import com.block.vtCoin.entity.DealPriceEntity;

import java.util.List;

/**
 * @Description
 * @Author lijie
 * @Date 2017/7/18.
 */
public class DealAdapter extends RecyclerView.Adapter<DealAdapter.ViewHolder> {

    private Context context;
    private List<DealPriceEntity> data;

    public DealAdapter(Context context, List<DealPriceEntity> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_deal, parent, false);
        return new ViewHolder(view);
    }

    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.dealItemLy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView coinType;
        TextView cionMoney;
        TextView coinId;
        TextView coinLimit;
        TextView coinDealNum;
        TextView coinDealEvaluate;
        TextView coinDealWay;
        LinearLayout dealItemLy;

        public ViewHolder(View itemView) {
            super(itemView);
            dealItemLy = (LinearLayout) itemView.findViewById(R.id.deal_item_ly);
            coinType = (TextView) itemView.findViewById(R.id.coin_type);
            cionMoney = (TextView) itemView.findViewById(R.id.coin_money);
            coinId = (TextView) itemView.findViewById(R.id.coin_id);
            coinLimit = (TextView) itemView.findViewById(R.id.coin_limit);
            coinDealNum = (TextView) itemView.findViewById(R.id.coin_deal_num);
            coinDealEvaluate = (TextView) itemView.findViewById(R.id.coin_deal_evaluate);
            coinDealWay = (TextView) itemView.findViewById(R.id.coin_deal_way);


        }
    }
}

