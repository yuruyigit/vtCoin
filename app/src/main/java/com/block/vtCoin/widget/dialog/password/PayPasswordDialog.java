package com.block.vtCoin.widget.dialog.password;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.block.vtCoin.R;
import com.block.vtCoin.widget.dialog.base.DialogViewHolder;
import com.jungly.gridpasswordview.GridPasswordView;
import com.jungly.gridpasswordview.PasswordType;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * @Description 支付密码对话框
 * @Author Yanx
 * @Date 16-12-29.
 */
public class
PayPasswordDialog extends DialogViewHolder
{
    @Bind(R.id.ivClose)
    ImageView mIvClose;
    @Bind(R.id.tvPaypsdTip)
    TextView mTvPaypsdTip;
    @Bind(R.id.gpvPaypsd)
    GridPasswordView mGpvPaypsd;
    @Bind(R.id.llPaypsd)
    LinearLayout mLlPaypsd;
    @Bind(R.id.tvForgetPaypsd)
    TextView mTvForgetPaypsd;
    @Bind(R.id.tvNum1)
    TextView mTvNum1;
    @Bind(R.id.tvNum2)
    TextView mTvNum2;
    @Bind(R.id.tvNum3)
    TextView mTvNum3;
    @Bind(R.id.tvNum4)
    TextView mTvNum4;
    @Bind(R.id.tvNum5)
    TextView mTvNum5;
    @Bind(R.id.tvNum6)
    TextView mTvNum6;
    @Bind(R.id.tvNum7)
    TextView mTvNum7;
    @Bind(R.id.tvNum8)
    TextView mTvNum8;
    @Bind(R.id.tvNum9)
    TextView mTvNum9;
    @Bind(R.id.tvNum0)
    TextView mTvNum0;
    @Bind(R.id.fldelete)
    FrameLayout mFldelete;

    private Context mContext;
    private StringBuilder mPaypsd = new StringBuilder();
    private OnPasswordChangedListener mListener = null;
   ;

    private String mTemporaryPayPwd;
    private OnAfterSetPayPwd mAfterSetPayPwd = null;



    public PayPasswordDialog(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    private void initView()
    {
        mGpvPaypsd.setPasswordType(PasswordType.NUMBER);
        mGpvPaypsd.setPasswordVisibility(false);
    }

    private void inputPaypsd(String paypsd)
    {
        if (mPaypsd.length() < 6)
            mPaypsd.append(paypsd);
        mGpvPaypsd.setPassword(mPaypsd.toString());
        notifyTextChanged(mPaypsd.toString());
    }

    private void deletePaypsd()
    {
        int i = mPaypsd.length();
        if (i > 0)
            mPaypsd.delete(i - 1, i);
        mGpvPaypsd.setPassword(mPaypsd.toString());
        notifyTextChanged(mPaypsd.toString());
    }
//
//    /**
//     * 设置支付密码提示信息
//     *
//     * @param message
//     */
//    public void setPaypsdMessage(String message)
//    {
//        mTvPaypsdMessage.setText(message);
//        mTvPaypsdMessage.setVisibility(View.VISIBLE);
//    }

    /**
     * 设置密码改变监听
     *
     * @param listener
     */
    public void setOnPasswordChangedListener(OnPasswordChangedListener listener)
    {
        mListener = listener;
    }



    public void afterSetPayPwd(OnAfterSetPayPwd afterSet)
    {
        mAfterSetPayPwd = afterSet;
    }

    public interface OnPasswordChangedListener
    {
        void onTextChanged(String psw);

        void onInputFinish(String psw);
    }

    public interface OnAfterSetPayPwd
    {
        void afterSetPayPwd(String psw);
    }

    public void clear()
    {
        mGpvPaypsd.clearPassword();
        mPaypsd.delete(0, mPaypsd.length());
    }

    private void notifyTextChanged(String paypPsd)
    {
        if (mListener == null)
            return;

        String currentPsw = paypPsd;
        mListener.onTextChanged(currentPsw);

        if (currentPsw.length() == 6)
            mListener.onInputFinish(currentPsw);
    }

    @Override
    protected int getWidth()
    {
        return WindowManager.LayoutParams.MATCH_PARENT;
    }

    @Override
    protected int getLayoutId()
    {
        return R.layout.dialog_paypassword;
    }

    @Override
    protected int getGravity()
    {
        return Gravity.BOTTOM;
    }




    @OnClick({R.id.ivClose, R.id.tvForgetPaypsd, R.id.tvNum1, R.id.tvNum2, R.id.tvNum3, R.id
            .tvNum4, R.id.tvNum5, R.id.tvNum6, R.id.tvNum7, R.id.tvNum8, R.id.tvNum9, R.id
            .tvNum0, R.id.fldelete})
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.ivClose:
                dismiss();
                break;
            case R.id.tvForgetPaypsd:
//                mContext.startActivity(new Intent(mContext, ForgetDelPadActivity.class));
                dismiss();
                break;
            case R.id.tvNum1:
                inputPaypsd("1");
                break;
            case R.id.tvNum2:
                inputPaypsd("2");
                break;
            case R.id.tvNum3:
                inputPaypsd("3");
                break;
            case R.id.tvNum4:
                inputPaypsd("4");
                break;
            case R.id.tvNum5:
                inputPaypsd("5");
                break;
            case R.id.tvNum6:
                inputPaypsd("6");
                break;
            case R.id.tvNum7:
                inputPaypsd("7");
                break;
            case R.id.tvNum8:
                inputPaypsd("8");
                break;
            case R.id.tvNum9:
                inputPaypsd("9");
                break;
            case R.id.tvNum0:
                inputPaypsd("0");
                break;
            case R.id.fldelete:
                deletePaypsd();
                break;
        }
    }
}
