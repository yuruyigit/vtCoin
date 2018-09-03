package com.block.vtCoin.deal.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.block.net.util.L;
import com.block.vtCoin.R;
import com.block.vtCoin.entity.ChatTokenEntity;
import com.block.vtCoin.entity.DealPriceEntity;
import com.block.vtCoin.entity.QueryOrder;
import com.block.vtCoin.util.UnitUtil;

import java.util.List;

public class DealAdapter1 extends RecyclerView.Adapter<DealAdapter1.ViewHolder> {
    private Context context;
    private List<QueryOrder.DataBean.OrdersBean> mOrders;
    private boolean isFirst;

    public DealAdapter1(Context context) {
        this.context = context;
    }

    public void setData(List<QueryOrder.DataBean.OrdersBean> orders,boolean isFirst) {
        this.mOrders = orders;
        this.isFirst=isFirst;
        this.notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_deal1, parent, false);
        return new ViewHolder(view);
    }

    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(String orderId);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final QueryOrder.DataBean.OrdersBean ordersBean = mOrders.get(position);
        holder.tvNickName.setText(ordersBean.getNickName());
        holder.tvDealNum.setText(ordersBean.getDealSum() + "");
        holder.tvScore.setText(UnitUtil.formatOne(ordersBean.getScroeAve()) + "");
        holder.tvPayment.setText(getPayment(ordersBean.getPayment()));
        holder.tvType.setText(ordersBean.getCurt());
        if (ordersBean.isOnline())
            holder.tvOnline.setVisibility(View.VISIBLE);
        else
            holder.tvOnline.setVisibility(View.GONE);
        if (isFirst) {//第一次，先给之前的赋值，现在的尚未赋值
            holder.tvPrice.setText(UnitUtil.formatTwo(ordersBean.getBeforeBuyPrice() * (ordersBean.getFee_Rate() + 1)));
            holder.tvPrice.setTextColor(context.getResources().getColor(R.color.black1));
            holder.tvPrice.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        } else if (ordersBean.getBeforeBuyPrice() < ordersBean.getNowBuyPrice()) {//涨了
            holder.tvPrice.setText(UnitUtil.formatTwo(ordersBean.getNowBuyPrice() * (ordersBean.getFee_Rate() + 1)));
            holder.tvPrice.setTextColor(context.getResources().getColor(R.color.red));
            holder.tvPrice.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.quotation_rise_02, 0, 0, 0);
        } else if (ordersBean.getBeforeBuyPrice() > ordersBean.getNowBuyPrice()) {
            holder.tvPrice.setText(UnitUtil.formatTwo(ordersBean.getNowBuyPrice() * (ordersBean.getFee_Rate() + 1)));
            holder.tvPrice.setTextColor(context.getResources().getColor(R.color.green0));
            holder.tvPrice.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.quotation_rise_01, 0, 0, 0);
        }
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(ordersBean.getNickName());
                }
            }
        });
    }
    private String getPayment(String payment) {
        if (payment.contains("|W|")) {
            payment = payment.replace("|W|", ", ").trim();
            if (payment.endsWith(",")) {
                payment=payment.substring(0,payment.length()-1);
            }
        }
        return payment;
    }

    @Override
    public int getItemCount() {
        return mOrders != null ? mOrders.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout item;
        TextView tvNickName;
        TextView tvPrice;
        TextView tvType;
        TextView tvDealNum;
        TextView tvScore;
        TextView tvOnline;
        TextView tvPayment;

        public ViewHolder(View itemView) {
            super(itemView);
            item = (LinearLayout) itemView.findViewById(R.id.item);
            tvNickName = (TextView) itemView.findViewById(R.id.tv_nickname);
            tvPrice = (TextView) itemView.findViewById(R.id.tv_price);
            tvType = (TextView) itemView.findViewById(R.id.tv_type);
            tvDealNum = (TextView) itemView.findViewById(R.id.tv_deal_num);
            tvScore = (TextView) itemView.findViewById(R.id.tv_score);
            tvOnline = (TextView) itemView.findViewById(R.id.tv_online);
            tvPayment = (TextView) itemView.findViewById(R.id.tv_payment);
        }
    }
}

