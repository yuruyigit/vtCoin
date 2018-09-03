package com.block.vtCoin.util;

import android.content.Context;
import android.os.CountDownTimer;
import android.widget.TextView;

import com.block.vtCoin.R;


/**
 * @Description
 * @Author MoseLin
 * @Date 2016/8/11.
 */

public class CountDownUtil extends CountDownTimer {
    private Context mContext;
    private TextView mTextView;
    private String tip="";
    private onFinishListener mListener;

    public CountDownUtil(Context context, TextView mTextView, long millisInFuture, long countDownInterval,
                         onFinishListener listener) {
        super(millisInFuture, countDownInterval);
        this.mContext = context;
        this.mTextView = mTextView;
        mListener = listener;
        tip = mContext.getString(R.string.get_again);
    }

    public CountDownUtil(Context context, TextView mTextView, long millisInFuture, long countDownInterval, String tip,
                         onFinishListener listener) {
        super(millisInFuture, countDownInterval);
        this.mContext = context;
        this.mTextView = mTextView;
        mListener = listener;
        this.tip = tip;
    }


    /**
     * @param l 显示时间
     */
    @Override
    public void onTick(long l) {
        if (tip.equals("")) {
            mTextView.setText(mContext.getString(R.string.before_time)+l/1000+mContext.getString(R.string.after_time));
        } else
            mTextView.setText(tip + "(" + (l / 1000) + mContext.getString(R.string.second) + ")");  //设置倒计时时间
    }


    @Override
    public void onFinish() {
        mTextView.setText(tip);
        if (mListener != null)
            mListener.finish();
    }

    public interface onFinishListener {
        void finish();
    }
}
