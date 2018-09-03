package com.block.vtCoin.widget.base;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import butterknife.ButterKnife;

/**
 * @Description 自定义组合控件基类
 * @Author lijie
 * @Date 2017/7/12.
 */
public abstract class BaseCombinationView extends LinearLayout {
    public BaseCombinationView(Context context){
        this(context,null);
    }


    public BaseCombinationView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        initLayout(context);
    }
    private void initLayout(Context context)
    {
        if (getLayoutId() != 0)
        {
            LayoutInflater.from(context).inflate(getLayoutId(), this);
        }
        ButterKnife.bind(this);
    }


    /**
     * 获得xml布局的ID
     * @return layoutId
     */
    public abstract int getLayoutId();

    @Override
    protected void onFinishInflate()
    {
        super.onFinishInflate();
        ButterKnife.unbind(this);
    }

    /**
     * 如果不可用Bind注解的时候请用些方法来findid
     * @param rid view id
     * @param <T> 泛型view
     * @return
     */
    protected  <T extends View> T find(int rid){
        return (T)findViewById(rid);
    }
}
