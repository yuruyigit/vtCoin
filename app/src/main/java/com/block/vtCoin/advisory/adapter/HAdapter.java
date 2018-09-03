package com.block.vtCoin.advisory.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.block.vtCoin.R;

/**
 * @Description
 * @Author lijie
 * @Date 2017/7/18.
 */
public class HAdapter extends RecyclerView.Adapter<HAdapter.ViewHolder> {



    private Context context;
    private int[]data;

    public HAdapter(Context context, int[]data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_image_advisory, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
//    holder.advisoryImage.setImageURI(Uri.parse("res:///" + data[position]));
        holder.advisoryImage.setImageResource(data[position]);
    }


    @Override
    public int getItemCount() {
        return data != null ? data.length : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView advisoryImage;
        public ViewHolder(View itemView) {
            super(itemView);
            advisoryImage = (ImageView) itemView.findViewById(R.id.advisory_image);
        }
    }
}

