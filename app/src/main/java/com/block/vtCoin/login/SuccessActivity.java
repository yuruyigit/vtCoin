package com.block.vtCoin.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.net.util.L;
import com.block.vtCoin.R;
import com.block.vtCoin.main.BaseActivity;
import com.block.vtCoin.main.MainActivity;
import com.block.vtCoin.util.CountDownUtil;
import com.block.vtCoin.widget.title.NormalTitleBar;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SuccessActivity extends BaseActivity {
    @Bind(R.id.tv_content)
    TextView tvContent;
    @Bind(R.id.commit1)
    TextView commit1;
    @Bind(R.id.commit0)
    TextView commit0;
    @Bind(R.id.commit2)
    TextView commit2;
    private int from;
    private String title = "", content = "", commit = "";
    private CountDownUtil countDownUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handIntent();
        setContentView(R.layout.activity_success);
        ButterKnife.bind(this);
        setUltimateBar(R.color.white0);
        initView();
    }

    private void handIntent() {
        from = getIntent().getIntExtra("from", -1);
        L.i("from="+from);
        if (from == 0) {/*手机号注册成功*/
            title = getString(R.string.tip_register_success);
            content = getString(R.string.five_second_skip);
            commit = getString(R.string.login_right_now);
        } else if (from == 1) {/*邮箱注册成功*/
            title = getString(R.string.tip_register_success);
            content = getString(R.string.email_send_success);
            commit = getString(R.string.to_login);
        } else if (from == 2) {/*手机号重置密码成功*/
            title = getString(R.string.reset_success);
            content = getString(R.string.find_password_success);
            commit = getString(R.string.login_right_now);
        } else if (from == 3) {/*邮箱重置密码*/
            title = getString(R.string.reset_success);
            content = getString(R.string.email_reset_success);
            commit = getString(R.string.to_login);
        }else if(from==4){/*重置密码成功*/
            title=getString(R.string.reset_success);
            content="重置密码成功，5秒后跳转到首页";
            commit = getString(R.string.login_right_now);
        }
    }

    private void initView() {
        tvContent.setText(content);
        if (from == 0) { /*从手机号注册设置密码界面跳过来*/
            commit0.setVisibility(View.VISIBLE);
            commit0.setText(commit);
            commit1.setVisibility(View.GONE);
            commit2.setVisibility(View.GONE);
            countDownUtil = new CountDownUtil(this, tvContent, 5000, 1000, "", new CountDownUtil.onFinishListener() {
                @Override
                public void finish() {
                    toActivity(MainActivity.class);
                }
            });
            countDownUtil.start();
        } else if (from == 1) {/*从邮箱注册设置密码界面跳过来*/
            commit0.setVisibility(View.GONE);
            commit1.setVisibility(View.VISIBLE);
            commit1.setText(commit);
            commit2.setVisibility(View.VISIBLE);
        } else if (from == 2) {/*手机号重置密码成功*/
            commit0.setVisibility(View.VISIBLE);
            commit0.setText(commit);
            commit1.setVisibility(View.GONE);
            commit2.setVisibility(View.GONE);
            countDownUtil = new CountDownUtil(this, tvContent, 5000, 1000, "", new CountDownUtil.onFinishListener() {
                @Override
                public void finish() {
                    toActivity(MainActivity.class);
                }
            });
            countDownUtil.start();
        } else if (from == 3) {/*email重置密码成功*/
            commit0.setVisibility(View.GONE);
            commit2.setVisibility(View.VISIBLE);
            commit1.setVisibility(View.VISIBLE);
            commit1.setText(commit);
        }else if(from==4){
            commit0.setVisibility(View.VISIBLE);
            commit0.setText(commit);
            commit1.setVisibility(View.GONE);
            commit2.setVisibility(View.GONE);
            countDownUtil = new CountDownUtil(this, tvContent, 5000, 1000, "", new CountDownUtil.onFinishListener() {
                @Override
                public void finish() {
                    toActivity(MainActivity.class);
                }
            });
            countDownUtil.start();
        }
    }

    @Override
    public View getTitleBar() {
        NormalTitleBar normalTitleBar = getNormalTitleBar().setTitle(title).setBackground(R.color.white0);
        normalTitleBar.setLeftDrawable(R.mipmap.return_01).setTitleTextColor(R.color.black1);
        return normalTitleBar;
    }
    @Override
    protected List<BasePresenter> getPresenter(List<BasePresenter> presenters) {
        return null;
    }

    @OnClick({R.id.commit1, R.id.commit0, R.id.commit2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.commit0:/*0,2*/
                startActivity(new Intent(this, LoginActivity.class).putExtra("from", 1));
                finish();
                break;
            case R.id.commit1:/*1,3*/
            if(from==1){
                startActivity(new Intent(this, SuccessActivity.class).putExtra("from", 0));
            }else if(from==3){
                startActivity(new Intent(this, SuccessActivity.class).putExtra("from", 4));
            }
                finish();
                break;
            case R.id.commit2:
                /*Todo,登陆邮箱*/
                break;
        }
    }

    @Override
    protected void onDestroy() {
        if (null != countDownUtil)
            countDownUtil.cancel();
        super.onDestroy();
    }

    @OnClick(R.id.commit2)
    public void onClick() {
    }
}
