package com.block.vtCoin.widget.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.block.vtCoin.R;
import com.block.vtCoin.util.ScreenUtils;
import com.block.vtCoin.widget.base.OnMyItemClickListener;
import com.block.vtCoin.widget.dialog.base.DialogViewHolder;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 底部弹出的dialog,带取消
 */
public class ListViewDialog extends DialogViewHolder {

    @Bind(R.id.listView)
    ListView listView;
    @Bind(R.id.cancel)
    TextView cancel;
    SelectListAdapter mAdapter;
    private String[] mItems;
    private boolean isSlide;
    public int position=-1;
    private OnMyItemClickListener miItemClickListener;

    /*监听*/
    public void setOnItemClickListener(OnMyItemClickListener ilClickListener) {
        miItemClickListener = ilClickListener;
    }

    public ListViewDialog(Context context, String[] items) {
        super(context);
        this.mItems = items;
        this.isSlide = false;
        initView(context);
    }

    /*isSlide，是否可以滑动*/
    public ListViewDialog(Context context, String[] items, boolean isSlide) {
        super(context);
        this.mItems = items;
        this.isSlide = isSlide;
        initView(context);
    }

    /*position,为了改变第一项字体的颜色*/
    public ListViewDialog(Context context, String[] items,int position) {
        super(context);
        this.mItems = items;
        this.isSlide = false;
        this.position=position;
        initView(context);
    }

    @Override
    protected int getStyle() {
        return super.getStyle();
    }

    @Override
    protected int getWindowAnimations() {
        return R.style.dialog_bottom_to_up;
    }
    /**
     * 设置数据并刷新
     */
    public void setData(String[] itemTexts) {
        if (mAdapter != null) {
            mAdapter.setData(itemTexts);
        }
    }

    private void initView(Context context) {
        this.setCanceledOnTouchOutside(true);
        mAdapter = new SelectListAdapter(context);
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (miItemClickListener != null) {
                    miItemClickListener.onItemClick(parent, view, position - listView.getHeaderViewsCount());
                }
                dismiss();
            }
        });

        if (isSlide) {
            //最多7行，超过滚动
            int height = ScreenUtils.dip2px((mItems.length * 44));
            if (height > ScreenUtils.dip2px(200)) {
                height = ScreenUtils.dip2px(200);
            }
            ViewGroup.LayoutParams xLp = listView.getLayoutParams();
            xLp.height = height;
            listView.setLayoutParams(xLp);
        }
        mAdapter.setData(mItems);
    }

    @OnClick(R.id.cancel)
    public void onClick() {
        dismiss();
    }

    private class SelectListAdapter extends BaseAdapter {
        Context mContext;
        String[] itemTexts;

        public SelectListAdapter(Context context) {
            this.mContext = context;

        }

        public void setData(String[] itemTexts) {
            this.itemTexts = itemTexts;
            notifyDataSetChanged();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHold viewHold = null;
            String itemValue = itemTexts[position];
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_bottom_dialog, parent, false);
                viewHold = new ViewHold();
                viewHold.tv_item = (TextView) convertView.findViewById(R.id.tv_item);
                viewHold.line = convertView.findViewById(R.id.line);
                convertView.setTag(viewHold);
            } else {
                viewHold = (ViewHold) convertView.getTag();
            }
            if(position==itemTexts.length-1)
                viewHold.line.setVisibility(View.GONE);
            if(position==0)
                viewHold.line.setVisibility(View.VISIBLE);
            if(ListViewDialog.this.position==position){
                viewHold.tv_item.setTextColor(mContext.getResources().getColor(R.color.black6));
            }
            viewHold.tv_item.setText(itemValue);
            return convertView;
        }

        @Override
        public int getCount() {
            if (itemTexts == null) {
                return 0;
            }
            return itemTexts.length;
        }

        @Override
        public Object getItem(int position) {
            if (itemTexts != null && position < itemTexts.length) {
                return itemTexts[position];
            } else {
                return null;
            }
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        class ViewHold {
            TextView tv_item;
            View line;
        }
    }

    @Override
    protected int getWidth() {
        return WindowManager.LayoutParams.MATCH_PARENT;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.popup_choose_currency;
    }

    @Override
    protected int getGravity() {
        return Gravity.BOTTOM;
    }

}
