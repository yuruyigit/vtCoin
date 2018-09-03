package com.block.vtCoin.main.widget;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.block.vtCoin.R;
import com.block.vtCoin.widget.base.BaseCombinationView;

import butterknife.Bind;

/**
 * @Description
 * @Author lijie
 * @Date 2017/7/12.
 */
public class TabView extends BaseCombinationView {
    @Bind(R.id.ivTabIcon)
    ImageView ivTabIcon;
    @Bind(R.id.tvTabName)
    TextView tvTabName;
    private int resIconCheck;
    private int resIconUnCheck;

    public TabView(Context context)
    {
        this(context,0,0,"");
    }

    public TabView(Context context, int resIconCheck, int resIconUnCheck, int resName)
    {
        super(context);
        this.resIconCheck = resIconCheck;
        this.resIconUnCheck = resIconUnCheck;
        tvTabName.setText(resName);
        setIcon(resIconUnCheck);
    }
    public TabView(Context context,int resIconCheck,int resIconUnCheck,String resName)
    {
        super(context);
        this.resIconCheck = resIconCheck;
        this.resIconUnCheck = resIconUnCheck;
        tvTabName.setText(resName);
        setIcon(resIconUnCheck);
    }

    @Override
    public int getLayoutId()
    {
        return R.layout.tab;
    }

    public void setName(String text){
        tvTabName.setText(text);
    }
    public void setNameColor(int color){
        tvTabName.setTextColor(getResources().getColor(color));
    }
    public void setIcon(int res){
        ivTabIcon.setBackgroundResource(res);
    }

    public void check(boolean check){
        if (check){
            setIcon(resIconCheck);
            setNameColor(R.color.blue0);
        }else {
            setIcon(resIconUnCheck);
            setNameColor(R.color.black2);
        }
    }

    public void setTab(int resIcon,int resStr){
        setTab(resIcon,getResources().getString(resStr));
    }
    public void setTab(int resIcon,String resStr){
        setIcon(resIcon);
        setName(resStr);

    }
}
