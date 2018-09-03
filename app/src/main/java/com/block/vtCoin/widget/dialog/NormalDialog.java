package com.block.vtCoin.widget.dialog;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.block.vtCoin.R;
import com.block.vtCoin.util.ScreenUtils;
import com.block.vtCoin.widget.dialog.base.DialogViewHolder;
import butterknife.Bind;
import butterknife.OnClick;

/**
 * @Description
 * @Author lijie
 * @Date 2017/7/23.
 */
public class NormalDialog extends DialogViewHolder {

    @Bind(R.id.tvTitle)
    TextView mTvTitle;
    @Bind(R.id.tvMsg)
    TextView mTvMsg;
    @Bind(R.id.tvLeft)
    TextView mTvLeft;
    @Bind(R.id.tvRight)
    TextView mTvRight;
    @Bind(R.id.llTwoButton)
    LinearLayout mLlTwoButton;
    @Bind(R.id.tvMsg_do)
    TextView tvMsgDo;

    private View.OnClickListener mLeftListener,mRightListener;

    public NormalDialog(Context context) {
        super(context);
    }

    public NormalDialog(Context context,View.OnClickListener rightListener) {
        super(context);
        this.mRightListener=rightListener;
    }
    public NormalDialog(Context context, View.OnClickListener leftListener, View.OnClickListener rightListener) {
        super(context);
        this.mLeftListener=leftListener;
        this.mRightListener=rightListener;
    }

    /**
     * 提示框宽度 ：自适应
     *
     * @return
     */
    @Override
    protected int getWidth() {
        return ScreenUtils.getScreenWidth(getContext()) / 100 * 85;
//        return WindowManager.LayoutParams.WRAP_CONTENT;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.normal_dialog;
    }

    /**
     * 设置左边按钮的文字
     *
     * @param leftText 文字
     */
    public void setLeftText(String leftText) {
        if (!TextUtils.isEmpty(leftText))
            mTvLeft.setText(leftText);
    }

    /*
    * 设置左边按钮的文字颜色
    * */
    public void setLeftButtonColor(int id) {
        mTvLeft.setTextColor(getContext().getResources().getColor(id));
    }

    /*
   * 设置右边按钮的文字颜色
   * */
    public void setRightButtonColor(int id) {
        mTvRight.setTextColor(getContext().getResources().getColor(id));
    }

    /**
     * 设置右边按钮的文字
     *
     * @param rightText 文字
     */
    public void setRightText(String rightText) {
        if (!TextUtils.isEmpty(rightText))
            mTvRight.setText(rightText);
    }


    /**
     * 设置提示内容
     *
     * @param msg 提示内容，为null/""/空格时提示内容栏隐藏
     */
    public void setMsg(String msg) {
        if (TextUtils.isEmpty(msg))
            mTvMsg.setVisibility(View.GONE);
        else {
            mTvMsg.setVisibility(View.VISIBLE);
            mTvMsg.setText(msg);
        }
    }

    /**
     * 设置提示标题
     *
     * @param title 提示标题，为null/""/空格时提示标题栏隐藏
     */
    public void setTitle(String title) {
        if (TextUtils.isEmpty(title))
            mTvTitle.setVisibility(View.GONE);
        else {
            mTvTitle.setVisibility(View.VISIBLE);
            mTvTitle.setText(title);
        }
    }
    public void setMsgdo(String msg) {
        if (TextUtils.isEmpty(msg))
            tvMsgDo.setVisibility(View.GONE);
        else {
            tvMsgDo.setVisibility(View.VISIBLE);
            tvMsgDo.setText(msg);
        }
    }


    @OnClick({R.id.tvLeft, R.id.tvRight,})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvLeft:
                if (mLeftListener != null)
                    mLeftListener.onClick(view);
                dismiss();
                break;
            case R.id.tvRight:
                if (mRightListener != null)
                    mRightListener.onClick(view);
                dismiss();
                break;
        }
    }
}
