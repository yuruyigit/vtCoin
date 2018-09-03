package com.block.vtCoin.widget.popupwindow;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.PopupWindow;
import com.block.vtCoin.R;

import butterknife.ButterKnife;

/**
 * @Description
 * @Author lijie
 * @Date 2017/7/12.
 */
public abstract class BasePopupWindow {

    private View view;//自定义dialog的View
    ListView listView;
    private final PopupWindow popup;
    private Context context;
    private PopupWindow.OnDismissListener dismissListener;
    public BasePopupWindow(Context context){
        this.context = context;
        view = LayoutInflater.from(context).inflate(getLayoutId(),null);
        ButterKnife.bind(this,view);//注册注解
        popup = new PopupWindow(view,getWidth(),getHeight());
        popup.setOnDismissListener(new PopupWindow.OnDismissListener()
        {
            @Override
            public void onDismiss()
            {
                if (dismissListener != null)
                    dismissListener.onDismiss();
            }
        });
        popup.setOutsideTouchable(true);
        popup.setFocusable(true);
        popup.setTouchable(true);
        popup.setBackgroundDrawable(new ColorDrawable(context.getResources().getColor(android.R.color.transparent)));
    }

    protected  int getHeight(){
        return WindowManager.LayoutParams.WRAP_CONTENT;
    }
    protected abstract int getWidth();
    protected abstract int getLayoutId();

    public void show(View anchor){
        if(!popup.isShowing()) {
            popup.showAsDropDown(anchor);
            //相对某个控件的位置（正左下方），无偏移
        }
    }
    public void show(View anchor, int xoff, int yoff){
        if (!popup.isShowing())
            popup.showAsDropDown(anchor,xoff,yoff);
        //相对某个控件的位置，有偏移;xoff表示x轴的偏移，正值表示向右，负值表示向左；yoff表示相对y轴的偏移，正值是向下，负值是向上；
    }
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void show(View anchor, int xoff, int yoff, int gravity){
        if (!popup.isShowing())
            popup.showAsDropDown(anchor,xoff,yoff,gravity);
    }
    public void showAtLocation(View anchor,int gravity, int xoff, int yoff){
        if (!popup.isShowing())
            popup.showAtLocation(anchor,gravity,xoff,yoff);
        //相对于父控件的位置（例如正中央Gravity.CENTER，下方Gravity.BOTTOM等），可以设置偏移或无偏移
    }

    public void dismiss(){
        if (popup.isShowing())
            popup.dismiss();
    }
    public Context getContext()
    {
        return context;
    }

    /**
     * 设置PopupWindow的高
     * @param height int
     */
    public void setHeight(int height)
    {
//        int h = ScreenUtils.getScreenHeight(context)-(height+ScreenUtils.getStatusHeight(context));
        popup.setHeight(height);
    }
    public void setWidth(int width)
    {
        popup.setWidth(width);
    }

    public boolean isShow()
    {
        return popup.isShowing();
    }

    public boolean isDismiss()
    {
        return !popup.isShowing();
    }

    public void setDismissListener(PopupWindow.OnDismissListener dismissListener)
    {
        this.dismissListener = dismissListener;
    }

    public void setBackgroundRes(int res){
        view.setBackgroundResource(res);
    }
    protected int getGravity(){
        return Gravity.CENTER;
    }
    /*解绑ButterKnife*/
    public void unbind(){
        ButterKnife.unbind(this);
    }
}
