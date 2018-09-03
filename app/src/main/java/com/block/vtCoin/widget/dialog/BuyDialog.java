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
public class BuyDialog extends DialogViewHolder {


    @Bind(R.id.tvTitle)
    TextView tvTitle;
    @Bind(R.id.deal_person_text)
    TextView dealPersonText;
    @Bind(R.id.unit_price_text)
    TextView unitPriceText;
    @Bind(R.id.deal_num)
    TextView dealNum;
    @Bind(R.id.deal_pay)
    TextView dealPay;
    @Bind(R.id.tvLeft)
    TextView tvLeft;
    @Bind(R.id.tvRight)
    TextView tvRight;
    @Bind(R.id.llContent)
    LinearLayout llContent;

    private View.OnClickListener mLeftListener,mRightListener;

    public BuyDialog(Context context) {
        super(context);
    }
    public BuyDialog(Context context, View.OnClickListener rightListener) {
        super(context);
        this.mRightListener=rightListener;
    }
    public BuyDialog(Context context, View.OnClickListener leftListener, View.OnClickListener rightListener) {
        super(context);
        this.mLeftListener=leftListener;
        this.mRightListener=rightListener;
    }

    public void setTvTitle(String title) {
        if (!TextUtils.isEmpty(title))
            tvTitle.setText(title);

    }

    public void setDealPersonText(String dealPerson) {
        if (!TextUtils.isEmpty(dealPerson))
            dealPersonText.setText(dealPerson);
    }

    public void setUnitPriceText(String unitPrice) {
        if (!TextUtils.isEmpty(unitPrice))
            unitPriceText.setText(unitPrice);

    }

    public void setDealNum(String unm) {
        if (!TextUtils.isEmpty(unm))
            dealNum.setText(unm);

    }

    public void setDealPay(String dealpay) {
        if (!TextUtils.isEmpty(dealpay))
            dealPay.setText(dealpay);
    }
    /**
     * 设置左边按钮的文字
     *
     * @param leftText 文字
     */
    public void setLeftText(String leftText) {
        if (!TextUtils.isEmpty(leftText))
            tvLeft.setText(leftText);
    }
    /**
     * 设置右边按钮的文字
     *
     * @param rightText 文字
     */
    public void setRightText(String rightText) {
        if (!TextUtils.isEmpty(rightText))
            tvRight.setText(rightText);
    }

    @Override
    protected int getWidth() {
//        return WindowManager.LayoutParams.MATCH_PARENT;
        return ScreenUtils.getScreenWidth(getContext()) / 100* 85;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.buy_dialog;
    }

    @OnClick({R.id.tvLeft, R.id.tvRight})
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
