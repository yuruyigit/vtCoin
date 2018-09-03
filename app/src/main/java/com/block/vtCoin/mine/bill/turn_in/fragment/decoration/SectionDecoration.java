package com.block.vtCoin.mine.bill.turn_in.fragment.decoration;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import com.block.vtCoin.R;
import com.block.vtCoin.util.ScreenUtils;

import java.util.List;

/**
 * Created by LiShiYu on 2016/12/2.
 * 显示悬浮栏
 */
public class SectionDecoration extends RecyclerView.ItemDecoration {
    private final Resources res;
    private Bitmap bitmap;
    private List<String> dataList;
    private TextPaint textPaint;
    private Paint paint;
    private int topGap;
    private int alignBottom;
    private Paint.FontMetrics fontMetrics;

    public SectionDecoration(List<String> dataList, Context context) {
        res = context.getResources();
        bitmap=BitmapFactory.decodeResource(res,R.mipmap.release_arrow);
        this.dataList = dataList;
        //设置悬浮栏的画笔---paint
        paint = new Paint();
        paint.setColor(res.getColor(R.color.gray_f6));
        //设置悬浮栏中文本的画笔
        textPaint = new TextPaint();
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(ScreenUtils.dip2px(14));
        textPaint.setColor(res.getColor(R.color.black5));
        textPaint.setTextAlign(Paint.Align.LEFT);
        fontMetrics = new Paint.FontMetrics();
        //决定悬浮栏的高度等
        topGap = res.getDimensionPixelSize(R.dimen.dimen_40);
        //决定文本的显示位置等
        alignBottom = res.getDimensionPixelSize(R.dimen.dimen_8);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int pos = parent.getChildAdapterPosition(view);
        String groupId = dataList.get(pos);
        if (groupId.equals("-1")) return;
        //只有是同一组的第一个才显示悬浮栏
        if (pos == 0 || isFirstInGroup(pos)) {
            outRect.top = topGap;
            if (dataList.get(pos) == "") {
                outRect.top = 0;
            }
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
            String text = dataList.get(position).toUpperCase();
            if (isFirstInGroup(position)) {
                float top = view.getTop() - topGap;
                float bottom = view.getTop();
                //绘制悬浮栏
                Log.i("onDraw","topGap="+topGap+"  left="+left+"   right="+right+"   childCount="+childCount+"   position="+position+" top="+top+"  bottom="+bottom);
                c.drawRect(left, top, right, bottom, paint);
                //绘制文本
                c.drawText(text, left + 2 * alignBottom, bottom - 2*alignBottom, textPaint);
                c.drawText("查看月账单", ScreenUtils.dip2px(257), bottom - 2*alignBottom, textPaint);
                c.drawBitmap(bitmap,ScreenUtils.dip2px(336),bottom -ScreenUtils.dip2px(28),null);
            }
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        int itemCount = state.getItemCount();
        int childCount = parent.getChildCount();
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        String preGroupId = "";
        String groupId = "-1";
        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(view);
            preGroupId = groupId;
            groupId = dataList.get(position);
            if (groupId.equals("-1") || groupId.equals(preGroupId)) continue;
            String text = dataList.get(position).toUpperCase();
            if (TextUtils.isEmpty(text)) continue;
            int viewBottom = view.getBottom();
            float textY = Math.max(topGap, view.getTop());
            //下一个和当前不一样移动当前
            if (position + 1 < itemCount) {
                String nextGroupId = dataList.get(position + 1);
                //组内最后一个view进入了header
                if (nextGroupId != groupId && viewBottom < textY) {
                    textY = viewBottom;
                }
            }
            //textY - topGap决定了悬浮栏绘制的高度和位置
            c.drawRect(left, textY - topGap, right, textY, paint);
            //left+2*alignBottom 决定了文本往左偏移的多少（加-->向左移）
            //textY-alignBottom  决定了文本往右偏移的多少  (减-->向上移)
            c.drawText(text, left + 2 * alignBottom, textY - 2*alignBottom, textPaint);
            c.drawText("查看月账单", ScreenUtils.dip2px(257), textY - 2*alignBottom, textPaint);
            c.drawBitmap(bitmap,ScreenUtils.dip2px(336),textY -ScreenUtils.dip2px(28),null);
        }
    }


    /**
     * 判断是不是组中的第一个位置
     *
     * @param pos
     * @return
     */
    private boolean isFirstInGroup(int pos) {
        if (pos == 0) {
            return true;
        } else {
            // 因为是根据 字符串内容的相同与否 来判断是不是同意组的，所以此处的标记id 要是String类型
            // 如果你只是做联系人列表，悬浮框里显示的只是一个字母，则标记id直接用 int 类型就行了
            String prevGroupId = dataList.get(pos - 1);
            String groupId = dataList.get(pos);
            //判断前一个字符串 与 当前字符串 是否相同
            if (prevGroupId.equals(groupId)) {
                return false;
            } else {
                return true;
            }
        }
    }
}

