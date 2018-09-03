package com.block.vtCoin.widget.decoration;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.block.vtCoin.R;
import com.block.vtCoin.util.ScreenUtils;

public class RecycleViewDivider extends RecyclerView.ItemDecoration {
    private final Resources res;
    private Paint mPaint;
    private int mDividerHeight = ScreenUtils.dip2px(0.5f);//分割线高度，默认为1px
    private int mMarginLeft = 0;
    private int mMarginRight = 0;

    /**
     * 自定义分割线,默认gray6，0.5dp
     */
    public RecycleViewDivider(Context context) {
        res = context.getResources();
        mPaint = new Paint();
        init(R.color.gray6, mDividerHeight, mMarginLeft, mMarginRight);
    }

    /**
     * @param dividerHeight 分割线高度
     * @param dividerColor  分割线颜色
     */
    public RecycleViewDivider(Context context, int dividerColor, int dividerHeight) {
        this(context);
        init(dividerColor, dividerHeight, mMarginLeft, mMarginRight);
    }
    /**
     * @param dividerHeight 分割线高度
     * @param dividerColor  分割线颜色
     */
    public RecycleViewDivider(Context context, int dividerHeight, int dividerColor, int marginLeft, int marginRight) {
        this(context);
        init(dividerColor, dividerHeight, marginLeft, marginRight);
    }

    private void init(int dividerColor, int dividerHeight, int marginLeft, int marginRight) {
        this.mMarginLeft = marginLeft;
        this.mMarginRight = marginRight;
        this.mDividerHeight = dividerHeight;
        mPaint.setColor(res.getColor(dividerColor));
    }


    //获取分割线尺寸
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(0, 0, 0, mDividerHeight);
    }

    //绘制分割线
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        drawHorizontal(c, parent);
    }

    //绘制横向 item 分割线
    private void drawHorizontal(Canvas canvas, RecyclerView parent) {
        final int left = parent.getPaddingLeft() + ScreenUtils.dip2px(mMarginLeft);
        final int right = parent.getMeasuredWidth() - parent.getPaddingRight()-ScreenUtils.dip2px(mMarginRight);
        final int childSize = parent.getChildCount();
        for (int i = 0; i < childSize; i++) {
            final View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getBottom() + layoutParams.bottomMargin;
            final int bottom = top + mDividerHeight;
            if (mPaint != null) {
                canvas.drawRect(left, top, right, bottom, mPaint);
            }
        }
    }
}