package com.block.vtCoin.deal.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.block.vtCoin.R;
import com.block.vtCoin.deal.adapter.DealAdapter;
import com.block.vtCoin.widget.dialog.BuyDialog;
import com.block.vtCoin.widget.dialog.NormalDialog;
import com.block.vtCoin.main.MainActivity;
import com.block.vtCoin.util.ScreenUtils;
import com.block.vtCoin.widget.dialog.password.PayPasswordDialog;
import com.block.vtCoin.entity.DealPriceEntity;
import com.block.vtCoin.widget.popupwindow.ListViewPopup;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
public class BuyFragment extends Fragment{
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    @Bind(R.id.refresh_layout)
    TwinklingRefreshLayout refreshLayout;
    @Bind(R.id.coin_type)
    TextView coinType;
    @Bind(R.id.coin_money)
    TextView coinMoney;
    @Bind(R.id.coin_id)
    TextView coinId;
    @Bind(R.id.coin_limit)
    TextView coinLimit;
    @Bind(R.id.coin_deal_num)
    TextView coinDealNum;
    @Bind(R.id.coin_deal_evaluate)
    TextView coinDealEvaluate;
    @Bind(R.id.coin_deal_way)
    TextView coinDealWay;
    @Bind(R.id.deal_item_ly)
    LinearLayout dealItemLy;
    @Bind(R.id.coin_image)
    ImageView coinImage;
    @Bind(R.id.choose_coin_layout)
    LinearLayout chooseCoinLayout;
    @Bind(R.id.coin_type_text)
    TextView coinTypeText;
    @Bind(R.id.deal_buy_into)
    TextView dealBuyInto;
    @Bind(R.id.icon_num)
    EditText iconNum;
    @Bind(R.id.money_num)
    EditText moneyNum;
    private MainActivity activity;
    private String[] items = {"BTC", "LTC", "VC", "SC"};
    private ListViewPopup coinPopup;
    private List<DealPriceEntity> data = new ArrayList<>();
    private DealAdapter dealAdapter;
    private PayPasswordDialog payPasswordDialog;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buy, null);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        dealAdapter = new DealAdapter(getContext(), data);
        dealAdapter.setOnItemClickListener(new DealAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                startActivity(new Intent(getActivity(), DealDetailActivity.class));
            }
        });
        recyclerView.setAdapter(dealAdapter);
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        initData();
                        refreshLayout.onFinishRefresh();
                    }
                },2000);
            }
        });
        refreshLayout.startRefresh();
        payPasswordDialog = new PayPasswordDialog(getContext());

    }

    private void initData() {
        data.clear();
        for (int i = 0; i < 10; i++) {
            DealPriceEntity entity = new DealPriceEntity();
            data.add(entity);
        }
        dealAdapter.notifyDataSetChanged();
    }

    private void showPopup(View view,int width,int height) {
        coinImage.setImageResource(R.mipmap.business_down_back);
        if (coinPopup == null) {
            coinPopup = new ListViewPopup(getContext(),items,width,height);
            coinPopup.setOnItemListener(new ListViewPopup.OnItemListener() {
                @Override
                public void onItemClick(int position) {
                    coinTypeText.setText(items[position]);
                }
            });
            coinPopup.setDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    coinImage.setImageResource(R.mipmap.business_down);
                }
            });
        }
        coinPopup.show(view);
    }

    @OnClick({R.id.choose_coin_layout, R.id.deal_buy_into,R.id.deal_item_ly})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.choose_coin_layout:
                 /*获取偏移的宽度*/
                int width= ScreenUtils.dip2px(115);//px
                int height=ScreenUtils.dip2px(40*4+12);//px
                showPopup(chooseCoinLayout,width,height);
                break;
            case R.id.deal_buy_into:
                if (TextUtils.isEmpty(getCoinNum())) {
                    activity.showTip(getString(R.string.please_into_num));
                    return;
                }
                if (TextUtils.isEmpty(getMoneyNum())) {
                    activity.showTip(getString(R.string.please_into_money));
                    return;
                }
                showBuyDialog();
                break;
            case R.id.deal_item_ly:
                startActivity(new Intent(getActivity(), DealDetailActivity.class));
                break;
        }
    }
    public void showBuyDialog() {
        BuyDialog buyDialog = new BuyDialog(getContext(), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPayPassword();
            }
        });
        buyDialog.show();
    }

    public String getCoinNum() {
        return iconNum.getText().toString();
    }

    public String getMoneyNum() {
        return moneyNum.getText().toString();
    }

    public void showPayPassword() {

        payPasswordDialog.show();
        payPasswordDialog.setOnPasswordChangedListener(new PayPasswordDialog.OnPasswordChangedListener() {
            @Override
            public void onTextChanged(String psw) {
            }
            @Override
            public void onInputFinish(String psw) {
                ShowTip();
            }
        });
    }

    public void ShowTip() {
        payPasswordDialog.clear();
        payPasswordDialog.dismiss();
        NormalDialog dialog = new NormalDialog(getContext(), new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        dialog.setTitle(getString(R.string.Warm_prompt));
        dialog.setMsg(getString(R.string.tip_buyer_sure));
        dialog.setMsgdo(getString(R.string.go_pay));
        dialog.setLeftText(getString(R.string.cancel));
        dialog.setRightText(getString(R.string.sure));
        dialog.show();
    }

    @Override
    public void onDestroyView() {
        ButterKnife.unbind(this);
        super.onDestroyView();
    }
}
