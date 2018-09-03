package com.block.vtCoin.main.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.block.net.util.L;
import com.block.vtCoin.R;
import com.block.vtCoin.constant.ApolloAction;
import com.block.vtCoin.entity.HandleOrderEntity;
import com.block.vtCoin.entity.IsDealerEntity;
import com.block.vtCoin.entity.PersonInfoEntity;
import com.block.vtCoin.main.MainActivity;
import com.block.vtCoin.main.fragment.p.HandleOrderPresenter;
import com.block.vtCoin.main.fragment.p.IsDealerPresenter;
import com.block.vtCoin.main.fragment.p.PersonInfoPresenter;
import com.block.vtCoin.main.fragment.v.HandlerOrderViewImpl;
import com.block.vtCoin.main.fragment.v.IsDealerViewImpl;
import com.block.vtCoin.main.fragment.v.PersonInfoViewImpl;
import com.block.vtCoin.mine.bill.BillActivity;
import com.block.vtCoin.mine.buy_coin.TurnInActivity;
import com.block.vtCoin.mine.buy_manager.BuyManagerActivity;
import com.block.vtCoin.mine.deal_recode.DealRecodeActivity;
import com.block.vtCoin.mine.issue_buy.IssueBuyActivity1;
import com.block.vtCoin.mine.my_attention.MyAttentionActivity;
import com.block.vtCoin.mine.sale_coin.TurnOutActivity;
import com.block.vtCoin.mine.sale_manager.SaleManagerActivity;
import com.block.vtCoin.mine.setting.setting.MineSettingActivity;
import com.block.vtCoin.mine.tobe_trader.TobeTraderActivity;
import com.block.vtCoin.mine.user.UserManagerActivity;
import com.block.vtCoin.mine.wallet.WalletActivity;
import com.block.vtCoin.request.ApiAction;
import com.block.vtCoin.request.MyModel;
import com.block.vtCoin.util.AppManager;
import com.block.vtCoin.util.StringUtils;
import com.block.vtCoin.widget.imageview.CircleImageView;
import com.bumptech.glide.Glide;
import com.lsxiao.apllo.annotations.Receive;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * @Description 区块
 * @Author lijie
 * @Date 2017/7/12.
 */
public class MineFragment extends LazyLoadFragment {
    @Bind(R.id.avatar)
    CircleImageView avatar;
    @Bind(R.id.name)
    TextView name;
    @Bind(R.id.id)
    TextView id;
    @Bind(R.id.tobe_trader)
    LinearLayout tobeTrader;
    private View dealerView, notDealerView;
    private MainActivity activity;
    private IsDealerPresenter isDealerPresenter;
    private PersonInfoPresenter personInfoPresenter;
    private HandleOrderPresenter handleOrderPresenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPresenter();
    }

    private void initPresenter() {
        isDealerPresenter = new IsDealerPresenter();
        isDealerPresenter.setModelView(new MyModel<IsDealerEntity>(), new IsDealerViewImpl(this, ApiAction.Preference_IsDealer));
        personInfoPresenter = new PersonInfoPresenter();
        personInfoPresenter.setModelView(new MyModel<PersonInfoEntity>(), new PersonInfoViewImpl(this, ApiAction.Account_GetPersonal_Info));
        handleOrderPresenter = new HandleOrderPresenter();
        handleOrderPresenter.setModelView(new MyModel<HandleOrderEntity>(), new HandlerOrderViewImpl(this, ApiAction.Order_UnhandOrderNumber));
    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView() {
        dealerView = getView().findViewById(R.id.dealer_layout);
        notDealerView = getView().findViewById(R.id.not_dealer_layout);
        isDealerPresenter.isDealer();
        personInfoPresenter.getUserInfo();
        handleOrderPresenter.handleOrder();
    }

    @Override
    protected void onWakeOrCurrent() {
        super.onWakeOrCurrent();
    }


    @OnClick({R.id.setting, R.id.avatar, R.id.issue_buy, R.id.issue_sale, R.id.trade_recode, R.id.buy_manager, R.id.sale_manager, R.id.wallet, R.id.buy_currency, R.id.sale_currency, R.id.bill, R.id.my_focus, R.id.share, R.id.tobe_trader,
            R.id.buy_currency0, R.id.sale_currency0, R.id.bill0, R.id.trade_recode0, R.id.wallet0, R.id.my_focus0, R.id.share0})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.setting:
                startActivity(new Intent(getContext(), MineSettingActivity.class));
                break;
            case R.id.avatar:
                startActivity(new Intent(getContext(), UserManagerActivity.class));
                break;
            case R.id.issue_buy:
                startActivity(new Intent(getContext(), IssueBuyActivity1.class));
                break;
            case R.id.issue_sale:
                startActivity(new Intent(getContext(), IssueBuyActivity1.class));
                break;
            case R.id.trade_recode0:
            case R.id.trade_recode:
                startActivity(new Intent(getContext(), DealRecodeActivity.class));
                break;
            case R.id.buy_manager:
                startActivity(new Intent(getContext(), BuyManagerActivity.class));
                break;
            case R.id.sale_manager:
                startActivity(new Intent(getContext(), SaleManagerActivity.class));
                break;
            case R.id.wallet0:
            case R.id.wallet:
                startActivity(new Intent(getContext(), WalletActivity.class));
                break;
            case R.id.buy_currency0:
            case R.id.buy_currency:
                startActivity(new Intent(getContext(), TurnInActivity.class));
                break;
            case R.id.sale_currency0:
            case R.id.sale_currency:
                startActivity(new Intent(getContext(), TurnOutActivity.class));
                break;
            case R.id.bill0:
            case R.id.bill:
                startActivity(new Intent(getContext(), BillActivity.class));
                break;
            case R.id.my_focus0:
            case R.id.my_focus:
                startActivity(new Intent(getContext(), MyAttentionActivity.class));
                break;
            case R.id.share0:
            case R.id.share:
                break;
            case R.id.tobe_trader:
                activity.toActivity(TobeTraderActivity.class);
                break;
        }
    }

    public void setDealer(IsDealerEntity entity) {
        if (entity != null) {
            if (entity.isData()) {//是交易商
                dealerView.setVisibility(View.VISIBLE);
                notDealerView.setVisibility(View.GONE);
                tobeTrader.setVisibility(View.GONE);
            } else {
                notDealerView.setVisibility(View.VISIBLE);
                tobeTrader.setVisibility(View.VISIBLE);
                dealerView.setVisibility(View.GONE);
            }
        }
    }

    public void setPersonInfo(PersonInfoEntity entity) {
        if (entity != null) {
            PersonInfoEntity.DataBean data = entity.getData();
            AppManager.setPersonInfo(data);//保存在appManager中
            if (data != null) {
                String imagePath = StringUtils.checkImagePath(data.getLogo());
                if (!TextUtils.isEmpty(imagePath))
                    Glide.with(this).load(imagePath).error(R.mipmap.avatar).into(avatar);
                if (!TextUtils.isEmpty(data.getNickName()))
                    name.setText(data.getNickName());
                if (!TextUtils.isEmpty(data.getUserName()))
                    id.setText(data.getUserName());
            }
        }
    }

    public void setHandleOrder(HandleOrderEntity entity) {
        //不知道要做什么操作
    }

    @Receive(tag = ApolloAction.Update_NickName)
    public void updateNick() {
        L.i("更新");
        name.setText(AppManager.getPersonInfo().getNickName());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        personInfoPresenter.unRegisterRx();
        handleOrderPresenter.unRegisterRx();
        isDealerPresenter.unRegisterRx();
        super.onDestroy();
    }
}
