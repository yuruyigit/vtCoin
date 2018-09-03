package com.block.vtCoin.mine.setting.account_address.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.block.vtCoin.R;
import com.block.vtCoin.mine.setting.account_address.m.AccountAddressEntity;
import com.zhy.android.percent.support.PercentRelativeLayout;
import java.util.List;


/**
 * 介绍：
 * 作者：zhangxutong
 * 邮箱：zhangxutong@imcoming.com
 * 时间： 2016/9/12.
 */

public class AccountAddressAdapter extends RecyclerView.Adapter<AccountAddressAdapter.FullDelDemoVH> {

    private Context mContext;
    private LayoutInflater mInfalter;
    private List<AccountAddressEntity> mDatas;

    public AccountAddressAdapter(Context context, List<AccountAddressEntity> mDatas) {
        mContext = context;
        mInfalter = LayoutInflater.from(context);
        this.mDatas = mDatas;
    }

    @Override
    public FullDelDemoVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FullDelDemoVH(mInfalter.inflate(R.layout.item_account_address, parent, false));
    }

    @Override
    public void onBindViewHolder(final FullDelDemoVH holder, final int position) {
//        ((SwipeMenuLayout) holder.itemView).setIos(false).setLeftSwipe(position % 2 == 0 ? true : false);//这句话关掉IOS阻塞式交互效果 并依次打开左滑右滑
        ((SwipeMenuLayout) holder.itemView).setIos(false).setLeftSwipe(true);

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(null!=mOnItemListener){
                   mOnItemListener.onItem(holder.getAdapterPosition());
               }
            }
        });
        holder.tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mOnSwipeListener) {
                    Toast.makeText(mContext, " 删除"+position, Toast.LENGTH_SHORT).show();
                    //如果删除时，不使用mAdapter.notifyItemRemoved(pos)，则删除没有动画效果，
                    //且如果想让侧滑菜单同时关闭，需要同时调用 ((CstSwipeDelMenu) holder.itemView).quickClose();
                    //((CstSwipeDelMenu) holder.itemView).quickClose();
                    mOnSwipeListener.onDel(holder.getAdapterPosition());
                }
            }
        });
        holder.tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mOnSwipeListener) {
                    mOnSwipeListener.onEdit(holder.getAdapterPosition());
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

    class FullDelDemoVH extends RecyclerView.ViewHolder {
        PercentRelativeLayout item;
        RelativeLayout tvEdit, tvDelete;
        ImageView ivImg;
        TextView tvName,tvAddress;


        public FullDelDemoVH(View itemView) {
            super(itemView);
            item = (PercentRelativeLayout) itemView.findViewById(R.id.item);
            tvEdit= (RelativeLayout) itemView.findViewById(R.id.rl_edit);
            tvDelete= (RelativeLayout) itemView.findViewById(R.id.rl_delete);
            ivImg= (ImageView) itemView.findViewById(R.id.iv_img);
            tvName= (TextView) itemView.findViewById(R.id.tv_name);
            tvAddress= (TextView) item.findViewById(R.id.tv_address);
        }
    }
}

