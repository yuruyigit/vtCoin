package com.block.vtCoin.widget.popupwindow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.block.vtCoin.R;

import butterknife.Bind;

/**
 * @Description
 * @Author lijie
 * @Date 2017/7/19.
 */
public class ListViewPopup extends BasePopupWindow {
    @Bind(R.id.list_view)
    ListView listView;
    private Context context;
    private ListPopupAdapter adapter;
    String[] items;

    public ListViewPopup(Context context, String[] items, int width, int height) {
        super(context);
        this.context = context;
        this.items = items;
        setWidth(width);
        setHeight(height);
        init();
    }

    private void init() {
        adapter = new ListPopupAdapter(context, items);
        listView.setAdapter(adapter);

    }

    @Override
    protected int getWidth() {
        /*match,是占满屏幕宽度*/
        return WindowManager.LayoutParams.WRAP_CONTENT;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.popup_listview;
    }

    /*监听*/
    private OnItemListener mListener;

    public void setOnItemListener(OnItemListener listener) {
        this.mListener = listener;
    }

    public interface OnItemListener {
        void onItemClick(int position);
    }

    private class ListPopupAdapter extends BaseAdapter {
        private Context mContext;
        private String[] items;

        public ListPopupAdapter(Context context, String[] items) {
            this.mContext = context;
            this.items = items;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHold viewHold = null;
            String itemValue = items[position];
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_coin_popup, parent, false);
                viewHold = new ViewHold();
                viewHold.tv_item = (TextView) convertView.findViewById(R.id.tv_item);
                convertView.setTag(viewHold);
            } else {
                viewHold = (ViewHold) convertView.getTag();
            }
            viewHold.tv_item.setText(itemValue);
            viewHold.tv_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                    if (mListener != null) {
                        mListener.onItemClick(position);
                    }
                }
            });
            return convertView;
        }

        @Override
        public int getCount() {
            if (items == null) {
                return 0;
            }
            return items.length;
        }

        @Override
        public Object getItem(int position) {
            if (items != null && position < items.length) {
                return items.length;
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
        }
    }

}
