package com.block.vtCoin.widget.dialog.password;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * @Description  拦截一切Touch事件
 * @Author Yanx
 * @Date 16-12-29.
 */
public class NoTouchFrameLayout extends FrameLayout
{
    public NoTouchFrameLayout(Context context)
    {
        super(context);
    }

    public NoTouchFrameLayout(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public NoTouchFrameLayout(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev)
    {
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        return true;
    }
}
