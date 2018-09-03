package com.block.vtCoin.mine.tobe_trader;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.vtCoin.R;
import com.block.vtCoin.entity.HandleOrderEntity;
import com.block.vtCoin.entity.QueryDealerEntity;
import com.block.vtCoin.main.BaseActivity;
import com.block.vtCoin.mine.setting.phone_num.BindPhoneActivity;
import com.block.vtCoin.mine.tobe_trader.p.QueryDealerPresenter;
import com.block.vtCoin.mine.tobe_trader.v.QueryDealerViewImpl;
import com.block.vtCoin.request.ApiAction;
import com.block.vtCoin.request.MyModel;
import com.block.vtCoin.widget.title.NormalTitleBar;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TobeTraderActivity1 extends BaseActivity {
    @Bind(R.id.tv_phone_state)
    TextView tvPhoneState;
    @Bind(R.id.tv_auth_state)
    TextView tvAuthState;
    @Bind(R.id.tv_assets_state)
    TextView tvAssetsState;
    @Bind(R.id.tv_pay_state)
    TextView tvPayState;
    @Bind(R.id.tv_area_state)
    TextView tvAreaState;
    @Bind(R.id.sure_apply)
    TextView sureApply;
    private QueryDealerPresenter queryDealerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tobe_trader1);
        ButterKnife.bind(this);
        setUltimateBar(R.color.blue1);
        initData();
    }

    private void initData() {
        queryDealerPresenter.queryDealer();
    }

    @Override
    public View getTitleBar() {
        NormalTitleBar normalTitleBar = new NormalTitleBar(this).setTitle(getString(R.string.apply_tobe_trader));
        return normalTitleBar;
    }

    @Override
    protected List<BasePresenter> getPresenter(List<BasePresenter> presenters) {
        queryDealerPresenter = new QueryDealerPresenter();
        queryDealerPresenter.setModelView(new MyModel<QueryDealerEntity>(), new QueryDealerViewImpl(this, ApiAction.Preference_QueryDealerQualification));
        return presenters;
    }

    @OnClick({R.id.rl_phone_verify, R.id.rl_auth_verify, R.id.rl_defined_assets, R.id.rl_pay_info_verify, R.id.rl_area_verify, R.id.sure_apply})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_phone_verify:
                if (isPhone) {
                    showTip("已认证");
                } else
                    toActivity(BindPhoneActivity.class);
                break;
            case R.id.rl_auth_verify:
                break;
            case R.id.rl_defined_assets:
                break;
            case R.id.rl_pay_info_verify:
                break;
            case R.id.rl_area_verify:
                break;
            case R.id.sure_apply:
                break;
        }
    }

    public boolean isPhone, isAuth, isAssets, isPay, isArea;

    public void setQueryDealer(QueryDealerEntity entity) {
        if (entity != null) {
            QueryDealerEntity.DataBean data = entity.getData();
            if (data != null) {
                isPhone = data.isPhoneQLN();
                isAuth = data.isIdentityQLN();
                isAssets = data.isCapitalQLN();
                isPay = data.isPaymentQLN();
                isArea = data.isCommicationQLN();
                if (isPhone)
                    tvPhoneState.setText(getString(R.string.have_certified));
                else
                    tvPhoneState.setText(getString(R.string.not_certified));
                if (isAuth)
                    tvAuthState.setText(getString(R.string.have_certified));
                else
                    tvAuthState.setText(getString(R.string.not_certified));
                if (isAssets)
                    tvAssetsState.setText(getString(R.string.have_certified));
                else
                    tvAssetsState.setText(getString(R.string.not_certified));
                if (isPay)
                    tvPayState.setText(getString(R.string.have_certified));
                else
                    tvPayState.setText(getString(R.string.not_certified));
                if (isArea)
                    tvAreaState.setText(getString(R.string.have_certified));
                else
                    tvAreaState.setText(getString(R.string.not_certified));
            }
        }

    }
}
