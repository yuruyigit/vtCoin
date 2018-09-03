package com.block.vtCoin.mine.setting.modify_password;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.vtCoin.R;
import com.block.vtCoin.entity.SmsEntity;
import com.block.vtCoin.login.LoginActivity;
import com.block.vtCoin.main.BaseActivity;
import com.block.vtCoin.mine.setting.modify_password.p.ModifyDealPasPresenter;
import com.block.vtCoin.mine.setting.modify_password.p.ModifyLoginPasPresenter;
import com.block.vtCoin.mine.setting.modify_password.v.ModifyDealPasImpl;
import com.block.vtCoin.mine.setting.modify_password.v.ModifyLoginPasImpl;
import com.block.vtCoin.request.ApiAction;
import com.block.vtCoin.request.MyModel;
import com.block.vtCoin.widget.editText.ClearEditText;
import com.block.vtCoin.widget.editText.MyTextWatcher;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 修改交易密码和修改登录密码两个界面公用
 */
public class ModifyPasswordActivity extends BaseActivity {
    @Bind(R.id.et_before_password)
    ClearEditText etBeforePassword;
    @Bind(R.id.visible1)
    ImageView visible1;
    @Bind(R.id.et_new_password)
    ClearEditText etNewPassword;
    @Bind(R.id.visible2)
    ImageView visible2;
    @Bind(R.id.et_repeat_new_password)
    ClearEditText etRepeatNewPassword;
    @Bind(R.id.visible3)
    ImageView visible3;
    @Bind(R.id.tv_commit)
    TextView tvCommit;
    private int from = 0;
    private String title;
    private ModifyLoginPasPresenter modifyLoginPasPresenter;
    private ModifyDealPasPresenter modifyDealPasPresenter;
    private String password1, password2, password3;
    private boolean isShow1 = false, isShow2 = false, isShow3 = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handIntent();
        setContentView(R.layout.activity_modify_login_password);
        setUltimateBar(R.color.blue1);
        intView();
    }

    private void handIntent() {
        from = getIntent().getIntExtra("from", 0);
        if (from == 0) {//修改登录密码
            title = getString(R.string.modify_login_password);
        } else if (from == 1) {//修改交易密码
            title = getString(R.string.modify_deal_password);
        }
    }

    private void intView() {
        etBeforePassword.addTextChangedListener(new MyTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().isEmpty()) {
                    etNewPassword.setText("");
                    etRepeatNewPassword.setText("");
                    setClickable(tvCommit, false);
                }
            }
        });
        etNewPassword.addTextChangedListener(new MyTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().isEmpty())
                    setClickable(tvCommit, false);
                else if (s.toString().length() == etRepeatNewPassword.getText().toString().length())
                    setClickable(tvCommit, true);
                else
                    setClickable(tvCommit, false);
            }
        });
        etRepeatNewPassword.addTextChangedListener(new MyTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().isEmpty())
                    setClickable(tvCommit, false);
                else if (s.toString().length() == etNewPassword.getText().toString().length())
                    setClickable(tvCommit, true);
                else
                    setClickable(tvCommit, false);
            }
        });
    }

    @Override
    public View getTitleBar() {
        return getNormalTitleBar().setTitle(title);
    }

    @Override
    protected List<BasePresenter> getPresenter(List<BasePresenter> presenters) {
        modifyLoginPasPresenter = new ModifyLoginPasPresenter();
        modifyLoginPasPresenter.setModelView(new MyModel<SmsEntity>(), new ModifyLoginPasImpl(this, ApiAction.Account_ModifyLoginPassword));
        modifyDealPasPresenter = new ModifyDealPasPresenter();
        modifyDealPasPresenter.setModelView(new MyModel<SmsEntity>(), new ModifyDealPasImpl(this, ApiAction.Account_ChangeAdvancedPassword));
        presenters.add(modifyLoginPasPresenter);
        presenters.add(modifyDealPasPresenter);
        return presenters;
    }

    public boolean isSure() {
        password1 = etBeforePassword.getText().toString().trim();
        password2 = etNewPassword.getText().toString().trim();
        password3 = etRepeatNewPassword.getText().toString().trim();
        if (password1.equals(password2)) {
            showTip(getString(R.string.tip_password_can_not_equal));
            etNewPassword.setText("");
            etRepeatNewPassword.setText("");
            return false;
        } else if (!password2.equals(password3)) {
            showTip(getString(R.string.password_not_equals));
            return false;
        } else {
            return true;
        }
    }

    @OnClick({R.id.visible1, R.id.visible2, R.id.visible3, R.id.tv_commit, R.id.tv_forget_password})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.visible1:
                isPasswordVis(etBeforePassword, visible1, isShow1, 1);
                break;
            case R.id.visible2:
                isPasswordVis(etNewPassword, visible2, isShow2, 2);
                break;
            case R.id.visible3:
                isPasswordVis(etRepeatNewPassword, visible3, isShow3, 3);
                break;
            case R.id.tv_commit:
                if (isSure() && isHasNet()) {
                    if (from == 0)
                        modifyLoginPasPresenter.modifyLoginPas();
                    else if (from == 1)
                        modifyDealPasPresenter.modifyDealPas();
                }
                break;
            case R.id.tv_forget_password:
                HashMap<String, Object> map = new HashMap<>();
                if (from == 0) //忘记登录密码
                    map.put("from", 0);
                else if (from == 1)//忘记交易密码
                    map.put("from", 1);
                toActivity(FindBackPasswordActivity.class);
                break;
        }
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
            else if (type == 2)
                isShow2 = false;
            else
                isShow3 = false;
            visImage.setImageResource(R.mipmap.visible);
        } else {
            et.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            et.setSelection(et.getText().length());
            if (type == 1)
                isShow1 = true;
            else if (type == 2)
                isShow2 = true;
            else
                isShow3 = true;
            visImage.setImageResource(R.mipmap.sword);
        }
    }

    public Map<String, Object> getModifyLoginPasMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("oldPsw", password1);
        map.put("newPsw", password2);
        map.put("cfmPsw", password3);
        return map;
    }

    public void setModifyLoginPas(SmsEntity entity) {
        toActivity(LoginActivity.class);
        finish();
    }

    public Map<String, Object> getModifyDealPasMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("oldPsw", password1);
        map.put("newPsw", password2);
        map.put("cfmPsw", password3);
        map.put("init", false);
        return map;
    }

    public void setModifyDealPas(SmsEntity entity) {
        finish();
    }
}