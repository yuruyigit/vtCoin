package com.block.vtCoin.mine.setting.modify_password;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.vtCoin.R;
import com.block.vtCoin.main.BaseActivity;
import com.block.vtCoin.util.AppManager;

import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * @Description
 * @Author lijie
 * @Date 2017/7/15.
 */
public class FindBackPasswordActivity extends BaseActivity {
    @Bind(R.id.tv_phone_bind_state)
    TextView tvPhoneBindState;
    @Bind(R.id.tv_email_bind_state)
    TextView tvEmailBindState;
    private int from = 0;
    private HashMap<String, Object> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handIntent();
        setContentView(R.layout.activity_find_back_password);
        setUltimateBar(R.color.blue1);
        intView();
    }

    private void handIntent() {
        /*0--找回登录密码，1--找回交易密码*/
        from = getIntent().getIntExtra("from", 0);
        map = new HashMap<>();
        map.put("from", from);
    }

    private void intView() {
        if (AppManager.isBindPhone())
            tvPhoneBindState.setText(getString(R.string.have_bind));
        else
            tvPhoneBindState.setText(getString(R.string.unbind));
        if (AppManager.isBindEmail())
            tvEmailBindState.setText(getString(R.string.have_bind));
        else
            tvEmailBindState.setText(getString(R.string.unbind));
    }


    @Override
    public View getTitleBar() {
        return getNormalTitleBar().setTitle(getString(R.string.find_back_password));
    }

    @Override
    protected List<BasePresenter> getPresenter(List<BasePresenter> presenters) {
        return null;
    }

    @OnClick({R.id.rl_phone_find, R.id.rl_email_find})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_phone_find:
                if (AppManager.isBindPhone()) {
                    toActivity(PhoneFindBackPasswordActivity.class, map);
                } else
                    showTip(getString(R.string.tip_have_not_bind_phone));
                break;
            case R.id.rl_email_find:
                if (AppManager.isBindEmail())
                    toActivity(EmailFindBackPasswordActivity.class, map);
                else
                    showTip(getString(R.string.tip_have_not_bind_email));
                break;
        }
    }
}
