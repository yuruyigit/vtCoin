package com.block.vtCoin.widget.dialog;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.block.vtCoin.R;
import com.block.vtCoin.deal.order.OrderActivity;
import com.block.vtCoin.widget.dialog.base.DialogViewHolder;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * @Description
 * @Author lijie
 * @Date 2017/8/14.
 */
public class OrderTypeDialog extends DialogViewHolder {


    @Bind(R.id.buy_order_image)
    ImageView buyOrderImage;
    @Bind(R.id.buy_order)
    LinearLayout buyOrder;
    @Bind(R.id.sell_order_image)
    ImageView sellOrderImage;
    @Bind(R.id.sell_order)
    LinearLayout sellOrder;
    @Bind(R.id.arbitration_order_image)
    ImageView arbitrationOrderImage;
    @Bind(R.id.arbitration_order)
    LinearLayout arbitrationOrder;
    @Bind(R.id.home_popu)
    RelativeLayout homePopu;
    private Context context;

    public OrderTypeDialog(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected int getWidth() {
        return WindowManager.LayoutParams.MATCH_PARENT;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_order_type;
    }
    @OnClick({R.id.buy_order, R.id.sell_order, R.id.arbitration_order, R.id.home_popu})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buy_order:
                context.startActivity(new Intent(context, OrderActivity.class).putExtra("ordertype","1"));
                dismiss();
                break;
            case R.id.sell_order:
                context.startActivity(new Intent(context, OrderActivity.class).putExtra("ordertype","2"));
                dismiss();
                break;
            case R.id.arbitration_order:
                context.startActivity(new Intent(context, OrderActivity.class).putExtra("ordertype","3"));
                dismiss();
                break;
            case R.id.home_popu:
                dismiss();
                break;
        }
    }

    protected int getGravity(){
        return Gravity.CENTER;
    }

    private MoneyTypeListener listener;

    public void setListener(MoneyTypeListener listener) {
        this.listener = listener;
    }

    public interface MoneyTypeListener {

        void getString(String moneytype);
    }
}
