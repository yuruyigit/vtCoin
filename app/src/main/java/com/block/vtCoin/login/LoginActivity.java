package com.block.vtCoin.login;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.net.util.L;
import com.block.vtCoin.R;
import com.block.vtCoin.constant.Constant;
import com.block.vtCoin.entity.LoginEntity;
import com.block.vtCoin.entity.TokenEntity;
import com.block.vtCoin.login.p.LoginPresenter;
import com.block.vtCoin.login.p.LoginPresenter1;
import com.block.vtCoin.login.p.TokenPresenter;
import com.block.vtCoin.login.v.LoginViewImpl;
import com.block.vtCoin.login.v.TokenViewImpl;
import com.block.vtCoin.main.BaseActivity;
import com.block.vtCoin.main.MainActivity;
import com.block.vtCoin.net.BaseSubscriber;
import com.block.vtCoin.net.ServiceFactory;
import com.block.vtCoin.net.api.AccountApi;
import com.block.vtCoin.request.MyModel;
import com.block.vtCoin.util.AppUtil;
import com.block.vtCoin.util.MD5;
import com.block.vtCoin.widget.editText.ClearEditText;
import com.block.vtCoin.widget.editText.MyTextWatcher;
import com.block.vtCoin.widget.title.NormalTitleBar;
import com.block.vtCoin.yun.receive.SDKCoreHelper;
import com.google.gson.Gson;
import com.lsxiao.apllo.Apollo;
import com.lsxiao.apllo.annotations.Receive;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginActivity extends BaseActivity {
    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.et_account)
    ClearEditText etAccount;
    @Bind(R.id.et_password)
    ClearEditText etPassword;
    @Bind(R.id.visible)
    ImageView visible;
    @Bind(R.id.forgetPassword)
    TextView forgetPassword;
    @Bind(R.id.login)
    TextView login;
    @Bind(R.id.register1)
    TextView register1;
    @Bind(R.id.register0)
    TextView register0;
    private boolean isShowPass = false;
    private String account, password;
    private LoginPresenter loginPresenter;
    private TokenPresenter tokenPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setUltimateBar(R.color.white0);
        initView();
    }

    @Override
    protected List<BasePresenter> getPresenter(List<BasePresenter> presenters) {
        loginPresenter = new LoginPresenter();
        loginPresenter.setModelView(new MyModel<LoginEntity>(), new LoginViewImpl(this));
        presenters.add(loginPresenter);

        tokenPresenter=new TokenPresenter();
        tokenPresenter.setModelView(new MyModel<TokenEntity>(),new TokenViewImpl(this));
        presenters.add(tokenPresenter);
        return presenters;
    }

    private void initView() {
        int from = getIntent().getIntExtra("from", -1);
        if (from == 1) {/*从注册成功界面跳转而来*/
            register1.setVisibility(View.VISIBLE);
            register0.setVisibility(View.GONE);
        } else {
            register1.setVisibility(View.GONE);
            register0.setVisibility(View.VISIBLE);
            etAccount.setText("15007253417");
            etPassword.setText("ba1234588");
//            etAccount.setText("13222902192");
//            etPassword.setText("ba123456");
        }
        etAccount.addTextChangedListener(new MyTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().isEmpty()) {
                    etPassword.setText("");
                }
            }
        });
    }

    /**
     * @return
     */
    @Override
    public View getTitleBar() {
        NormalTitleBar normalTitleBar = getNormalTitleBar().setTitle(getString(R.string.login_or_register)).setBackground(R.color.white0);
        normalTitleBar.setLeftDrawable(0).setTitleTextColor(R.color.black1);
        return normalTitleBar;
    }

    @OnClick({R.id.visible, R.id.forgetPassword, R.id.login, R.id.register1, R.id.register0})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.visible:
                isPasswordVis(isShowPass);
                break;
            case R.id.forgetPassword:
                toActivity(FindPasswordActivity.class);
                break;
            case R.id.login:
                if (isLogin() && isHasNet()) {
                    loginPresenter.login();
//                    login();
                }
                break;
            case R.id.register0:
            case R.id.register1:
                toActivity(PhoneRegisterActivity.class);
                break;
        }
    }

    public void login() {
        loadDialog();
        Subscription subscribe = ServiceFactory.getInstance()
                .createBaseService(AccountApi.class)
                .login(account, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<com.block.vtCoin.entity1.LoginEntity>() {
                    @Override
                    public void onSuccess(com.block.vtCoin.entity1.LoginEntity loginEntity) {
                        dismissDialog();
                        showTip(getString(R.string.tip_login_success));
                        toActivity(MainActivity.class);
                    }

                    @Override
                    public void onFail(String msg) {
                        dismissDialog();
                        showTip(msg);
                    }
                });
    }

    public boolean isLogin() {
        account = etAccount.getText().toString().trim();
        password = etPassword.getText().toString().trim();
        password = MD5.encrypt(password, true);
        if (account.isEmpty()) {
            showTip(getString(R.string.account_empty));
            return false;
        } else if (password.isEmpty()) {
            showTip(getString(R.string.password_empty));
            return false;
        }
        return true;
    }

    /**
     * @param isShow 是否显示密码
     */
    public void isPasswordVis(Boolean isShow) {
        if (isShow) {//之前是显示
            etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            etPassword.setSelection(etPassword.getText().length());
            isShowPass = false;
            visible.setImageResource(R.mipmap.visible);
        } else {
            etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            etPassword.setSelection(etPassword.getText().length());
            isShowPass = true;
            visible.setImageResource(R.mipmap.sword);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            // 获得当前得到焦点的View，一般情况下就是EditText（特殊情况就是轨迹求或者实体案件会移动焦点）
            View v = getCurrentFocus();
            if (AppUtil.isShouldHideInput(v, ev)) {
                AppUtil.hideKeyboard(v);
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    public Map getLoginMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("EmailOrPhoneNumber", etAccount.getText().toString().trim());
        password = etPassword.getText().toString().trim();
        password = MD5.encrypt(password, true);
        map.put("Password",password);
        return map;
    }

    public void setLogin(LoginEntity entity) {
        if (entity != null) {
            getChatToken();
            toActivity(MainActivity.class);
        }
    }

    private void getChatToken() {
        tokenPresenter.getToken();
    }

    public void setToken(TokenEntity entity){
        if(entity!=null){
            String chatToken = entity.getData().getDecChatToken();
            L.i("chatToken="+chatToken);
            SDKCoreHelper.init(chatToken);
        }
    }
}
