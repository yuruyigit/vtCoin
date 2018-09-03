package com.block.vtCoin.mine.tobe_trader;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.vtCoin.R;
import com.block.vtCoin.entity.HandleOrderEntity;
import com.block.vtCoin.main.BaseActivity;
import com.block.vtCoin.mine.tobe_trader.p.QueryDealerPresenter;
import com.block.vtCoin.mine.tobe_trader.v.QueryDealerViewImpl;
import com.block.vtCoin.request.ApiAction;
import com.block.vtCoin.request.MyModel;
import com.block.vtCoin.widget.title.NormalTitleBar;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class TobeTraderActivity extends BaseActivity {
    @Bind(R.id.apply_now)
    TextView applyNow;
    @Bind(R.id.agree)
    TextView agree;
    @Bind(R.id.terms)
    TextView terms;
    private boolean isAgree = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tobe_trader);
        setUltimateBar(R.color.blue1);
    }

    @Override
    public View getTitleBar() {
        NormalTitleBar normalTitleBar = new NormalTitleBar(this).setTitle(getString(R.string.apply_tobe_trader));
        return normalTitleBar;
    }

    @Override
    protected List<BasePresenter> getPresenter(List<BasePresenter> presenters) {
        return null;
    }

    @OnClick({R.id.apply_now, R.id.agree, R.id.terms})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.apply_now:
                toActivity(TobeTraderActivity1.class);
                break;
            case R.id.agree:
                if (isAgree) {
                    isAgree = false;
                    setLeftDrawable(agree, R.mipmap.circle);
                } else {
                    isAgree = true;
                    setLeftDrawable(agree, R.mipmap.circle_click);
                }
                break;
            case R.id.terms:

                break;
        }
    }


}
