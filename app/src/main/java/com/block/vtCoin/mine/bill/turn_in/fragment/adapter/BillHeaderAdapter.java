package com.block.vtCoin.mine.bill.turn_in.fragment.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.block.vtCoin.R;

import java.util.List;
import recyclerview.eowise.recycleview.library.StickyHeadersAdapter;

/**
 * Created by aurel on 24/09/14.
 */
public class BillHeaderAdapter implements StickyHeadersAdapter<BillHeaderAdapter.ViewHolder> {
    private List<String> items;
    public BillHeaderAdapter(List<String> items) {
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_top_header, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder headerViewHolder, int position) {
        headerViewHolder.title.setText(items.get(position));
    }

    @Override
    public long getHeaderId(int position) {
        return items.get(position).hashCode();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
        }
    }
}
