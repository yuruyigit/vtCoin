package com.block.vtCoin.deal.order.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.block.vtCoin.R;
import com.block.vtCoin.deal.order.m.OrderEntity;

import java.util.List;

import butterknife.Bind;

/**
 * @Description
 * @Author lijie
 * @Date 2017/7/18.
 */
public class DealOrderAdapter extends RecyclerView.Adapter<DealOrderAdapter.ViewHolder> {

    @Bind(R.id.order_number)
    TextView orderNumber;
    @Bind(R.id.order_creat_time)
    TextView orderCreatTime;
    @Bind(R.id.order_person_type)
    TextView orderPersonType;
    @Bind(R.id.order_person)
    TextView orderPerson;
    @Bind(R.id.order_unit_price)
    TextView orderUnitPrice;
    @Bind(R.id.order_num)
    TextView orderNum;
    @Bind(R.id.total_prices)
    TextView totalPrices;
    @Bind(R.id.order_state)
    TextView orderState;
    @Bind(R.id.deal_item_ly)
    LinearLayout dealItemLy;
    private Context context;
    private List<OrderEntity> data;

    public DealOrderAdapter(Context context, List<OrderEntity> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_deal_order, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.orderNumber.setText(data.get(position).orderNumber);
        holder.orderCreatTime.setText(data.get(position).orderCreatTime);
        holder.orderPersonType.setText(data.get(position).orderPersonType);
        holder.orderPerson.setText(data.get(position).orderPerson);
        holder.orderUnitPrice.setText(data.get(position).orderUnitPrice);
        holder.orderNum.setText(data.get(position).orderNum);
        holder.totalPrices.setText(data.get(position).totalPrices);
        String state = "";
        switch (data.get(position).orderState) {
            case 0:
                state = "未付款";
                break;
            case 1:
                state = "待卖方通过";
                break;
            case 2:
                state = "交易成功";
                break;
            case 3:
                state = "待买方付款";
                break;
            case 4:
                state = "未通过";
                break;
            case 5:
                state="仲裁中";
                break;
        }
        holder.orderState.setText(state);
        holder.dealItemLy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null)
                    mListener.onItemClick(position , data.get(position).orderState);
            }
        });
    }

    public interface OnItemClickListener {
        void onItemClick(int postion ,int state);
    }

    public DealOrderAdapter.OnItemClickListener mListener;

    public void setOnItemClickListener(DealOrderAdapter.OnItemClickListener listener) {
        this.mListener = listener;
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView orderNumber;
        TextView orderCreatTime;
        TextView orderPersonType;
        TextView orderPerson;
        TextView orderUnitPrice;
        TextView orderNum;
        TextView totalPrices;
        TextView orderState;
        LinearLayout dealItemLy;

        public ViewHolder(View itemView) {
            super(itemView);
            orderNumber = (TextView) itemView.findViewById(R.id.order_number);
            orderCreatTime = (TextView) itemView.findViewById(R.id.order_creat_time);
            orderPersonType = (TextView) itemView.findViewById(R.id.order_person_type);
            orderPerson = (TextView) itemView.findViewById(R.id.order_person);
            orderUnitPrice = (TextView) itemView.findViewById(R.id.order_unit_price);
            orderNum = (TextView) itemView.findViewById(R.id.order_num);
            totalPrices = (TextView) itemView.findViewById(R.id.total_prices);
            orderState = (TextView) itemView.findViewById(R.id.order_state);
            dealItemLy = (LinearLayout) itemView.findViewById(R.id.deal_item_ly);

        }
    }
}

