package com.block.vtCoin.mine.sale_coin;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.vtCoin.R;
import com.block.vtCoin.main.BaseActivity;
import com.block.vtCoin.mine.bill.turn_in.TurnInBillActivity;
import com.block.vtCoin.mine.setting.account_address.ChooseAddressActivity;
import com.block.vtCoin.util.ScreenUtils;
import com.block.vtCoin.widget.popupwindow.ListViewPopup;
import com.block.vtCoin.widget.title.NormalTitleBar;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class TurnOutActivity extends BaseActivity {

    @Bind(R.id.tv_address)
    TextView tvAddress;
    @Bind(R.id.et_amount)
    EditText etAmount;
    private int type;
    private String title;
    private ListViewPopup coinPopup;
    private String[] data = {"CNY", "USD", "HKD", "UER", "SEK", "PHP"};
    private NormalTitleBar normalTitleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handIntent();
        setContentView(R.layout.activity_turn_out);
        setUltimateBar(R.color.blue1);
        setBackground(R.color.gray7);


    }

    private void handIntent() {
        type = getIntent().getIntExtra("type", -1);
        switch (type) {
            case -1:
            case 0://BTC
                title = "转出BTC";
                break;
            case 1:
                title = "转出LTC";
                break;
        }
    }

    private void showPopup(View view, int width, int height, int xoff) {
        normalTitleBar.setTitleDrawable(R.mipmap.business_down_back);
        if (coinPopup == null) {
            coinPopup = new ListViewPopup(this,data,width,height);
            coinPopup.setOnItemListener(new ListViewPopup.OnItemListener() {
                @Override
                public void onItemClick(int position) {
                    normalTitleBar.setTitle("转入"+data[position]);
                }
            });
            coinPopup.setDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    normalTitleBar.setTitleDrawable(R.mipmap.business_down);
                }
            });
        }
        coinPopup.show(view,-xoff,0);
    }


    @Override
    public View getTitleBar() {
        normalTitleBar = getNormalTitleBar().setTitle(title);
        if (type == -1) {
            //从转入币而来
            normalTitleBar.setTitleDrawable(R.mipmap.business_down).setTitleListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    normalTitleBar.setTitleDrawable(R.mipmap.business_down_back);
                    int width= ScreenUtils.dip2px(160);//px
                    int height=ScreenUtils.dip2px(40*5+12);//px
                    int[] widthAndHeight1 = ScreenUtils.getWidthAndHeight1(normalTitleBar.getTitleView());//px
                    int xoff=width/2-widthAndHeight1[0]/2;
                    showPopup(normalTitleBar.getTitleView(),width,height,xoff);
                }
            });
        }
        normalTitleBar.setRightDrawable(R.mipmap.wallet_ansfer).setRightListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toActivity(TurnInBillActivity.class);
            }
        });

        return normalTitleBar;
    }

    @Override
    protected List<BasePresenter> getPresenter(List<BasePresenter> presenters) {
        return null;
    }

    @OnClick({R.id.accept_address, R.id.sure})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.accept_address:
                toActivity(ChooseAddressActivity.class);
                break;
            case R.id.sure:
                showTip("成功");
                break;
        }
    }
}
