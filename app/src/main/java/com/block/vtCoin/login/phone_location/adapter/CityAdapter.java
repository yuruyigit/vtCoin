package com.block.vtCoin.login.phone_location.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.block.vtCoin.R;
import com.block.vtCoin.login.phone_location.m.City;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tangyangkai on 16/7/29.
 */
public class CityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    public static final int FIRST_STICKY_VIEW = 1;
    public static final int HAS_STICKY_VIEW = 2;
    public static final int NONE_STICKY_VIEW = 3;
    private Context context;
    private List<City> cityLists = new ArrayList<>();
    private onItemClickListener listener;

    public CityAdapter(Context context, List<City> cityLists) {
        this.context = context;
        this.cityLists = cityLists;
    }


    public interface onItemClickListener {

        void itemClick(int position);
    }

    public void setListener(onItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_city, parent, false);
        return new CityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof CityViewHolder) {
            CityViewHolder viewHolder = (CityViewHolder) holder;
            City cityModel = cityLists.get(position);
            viewHolder.tvCityName.setText(cityModel.getCityName());
            viewHolder.number.setText("+"+cityModel.getNumber());
            viewHolder.rlContentWrapper.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.itemClick(position);
                }
            });


        }


    }

    @Override
    public int getItemCount() {
        return cityLists.size() == 0 ? 0 : cityLists.size();
    }


    public class CityViewHolder extends RecyclerView.ViewHolder {
        TextView tvCityName,number;
        RelativeLayout rlContentWrapper;

        public CityViewHolder(View itemView) {
            super(itemView);
            rlContentWrapper = (RelativeLayout) itemView.findViewById(R.id.item);
            tvCityName = (TextView) itemView.findViewById(R.id.name);
            number= (TextView) itemView.findViewById(R.id.number);
        }
    }


}
