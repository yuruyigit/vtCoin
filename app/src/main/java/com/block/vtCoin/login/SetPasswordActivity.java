package com.block.vtCoin.login;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.vtCoin.R;
import com.block.vtCoin.entity.RegisterEntity;
import com.block.vtCoin.entity.SmsEntity;
import com.block.vtCoin.login.p.RegisterPresenter;
import com.block.vtCoin.login.p.ResetLoginPasPresenter;
import com.block.vtCoin.login.v.RegisterViewImpl;
import com.block.vtCoin.login.v.ResetLoginPasViewImpl;
import com.block.vtCoin.main.BaseActivity;
import com.block.vtCoin.request.ApiAction;
import com.block.vtCoin.request.MyModel;
import com.block.vtCoin.widget.dialog.NormalDialog;
import com.block.vtCoin.widget.editText.ClearEditText;
import com.block.vtCoin.widget.editText.MyTextWatcher;
import com.block.vtCoin.widget.title.NormalTitleBar;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

public class SetPasswordActivity extends BaseActivity {
    @Bind(R.id.visible1)
    ImageView visible1;
    @Bind(R.id.visible2)
    ImageView visible2;
    @Bind(R.id.tv_commit)
    TextView tvCommit;
    @Bind(R.id.et_password1)
    ClearEditText etPassword1;
    @Bind(R.id.et_password2)
    ClearEditText etPassword2;
    private int from;
    private RegisterPresenter registerPresenter;
    private ResetLoginPasPresenter resetLoginPasPresenter;
    private String title = "", commit = "", password1, password2;
    private boolean isShow1 = false, isShow2 = false;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handIntent();
        setContentView(R.layout.activity_set_password);
        setUltimateBar(R.color.white0);
        initView();
    }

    private void handIntent() {
        from = getIntent().getIntExtra("from", -1);
        if (from == 0) {/*来自手机号注册*/
            title = getString(R.string.set_password);
            commit = getString(R.string.register);
        } else if (from == 1) {/*来自邮箱注册*/
            email = getIntent().getStringExtra("email");
            title = getString(R.string.set_password);
            commit = getString(R.string.register);
        } else if (from == 2) {/*忘记密码后，来自手机号重置密码*/
            title = getString(R.string.reset_password);
            commit = getString(R.string.reset_sure);
        } else if (from == 3) {/*忘记密码后，来自email重置密码*/
            email = getIntent().getStringExtra("email");
            title = getString(R.string.reset_password);
            commit = getString(R.string.reset_sure);
        }
    }

    private void initView() {
        tvCommit.setText(commit);
        etPassword1.addTextChangedListener(new MyTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().isEmpty()) {
                    etPassword2.setText("");
                    setClickable(tvCommit, false);
                }
            }
        });
        etPassword2.addTextChangedListener(new MyTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().isEmpty())
                    setClickable(tvCommit, false);
                else if (s.toString().length() == etPassword1.getText().toString().length())
                    setClickable(tvCommit, true);
                else
                    setClickable(tvCommit, false);
            }
        });
    }

    /**
     * @return
     */
    @Override
    public View getTitleBar() {
        NormalTitleBar normalTitleBar = getNormalTitleBar().setTitle(title).setBackground(R.color.white0);
        normalTitleBar.setLeftDrawable(R.mipmap.return_01).setTitleTextColor(R.color.black1);
        return normalTitleBar;
    }

    @Override
    protected List<BasePresenter> getPresenter(List<BasePresenter> presenters) {
        registerPresenter = new RegisterPresenter();
        registerPresenter.setModelView(new MyModel<RegisterEntity>(), new RegisterViewImpl(this, ApiAction.Account_Register, 0));
        resetLoginPasPresenter = new ResetLoginPasPresenter();
        resetLoginPasPresenter.setModelView(new MyModel<SmsEntity>(), new ResetLoginPasViewImpl(this, ApiAction.Account_ResetLoginPas));
        presenters.add(registerPresenter);
        presenters.add(resetLoginPasPresenter);
        return presenters;
    }

    public boolean isRegister() {
        password1 = etPassword1.getText().toString().trim();
        password2 = etPassword2.getText().toString().trim();
        if (password1.equals(password2)) {
            return true;
        } else {
            showTip(getString(R.string.password_not_equals));
            return false;
        }
    }

    @OnClick({R.id.visible1, R.id.visible2, R.id.tv_commit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.visible1:
                isPasswordVis(etPassword1, visible1, isShow1, 1);
                break;
            case R.id.visible2:
                isPasswordVis(etPassword2, visible2, isShow2, 2);
                break;
            case R.id.tv_commit:
                if (isRegister()) {
                    if (from == 0) {/*来自手机号注册,设置密码*/
                        registerPresenter.register();
                        return;
                    } else if (from == 1) {/*来自邮箱注册，设置密码*/
                        showDialog();
                        return;
                    } else if (from == 2) {/*2.手机号重置密码*/
                        resetLoginPasPresenter.resetLoginPas();
                        return;
                    } else if (from == 3) {/*3.email重置密码*/
                        showDialog();
                        return;
                    }
                }
                break;
        }
    }

    public void showDialog() {
        NormalDialog dialog = new NormalDialog(this, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (from == 1) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("form", 1);
                    toActivity(SuccessActivity.class, map);
                } else if (from == 3) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("form", 3);
                    toActivity(SuccessActivity.class, map);
                }
            }
        });
        dialog.setMsg(getString(R.string.send_link_to_email) + email);
        dialog.show();
    }

    /**
     * @param isShow 是否显示密码
     */
    public void isPasswordVis(EditText et, ImageView visImage, Boolean isShow, int type) {
        if (isShow) {//之前是显示
            et.setTransformationMethod(PasswordTransformationMethod.getInstance());
            et.setSelection(et.getText().length());
            if (type == 1)
                isShow1 = false;
            else
                isShow2 = false;
            visImage.setImageResource(R.mipmap.visible);
        } else {
            et.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            et.setSelection(et.getText().length());
            if (type == 1)
                isShow1 = true;
            else
                isShow2 = true;
            visImage.setImageResource(R.mipmap.sword);
        }
    }

    /*手机号注册*/
    public Map<String, Object> getRegisterMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("EmailOrPhoneNumber", getIntent().getStringExtra("phone"));
        map.put("Password", password1);
        map.put("ConfirmPassword", password2);
        map.put("RegCode", getIntent().getStringExtra("code"));
        return map;
    }

    public void setRegister(boolean isSuccess) {
        if (isSuccess || !isSuccess) {
            Map<String, Object> map = new HashMap<>();
            map.put("from", 0);
            toActivity(SuccessActivity.class, map);
        }
    }

    /*重置登录密码*/
    public Map<String, Object> getResetPasMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("EmailOrPhoneNumber", getIntent().getStringExtra("phone"));
        map.put("Password", password1);
        map.put("ConfirmPassword", password2);
        map.put("RegCode", getIntent().getStringExtra("code"));
        return map;
    }

    public void setResetPas(boolean isSuccess) {
        Map<String, Object> map = new HashMap<>();
        map.put("from", 2);
        toActivity(SuccessActivity.class, map);
    }
}
