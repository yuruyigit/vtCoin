package com.block.vtCoin.mine.my_attention;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.vtCoin.R;
import com.block.vtCoin.main.BaseActivity;
import com.block.vtCoin.widget.title.NormalTitleBar;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class MyHomeActivity extends BaseActivity {

    @Bind(R.id.tv_consult_now)
    TextView tvConsultNow;
    @Bind(R.id.tv_attention)
    TextView tvAttention;
    private boolean isAttention=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_home);
        setUltimateBar(R.color.blue1);
        initView();
    }

    private void initView() {
    }

    @Override
    public View getTitleBar() {
        NormalTitleBar normalTitleBar = getNormalTitleBar().setTitle(getString(R.string.my_home));
        return normalTitleBar;
    }

    @Override
    protected List<BasePresenter> getPresenter(List<BasePresenter> presenters) {
        return null;
    }

    @OnClick({R.id.tv_consult_now, R.id.tv_attention})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_consult_now:
                //跳到聊天界面
                break;
            case R.id.tv_attention:
                if(isAttention){
                    isAttention=false;
                    tvAttention.setText(getString(R.string.un_follow));
                }else {
                    isAttention = true;
                    tvAttention.setText("+"+getString(R.string.attention));
                }
                break;
        }
    }
}
