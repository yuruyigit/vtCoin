package com.block.vtCoin.mine.buy_coin;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.vtCoin.R;
import com.block.vtCoin.entity.BalanceAddressEntity;
import com.block.vtCoin.entity.WalletEntity;
import com.block.vtCoin.main.BaseActivity;
import com.block.vtCoin.mine.bill.turn_in.TurnInBillActivity;
import com.block.vtCoin.mine.buy_coin.p.BalanceAddressPresenter;
import com.block.vtCoin.mine.buy_coin.v.BalanceAddressViewImpl;
import com.block.vtCoin.mine.wallet.p.CapitalPresenter;
import com.block.vtCoin.mine.wallet.v.CapitalViewImpl;
import com.block.vtCoin.request.ApiAction;
import com.block.vtCoin.request.MyModel;
import com.block.vtCoin.util.ScreenUtils;
import com.block.vtCoin.util.UnitUtil;
import com.block.vtCoin.widget.popupwindow.ListViewPopup;
import com.block.vtCoin.widget.title.NormalTitleBar;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.qrcode.core.BGAQRCodeUtil;
import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder;

public class TurnInActivity extends BaseActivity {
    @Bind(R.id.tv_stock)
    TextView tvStock;
    @Bind(R.id.tv_coin_stock)
    TextView tvCoinStock;
    @Bind(R.id.iv_vis)
    ImageView ivVis;
    @Bind(R.id.tv_copy)
    TextView tvCopy;
    @Bind(R.id.tv_address)
    TextView tvAddress;
    @Bind(R.id.qr_code)
    ImageView qrCode;
    private BalanceAddressPresenter balanceAddressPresenter;
    private List<BalanceAddressEntity.DataBean> mData;
    private int from = 0;
    private String[] coinTypes = null;
    private String title = "";
    private String currentCoin;
    private String address = "";
    private ListViewPopup coinPopup;
    private NormalTitleBar normalTitleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handIntent();
        setContentView(R.layout.activity_turn_in);
        setUltimateBar(R.color.blue1);
        initView();
    }

    private void handIntent() {
        from = getIntent().getIntExtra("from", 0);
        if (from == 0) {//直接进到这个界面
            currentCoin = "BTC";
        } else if (from == 1) {//从钱包界面跳转过来的
            currentCoin = getIntent().getStringExtra("coinType");
        }
        title = "转入" + currentCoin;
    }

    private void initView() {
        balanceAddressPresenter.getBalanceAddress();
    }

    private void showPopup(View view, int xOff) {
        normalTitleBar.setTitleDrawable(R.mipmap.business_down_back);
        coinPopup.show(view, -xOff, 0);
    }

    private void showCode(String address) {
        tvAddress.setText(address);
        new AsyncTask<String, Void, Bitmap>() {
            @Override
            protected Bitmap doInBackground(String... params) {
                return QRCodeEncoder.syncEncodeQRCode(params[0], BGAQRCodeUtil.dp2px(TurnInActivity.this, 110), Color.BLACK);
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                if (bitmap != null) {
                    qrCode.setImageBitmap(bitmap);
                } else {
                    showTip("生成二维码失败");
                }
            }
        }.execute(address);
    }

    @Override
    public View getTitleBar() {
        normalTitleBar = getNormalTitleBar().setTitle(title);
        normalTitleBar.setTitleDrawable(R.mipmap.business_down).setTitleListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (coinTypes != null) {
                    normalTitleBar.setTitleDrawable(R.mipmap.business_down_back);
                    int width = ScreenUtils.dip2px(160);//px
                    int[] widthAndHeight1 = ScreenUtils.getWidthAndHeight1(normalTitleBar.getTitleView());//px
                    int xOff = width / 2 - widthAndHeight1[0] / 2;
                    showPopup(normalTitleBar.getTitleView(), xOff);
                } else
                    showTip("暂未请求到币种");
            }
        });
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
        balanceAddressPresenter = new BalanceAddressPresenter();
        balanceAddressPresenter.setModelView(new MyModel<BalanceAddressEntity>(), new BalanceAddressViewImpl(this, ApiAction.Preference_ListUserAddressWithBanlance));
        presenters.add(balanceAddressPresenter);
        return presenters;
    }

    @OnClick(R.id.tv_copy)
    public void onClick() {
        address = tvAddress.getText().toString();
    }

    private void initPopup() {
        if (coinPopup == null) {
            int width = ScreenUtils.dip2px(160);//px
            int height = ScreenUtils.dip2px(40 * coinTypes.length + 12);//px
            coinPopup = new ListViewPopup(this, coinTypes, width, height);
            coinPopup.setOnItemListener(new ListViewPopup.OnItemListener() {
                @Override
                public void onItemClick(int position) {
                    if (!coinTypes[position].equals(currentCoin)) {//切换币种了
                        currentCoin = coinTypes[position];
                        normalTitleBar.setTitle("转入" + currentCoin);
                        for (int i = 0; i < mData.size(); i++) {
                            if (mData.get(i).getCoinType().equals(currentCoin)) {
                                chooseCoinType(mData.get(i));
                            }
                        }
                    }
                }
            });
            coinPopup.setDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    normalTitleBar.setTitleDrawable(R.mipmap.business_down);
                }
            });
        }
    }

    public void setBalanceAddress(BalanceAddressEntity entity) {
        if (entity != null) {
            mData = entity.getData();
            coinTypes = new String[mData.size()];
            for (int i = 0; i < mData.size(); i++) {
                if (mData.get(i).getCoinType().equals(currentCoin)) {
                    chooseCoinType(mData.get(i));
                }
                coinTypes[i] = mData.get(i).getCoinType();
            }
            initPopup();
        }
    }

    public void chooseCoinType(BalanceAddressEntity.DataBean dataBean) {
        tvStock.setText(UnitUtil.formatFour(dataBean.getBalance()) + "");
        tvCoinStock.setText(dataBean.getCoinType());
        showCode(dataBean.getAddr());
    }
}
