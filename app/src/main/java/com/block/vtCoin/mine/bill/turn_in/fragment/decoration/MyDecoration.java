package com.block.vtCoin.mine.bill.turn_in.fragment.decoration;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.view.View;

import com.block.vtCoin.R;
import com.block.vtCoin.mine.bill.turn_in.fragment.m.ALLEntity;

import java.util.List;

/**
 * Created by tangyangkai on 2016/12/27.
 */

public class MyDecoration extends RecyclerView.ItemDecoration {

    private TextPaint textPaint;
    private Paint paint;
    private int topHeight;
    private List<ALLEntity> mCityList;

    public MyDecoration(Context context, List<ALLEntity> cityList) {
        this.mCityList=cityList;
        Resources res = context.getResources();

        paint = new Paint();
        paint.setColor(res.getColor(R.color.white0));
        textPaint = new TextPaint();
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(50);
        textPaint.setColor(Color.BLACK);
        topHeight = res.getDimensionPixelSize(R.dimen.dimen_32);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);
        if (isFirstInGroup(position)) {
            outRect.top = topHeight;
        } else {
            outRect.top = 0;
        }

    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(view);
            String textLine =mCityList.get(position).decoration;
            if (isFirstInGroup(position)) {
                float top = view.getTop() - topHeight;
                float bottom = view.getTop();
                c.drawRect(left, top, right, bottom, paint);//绘制红色矩形
                c.drawText(textLine, left + 40, bottom - 30, textPaint);//绘制文本
            }
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        int position = ((LinearLayoutManager) (parent.getLayoutManager())).findFirstVisibleItemPosition();
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        c.drawRect(left, 0, right, topHeight, paint);//绘制红色矩形
        String text = mCityList.get(position).decoration;
        c.drawText(text, 30, topHeight - 30, textPaint);//绘制文本
    }

    private boolean isFirstInGroup(int position) {
        boolean isFirst;
        if (position == 0) {
            isFirst = true;
        } else {
            if (mCityList.get(position).decoration.
                    equals(mCityList.get(position - 1).decoration)) {
                isFirst = false;
            } else {
                isFirst = true;
            }
        }
        return isFirst;
    }
}
