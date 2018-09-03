package com.block.vtCoin.mine.deal_recode.buy_deal_recode.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.vtCoin.R;
import com.block.vtCoin.main.BaseActivity;
import com.block.vtCoin.widget.starView.RatingBarView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @Description
 * @Author lijie
 * @Date 2017/8/25.
 */
public class EvaluatedetailActivity extends BaseActivity {

    @Bind(R.id.rbBar)
    RatingBarView rbBar;
    @Bind(R.id.tv_content)
    TextView tvContent;
    @Bind(R.id.no_data)
    TextView noData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluate_detail);
        ButterKnife.bind(this);
        setUltimateBar(R.color.blue1);
        initview();
    }

    private void initview() {
        rbBar.setClickable(true);//设置可否点击
        rbBar.setStar(4f);//设置显示的星星个数
    }

    @Override
    protected List<BasePresenter> getPresenter(List<BasePresenter> presenters) {
        return null;
    }

    @Override
    public View getTitleBar() {
        return getNormalTitleBar().setTitle(getString(R.string.evaluate_detail));
    }
}
