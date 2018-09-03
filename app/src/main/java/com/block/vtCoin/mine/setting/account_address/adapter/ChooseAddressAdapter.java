package com.block.vtCoin.mine.setting.account_address.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.block.vtCoin.R;
import com.block.vtCoin.mine.setting.account_address.m.AccountAddressEntity;
import com.zhy.android.percent.support.PercentRelativeLayout;

import java.util.List;

public class ChooseAddressAdapter extends RecyclerView.Adapter<ChooseAddressAdapter.ViewHolder> {

    private Context mContext;
    private LayoutInflater mInfalter;
    private List<AccountAddressEntity> mDatas;

    public ChooseAddressAdapter(Context context, List<AccountAddressEntity> mDatas) {
        mContext = context;
        mInfalter = LayoutInflater.from(context);
        this.mDatas = mDatas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInfalter.inflate(R.layout.item_choose_address, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(null!=mOnItemListener){
                   mOnItemListener.onItem(holder.getAdapterPosition());
               }
            }
        });
    }

    @Override
    public int getItemCount() {
        return null != mDatas ? mDatas.size() : 0;
    }

    private OnItemListener mOnItemListener;
    public interface OnItemListener{
        void onItem(int position);
    }
    public void setOnItemListener(OnItemListener onItemListener ){
        this.mOnItemListener=onItemListener;
    }

    /**
     * 和Activity通信的接口
     */
    public interface onSwipeListener {
        void onDel(int pos);

        void onEdit(int pos);
    }

    private onSwipeListener mOnSwipeListener;

    public onSwipeListener getOnDelListener() {
        return mOnSwipeListener;
    }

    public void setOnSwipeListener(onSwipeListener mOnSwipeListener) {
        this.mOnSwipeListener = mOnSwipeListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        PercentRelativeLayout item;
        RelativeLayout tvEdit, tvDelete;
        ImageView ivChoose,ivImg;
        TextView tvName,tvAddress;
        public ViewHolder(View itemView) {
            super(itemView);
            item = (PercentRelativeLayout) itemView.findViewById(R.id.item);
            ivChoose= (ImageView) itemView.findViewById(R.id.iv_choose);
            ivImg= (ImageView) itemView.findViewById(R.id.iv_img);
            tvName= (TextView) itemView.findViewById(R.id.tv_name);
            tvAddress= (TextView) itemView.findViewById(R.id.tv_address);
        }
    }
}

