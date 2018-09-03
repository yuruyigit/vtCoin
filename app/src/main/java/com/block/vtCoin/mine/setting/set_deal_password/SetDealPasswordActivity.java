package com.block.vtCoin.mine.setting.set_deal_password;

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
import com.block.vtCoin.main.BaseActivity;
import com.block.vtCoin.request.ApiAction;
import com.block.vtCoin.request.MyModel;
import com.block.vtCoin.widget.editText.ClearEditText;
import com.block.vtCoin.widget.editText.MyTextWatcher;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

public class SetDealPasswordActivity extends BaseActivity {
    @Bind(R.id.et_password1)
    ClearEditText etPassword1;
    @Bind(R.id.visible1)
    ImageView visible1;
    @Bind(R.id.et_password2)
    ClearEditText etPassword2;
    @Bind(R.id.visible2)
    ImageView visible2;
    @Bind(R.id.et_password3)
    ClearEditText etPassword3;
    @Bind(R.id.visible3)
    ImageView visible3;
    @Bind(R.id.tv_commit)
    TextView tvCommit;
    private SetDealPasPresenter setDealPasPresenter;
    private String password1, password2, password3;
    private boolean isShow1 = false, isShow2 = false, isShow3 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_sale_password);
        setUltimateBar(R.color.blue1);
        intView();
    }

    private void intView() {
        etPassword1.addTextChangedListener(new MyTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().isEmpty()) {
                    etPassword2.setText("");
                    etPassword3.setText("");
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

        etPassword3.addTextChangedListener(new MyTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().isEmpty())
                    setClickable(tvCommit, false);
                else if (s.toString().length() == etPassword2.getText().toString().length())
                    setClickable(tvCommit, true);
                else
                    setClickable(tvCommit, false);
            }
        });
    }

    @Override
    public View getTitleBar() {
        return getNormalTitleBar().setTitle(getString(R.string.set_deal_password));
    }

    @Override
    protected List<BasePresenter> getPresenter(List<BasePresenter> presenters) {
        setDealPasPresenter = new SetDealPasPresenter();
        setDealPasPresenter.setModelView(new MyModel<SmsEntity>(), new SetDealPasViewImpl(this, ApiAction.Account_ChangeAdvancedPassword));
        presenters.add(setDealPasPresenter);
        return presenters;
    }

    public boolean isSure() {
        password1 = etPassword1.getText().toString().trim();
        password2 = etPassword2.getText().toString().trim();
        password3 = etPassword3.getText().toString().trim();
        if (password1.equals(password2)) {
            showTip(getString(R.string.tip_deal_and_login_can_not_equal));
            etPassword2.setText("");
            etPassword3.setText("");
            return false;
        } else if (!password2.equals(password3)) {
            showTip(getString(R.string.password_not_equals));
            return false;
        } else {
            return true;
        }
    }

    @OnClick({R.id.visible1, R.id.visible2, R.id.visible3, R.id.tv_commit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.visible1:
                isPasswordVis(etPassword1, visible1, isShow1, 1);
                break;
            case R.id.visible2:
                isPasswordVis(etPassword2, visible2, isShow2, 2);
                break;
            case R.id.visible3:
                isPasswordVis(etPassword3, visible3, isShow3, 3);
                break;
            case R.id.tv_commit:
                if (isSure() && isHasNet())
                    setDealPasPresenter.setDealPas();
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

    public Map<String, Object> getSetDealPasMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("oldPsw", password1);
        map.put("newPsw", password2);
        map.put("cfmPsw", password3);
        map.put("init", true);
        return map;
    }

    public void setDealPas() {
        setResult(RESULT_OK);
        finish();
    }
}
