package com.block.vtCoin.mine.setting.second_login_verify;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.vtCoin.R;
import com.block.vtCoin.main.BaseActivity;
import com.block.vtCoin.widget.dialog.ListViewDialog;
import com.block.vtCoin.widget.base.OnMyItemClickListener;
import com.zhy.android.percent.support.PercentRelativeLayout;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SecondLoginVerifyActivity extends BaseActivity {

    @Bind(R.id.tv_state)
    TextView tvState;
    @Bind(R.id.tv_type)
    TextView tvType;
    @Bind(R.id.rl_type)
    PercentRelativeLayout rlType;
    private boolean isOpen = false;
    private ListViewDialog verifyWayDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_login_vertify);
        ButterKnife.bind(this);
        setUltimateBar(R.color.blue1);
        setBackground(R.color.gray7);
        initView();
    }

    private void initView() {

    }

    /**
     * @return
     */
    @Override
    public View getTitleBar() {
        return getNormalTitleBar().setTitle(getString(R.string.second_login_verify));
    }

    @Override
    protected List<BasePresenter> getPresenter(List<BasePresenter> presenters) {
        return null;
    }

    @Override
    protected void onDestroy() {
        if (null != verifyWayDialog)
            verifyWayDialog.unbind();
        super.onDestroy();
    }

    @OnClick({R.id.tv_state, R.id.rl_type})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_state:
                if (isOpen) {
                    tvState.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.release_slide, 0);
                    isOpen = false;
                } else {
                    tvState.setCompoundDrawablesWithIntrinsicBounds(0,0,R.mipmap.release_slide_click, 0);
                    isOpen = true;
                }
                break;
            case R.id.rl_type:
                showDialog();
                break;
        }
    }

    private void showDialog() {
        final String[] items = new String[]{getString(R.string.verify_way), getString(R.string.msm), getString(R.string.google)};
        if (verifyWayDialog == null) {
            verifyWayDialog = new ListViewDialog(this, items,0);
            verifyWayDialog.setOnItemClickListener(new OnMyItemClickListener() {
                @Override
                public void onItemClick(View parent, View view, int position) {
                    if (position == 0) {
                        return;
                    } else
                        tvType.setText(items[position]);
                }
            });
        }
        verifyWayDialog.show();
    }
}
