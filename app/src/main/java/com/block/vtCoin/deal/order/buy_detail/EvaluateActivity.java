package com.block.vtCoin.deal.order.buy_detail;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.vtCoin.R;
import com.block.vtCoin.main.BaseActivity;
import com.block.vtCoin.widget.starView.RatingBarView;

import java.util.List;

import butterknife.Bind;

/**
 * @Description
 * @Author lijie
 * @Date 2017/8/25.
 */
public class EvaluateActivity extends BaseActivity {

    @Bind(R.id.other_question)
    EditText otherQuestion;
    @Bind(R.id.evaluate_provide)
    TextView evaluateProvide;
    @Bind(R.id.rbBar)
    RatingBarView rbBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluate);
        setUltimateBar(R.color.blue1);
        initview();
    }

    @Override
    protected List<BasePresenter> getPresenter(List<BasePresenter> presenters) {
        return null;
    }

    private void initview() {
        rbBar.setClickable(true);//设置可否点击
        rbBar.setStar(2f);//设置显示的星星个数

        rbBar.setOnRatingChangeListener(new RatingBarView.OnRatingChangeListener() {
            @Override
            public void onRatingChange(float ratingCount) {
                showTip("评价了" + ratingCount + "星");
            }
        });


    }

    @Override
    public View getTitleBar() {
        return getNormalTitleBar().setTitle(getString(R.string.evaluate));
    }
}
