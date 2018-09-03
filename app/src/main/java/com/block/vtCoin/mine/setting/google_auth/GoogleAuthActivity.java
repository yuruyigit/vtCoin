package com.block.vtCoin.mine.setting.google_auth;

import android.os.Bundle;
import android.view.View;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.vtCoin.R;
import com.block.vtCoin.main.BaseActivity;
import com.block.vtCoin.widget.dialog.password.PayPasswordDialog;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class GoogleAuthActivity extends BaseActivity {
    private PayPasswordDialog psDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_auth);
        ButterKnife.bind(this);
        setUltimateBar(R.color.blue1);
        initView();
    }

    private void initView() {
    }

    @Override
    public View getTitleBar() {
        return getNormalTitleBar().setTitle(getString(R.string.google_attest));
    }

    @Override
    protected List<BasePresenter> getPresenter(List<BasePresenter> presenters) {
        return null;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    private void showPsDialog() {
        if (psDialog == null) {
            psDialog = new PayPasswordDialog(this);
            psDialog.setOnPasswordChangedListener(new PayPasswordDialog.OnPasswordChangedListener() {
                @Override
                public void onTextChanged(String psw) {
                }
                @Override
                public void onInputFinish(String psw) {
                    toActivity(GoogleAuthActivity1.class);
                }
            });
        }
        psDialog.show();
    }
    @OnClick({R.id.google_down, R.id.next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.google_down:
                break;
            case R.id.next:
                showPsDialog();
                break;
        }
    }
}
