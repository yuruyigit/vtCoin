package com.block.vtCoin.main.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.block.net.util.L;
import com.block.vtCoin.R;
import com.block.vtCoin.advisory.adapter.HAdapter;
import com.block.vtCoin.advisory.adapter.VAdapter;
import com.block.vtCoin.advisory.m.AdvisoryEntity;
import com.block.vtCoin.advisory.m.VRecyclerEntity;
import com.block.vtCoin.advisory.p.AdvisoryPresenter;
import com.block.vtCoin.advisory.v.AdvisoryImpl;
import com.block.vtCoin.main.MainActivity;
import com.block.vtCoin.request.MyModel;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @Description 区块
 * @Author lijie
 * @Date 2017/7/12.
 */
public class AdvisoryFragment extends LazyLoadFragment {
    @Bind(R.id.banner)
    MZBannerView banner;
    @Bind(R.id.h_recycler)
    RecyclerView hRecycler;
    @Bind(R.id.v_recycler)
    RecyclerView vRecycler;
    @Bind(R.id.cny_money)
    TextView cnyMoney;
    private MainActivity activity;
    private int[] MessageLogo = {R.mipmap.information_banner_01, R.mipmap.information_banner_02, R.mipmap.information_banner_03};
    private int[] MessageImage = {R.mipmap.information_picture_01, R.mipmap.information_picture_02, R.mipmap.information_picture_03, R.mipmap.information_picture_04};
    private AdvisoryPresenter advisoryPresenter;
    private List<VRecyclerEntity> mVData;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        L.i("TAG","Advisory   onCreate");
        initPresenter();
    }


    private void initPresenter() {
        advisoryPresenter = new AdvisoryPresenter();//账户余额控制器
        advisoryPresenter.setModelView(new MyModel<AdvisoryEntity>(), new AdvisoryImpl(this));
    }

    @Override
    protected int setContentView() {return R.layout.fragment_advisory;}

    @Override
    protected void initView() {
        Log.i("TAG","Advisory   initView");
     /*AdBannerView*/
        List<Integer> list = new ArrayList<>();
        final String[] items = new String[MessageLogo.length];
        for (int i = 0; i < MessageLogo.length; i++) {
            list.add(MessageLogo[i]);
            items[i] = "我是第" + i + "个";
        }
        banner.setIndicatorRes(R.drawable.dot_banner_blue, R.drawable.dot_banner_focus);
        banner.setPages(list, new MZHolderCreator<BannerViewHolder>() {
            @Override
            public BannerViewHolder createViewHolder() {
                return new BannerViewHolder(items);
            }
        });
        /*hRecyclerView*/
        LinearLayoutManager manager0 = new LinearLayoutManager(getActivity());
        manager0.setOrientation(LinearLayoutManager.HORIZONTAL);
        hRecycler.setLayoutManager(manager0);
        hRecycler.setAdapter(new HAdapter(getActivity(), MessageImage));
        /*vRecyclerView*/
        LinearLayoutManager manager1 = new LinearLayoutManager(getActivity());
        manager1.setOrientation(LinearLayoutManager.VERTICAL);
        vRecycler.setLayoutManager(manager1);
        mVData = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            VRecyclerEntity entity = new VRecyclerEntity();
            entity.imagePath = "http://img.my.csdn.net/uploads/201508/05/1438760757_3588.jpg";
            entity.name = "习近平";
            entity.time = "2017-08-08 08:44";
            entity.title = "比特币价格突破3500美元刷新纪录 今年上涨264%";
            mVData.add(entity);
        }
        vRecycler.setAdapter(new VAdapter(getActivity(), mVData));
    }

    @Override
    protected void onWakeOrCurrent() {
        Log.i("TAG","Advisory   onWakeOrCurrent");
        banner.start();
    }

    @Override
    protected void notCurrent() {
        Log.i("TAG","Advisory   notCurrent");
        banner.pause();
    }

    public void loading() {
        activity.loadDialog();
    }

    public void dismiss() {
        activity.dismissDialog();
    }

    public void showTip(String msg) {
        activity.showTip(msg);
    }

    @Override
    public void onDestroy() {
        Log.i("TAG","Advisory   onDestroy");
        super.onDestroy();
    }

    public static class BannerViewHolder implements MZViewHolder<Integer> {
        private ImageView mImageView;
        private TextView textView;
        private String[] items;

        public BannerViewHolder(String[] items) {
            this.items = items;
        }

        @Override
        public View createView(Context context) {
            // 返回页面布局文件
            View view = LayoutInflater.from(context).inflate(R.layout.banner_item, null);
            mImageView = (ImageView) view.findViewById(R.id.banner_image);
            textView = (TextView) view.findViewById(R.id.banner_text);
            return view;
        }

        @Override
        public void onBind(Context context, int position, Integer data) {
            // 数据绑定
            mImageView.setImageResource(data);
            textView.setText(items[position]);
        }
    }
}
