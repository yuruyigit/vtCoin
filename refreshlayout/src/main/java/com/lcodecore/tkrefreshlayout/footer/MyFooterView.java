package com.lcodecore.tkrefreshlayout.footer;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.lcodecore.tkrefreshlayout.IBottomView;
import com.lcodecore.tkrefreshlayout.R;

/**
 refreshLayout.setOverScrollTopShow(true);//飞速滑动时不显示下拉刷新，只是越界回弹
 refreshLayout.setOverScrollBottomShow(true );//飞速滑动时不显示加载更多，只是越界回弹
 refreshLayout.setOverScrollRefreshShow(true);//飞速滑动时不显示下拉刷新，加载更多，只是越界回弹
 refreshLayout.setEnableOverScroll(false);//飞速滑动时，是否要越界回弹
 refreshLayout.setFloatRefresh(false);//浮动刷新
 refreshLayout.setPureScrollModeOn();//开启纯净的越界回弹模式，也就是所有刷新相关的View都不显示，只显示越界回弹效果
 refreshLayout.setEnableKeepIView(true);//是否在刷新或者加载更多后保持状态
 */

public class MyFooterView extends FrameLayout implements IBottomView {

    private ImageView loadingView;
    private TextView refreshTextView;
    private boolean isNoMore = false;

    private String noMoreStr = "没有更多数据";
    private String loadingStr = "加载中...";

    public MyFooterView(Context context) {
        this(context, null);
    }

    public MyFooterView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyFooterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View rootView = View.inflate(getContext(), R.layout.foot_refresh, null);
        refreshTextView = (TextView) rootView.findViewById(R.id.tv);
        loadingView = (ImageView) rootView.findViewById(R.id.iv_loading);
        refreshTextView.setText(loadingStr);
        addView(rootView);
    }


    public void setNoMoreStr(String str) {
        noMoreStr = str;
    }

    public void setLoadingStr(String str) {
        loadingStr = str;
    }

    @Override
    public View getView() {
        return this;
    }

    @Override
    public void onPullingUp(float fraction, float maxBottomHeight, float bottomHeight) {
    }

    @Override
    public void onPullReleasing(float fraction, float maxHeadHeight, float headHeight) {
    }

    @Override
    public void isNoMore(Boolean b) {
        if(b){
            refreshTextView.setText(noMoreStr);
            loadingView.setVisibility(GONE);
        }else {
            refreshTextView.setText(loadingStr);
            loadingView.setVisibility(VISIBLE);
        }
    }

    @Override
    public void onFinish() {
    }
    @Override
    public void startAnim(float maxHeadHeight, float headHeight) {
        ((AnimationDrawable) loadingView.getDrawable()).start();
    }

    @Override
    public void reset() {
    }
}
