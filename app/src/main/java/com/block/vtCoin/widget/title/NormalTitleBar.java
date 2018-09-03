package com.block.vtCoin.widget.title;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.block.vtCoin.R;
import com.block.vtCoin.widget.base.BaseCombinationView;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * @Description
 * @Author lijie
 * @Date 2017/7/12.
 */
public class NormalTitleBar extends BaseCombinationView implements View.OnClickListener {
    @Bind(R.id.tvTitle)
    TextView tvTitle;//标题
    @Bind(R.id.tvLeft)
    TextView tvLeft;//标题左边
    @Bind(R.id.tvRight)
    TextView tvRight;//标题右边
    @Bind(R.id.title)
    RelativeLayout title;
    private Activity activity;
    private OnClickListener listener;

    public NormalTitleBar(Context context) {
        super(context);
        activity = (Activity) context;
        setLeftDrawable(R.mipmap.turn);
    }

    @Override
    public int getLayoutId() {
        return R.layout.normal_title;
    }

    public NormalTitleBar setBackground(int color){
        title.setBackground(getResources().getDrawable(color));
        return this;
    }

    @OnClick(R.id.tvLeft)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvLeft:
                if (listener == null)
                    activity.finish();
                else
                    listener.onClick(view);
                break;
        }
    }

    /**
     * 设置左边的图标
     *
     * @param rec 图标
     * @return this
     */
    public NormalTitleBar setLeftDrawable(int rec) {
        tvLeft.setCompoundDrawablesWithIntrinsicBounds(rec, 0, 0, 0);
        return this;
    }
    /**
     * 设置左边的图标
     *
     * @param rec 图标
     * @return this
     */
    public NormalTitleBar setTitleDrawable(int rec) {
        tvTitle.setCompoundDrawablesWithIntrinsicBounds(0, 0, rec, 0);
        return this;
    }

    /**
     * 给左边设置点击事件
     *
     * @param listener 点击事件监听回调
     * @return this
     */
    public NormalTitleBar setLeftListener(OnClickListener listener) {
        this.listener = listener;
        return this;
    }

    public void setTitleListener(OnClickListener listener) {
        tvTitle.setOnClickListener(listener);
    }
    /**
     * 给右边设置点击事件
     *
     * @param listener 点击事件监听回调
     * @return this
     */
    public NormalTitleBar setRightListener(OnClickListener listener) {
        tvRight.setOnClickListener(listener);
        return this;
    }

    /**
     * 设置右边的图标
     *
     * @param rec 图标
     * @return this
     */
    public NormalTitleBar setRightDrawable(int rec) {
        tvRight.setCompoundDrawablesWithIntrinsicBounds(0, 0, rec, 0);

        return this;
    }

    /**
     * 设置右边的图标
     *
     * @param rec 图标资源Id
     * @return this
     */
    public NormalTitleBar setRightText(int rec) {
        tvRight.setText(rec);
        return this;
    }

    public NormalTitleBar setRightText(int rec, int colorRec) {
        tvRight.setText(rec);
        tvRight.setTextColor(colorRec);
        return this;
    }

    public NormalTitleBar setRightText(int rec, float size) {
        tvRight.setText(rec);
        tvRight.setTextSize(size);
        return this;
    }

    /**
     * 设置右边的文字
     *
     * @param rec      文字资源ID
     * @param colorRec 颜色资源ID
     * @param size     文字尺寸（px）
     * @return
     */
    public NormalTitleBar setRightText(int rec, int colorRec, float size) {
        tvRight.setText(rec);
        tvRight.setTextColor(colorRec);
        tvRight.setTextSize(size);
        return this;
    }

    /**
     * 设置右边的文字
     *
     * @param text 文字
     * @return this
     */
    public NormalTitleBar setRightText(String text) {
        tvRight.setText(text);
        return this;
    }

    public NormalTitleBar setRightTextColor(int colorRec) {
        tvRight.setTextColor(colorRec);
        return this;
    }

    public NormalTitleBar setRightText(String text, int colorRec) {
        tvRight.setText(text);
        tvRight.setTextColor(colorRec);
        return this;
    }

    /**
     * 设置左边的文字
     *
     * @param text 文字
     * @return this
     */
    public NormalTitleBar setLeftText(String text) {
        tvLeft.setText(text);
        return this;
    }

    /**
     * 设置左边的文字
     *
     * @param rec string id
     * @return this
     */
    public NormalTitleBar setLeftText(int rec) {
        tvLeft.setText(rec);
        return this;
    }

    /**
     * 设置标题
     *
     * @param text 文字
     * @return this
     */
    public NormalTitleBar setTitle(String text) {
        tvTitle.setText(text);
        return this;
    }

    public NormalTitleBar setTitleTextColor(int color){
        tvTitle.setTextColor(getResources().getColor(color));
        return this;
    }

    /**
     * 设置标题和字体颜色
     *
     * @param text  文字
     * @param color 字体颜色
     * @return this
     */
    public NormalTitleBar setTitle(String text, int color) {
        tvTitle.setText(text);
        tvTitle.setTextColor(color);
        return this;
    }

    /**
     * 设置标题和字体颜色
     *
     * @param rec   string id
     * @param color 字体颜色
     * @return this
     */
    public NormalTitleBar setTitle(int rec, int color) {
        tvTitle.setText(rec);
        tvTitle.setTextColor(color);
        return this;
    }

    public NormalTitleBar hideLeft() {
        tvLeft.setVisibility(GONE);
        return this;
    }

    public NormalTitleBar showLeft() {
        tvLeft.setVisibility(VISIBLE);
        return this;
    }

    public NormalTitleBar setMFitsSystemWindows(boolean fits) {
        setFitsSystemWindows(fits);
        return this;
    }

    public TextView getRightView() {
        return tvRight;
    }

    public TextView getLeftView() {
        return tvLeft;
    }

    public TextView getTitleView() {
        return tvTitle;
    }
}
