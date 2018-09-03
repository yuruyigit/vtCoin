package com.block.vtCoin.market.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.block.net.util.L;
import com.block.vtCoin.R;
import com.block.vtCoin.entity.MarketEntity;
import com.block.vtCoin.util.SpannableStringUtils;
import com.block.vtCoin.util.UnitUtil;

import java.util.List;

public class CoinAdapter extends RecyclerView.Adapter<CoinAdapter.ViewHolder> {
    private Context context;
    private List<MarketEntity> mData;
    private double rate;

    public CoinAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<MarketEntity> data, double rate) {
        this.mData = data;
        this.rate = rate;
        this.notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_market, parent, false);
        return new ViewHolder(view);
    }

    private void setLogo(ImageView iv, String name) {
        switch (name) {
            case "OKCoin":
                iv.setImageResource(R.mipmap.quotation_timg);
                break;
            case "微特区块":
                iv.setImageResource(R.mipmap.quotation_bitcoin);
                break;
            case "火币网":
                iv.setImageResource(R.mipmap.quotation_bitvc_01);
                break;
            case "聚币网":
                iv.setImageResource(R.mipmap.quotation_fire_currency);
                break;
            case "BTC-E":
                iv.setImageResource(R.mipmap.quotation_okcoin);
                break;
            case "比特儿":
                iv.setImageResource(R.mipmap.quotation_vetor);
                break;
            case "比特时代":
                iv.setImageResource(R.mipmap.quotation_bitcoin);
                break;
            case "比特币交易网":
                iv.setImageResource(R.mipmap.quotation_bitcoin);
                break;
            case "Bitvc本周":
                iv.setImageResource(R.mipmap.quotation_bitcoin);
                break;
            case "比特币中国":
                iv.setImageResource(R.mipmap.quotation_bitcoin);
                break;
            case "中国比特币":
                iv.setImageResource(R.mipmap.quotation_bitcoin);
                break;
            default:
                iv.setImageResource(R.mipmap.quotation_bitcoin);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        setLogo(holder.ivLogo, mData.get(position).getProvierName());
        holder.tvName.setText(mData.get(position).getProvierName());
        MarketEntity.DataBean data = mData.get(position).getData();
        if (data == null)
            return;
        double cnyMinus = data.getBuy() - data.getBeforeBuy();
        int color = context.getResources().getColor(R.color.black1);
        if (cnyMinus > 0 && data.getBeforeBuy() != 0) {//涨
            color = context.getResources().getColor(R.color.red);
            holder.tvCnyMinus.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.quotation_rise_02, 0, 0, 0);
        } else if (cnyMinus < 0) {//跌了
            color = context.getResources().getColor(R.color.green0);
            holder.tvCnyMinus.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.quotation_rise_01, 0, 0, 0);
            cnyMinus = 0 - cnyMinus;
        } else if (cnyMinus == 0 || data.getBeforeBuy() == 0) {//不涨不跌
            color = context.getResources().getColor(R.color.black1);
            holder.tvCnyMinus.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        }
        holder.tvCNY.setText(SpannableStringUtils.getGreenCombString(UnitUtil.formatTwo(data.getBuy()), color, "CNY"));
        holder.tvCnyMinus.setText(SpannableStringUtils.getGreenCombString(UnitUtil.formatTwo(cnyMinus), color, "CNY"));

        holder.tvVol.setText("成交量 " + UnitUtil.formatFour(data.getVol()));
        holder.tvUSD.setText(SpannableStringUtils.getGreenCombString(UnitUtil.formatTwo(data.getBuy() / rate), color, "USD"));
        String percent = "";
        if (cnyMinus == 0 || data.getBeforeBuy() == 0) {
            percent = "0.00%";
        } else {
            percent = UnitUtil.formatTwo(cnyMinus / data.getBeforeBuy() * 100) + "%";
        }
        holder.tvPercent.setText("(" + percent + ")");
        holder.tvPercent.setTextColor(color);
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivLogo;
        TextView tvName;
        TextView tvCNY;
        TextView tvCnyMinus;
        TextView tvVol;
        TextView tvUSD;
        TextView tvPercent;

        public ViewHolder(View itemView) {
            super(itemView);
            ivLogo = (ImageView) itemView.findViewById(R.id.iv_logo);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvCNY = (TextView) itemView.findViewById(R.id.tv_cny);
            tvCnyMinus = (TextView) itemView.findViewById(R.id.tv_cny_minus);
            tvVol = (TextView) itemView.findViewById(R.id.tv_vol);
            tvUSD = (TextView) itemView.findViewById(R.id.tv_usd);
            tvPercent = (TextView) itemView.findViewById(R.id.tv_percent);
        }
    }
}

