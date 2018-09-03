package com.block.vtCoin.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.vtCoin.R;
import com.block.vtCoin.main.BaseActivity;
import com.block.vtCoin.util.MatchUtil;
import com.block.vtCoin.widget.editText.ClearEditText;
import com.block.vtCoin.widget.editText.MyTextWatcher;
import com.block.vtCoin.widget.title.NormalTitleBar;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;


/**
 * 邮箱注册和邮箱找回密码的复用界面
 */
public class EmailRegisterActivity extends BaseActivity {

    @Bind(R.id.et_email)
    ClearEditText etEmail;
    @Bind(R.id.next)
    TextView next;
    @Bind(R.id.agree)
    TextView agree;
    @Bind(R.id.terms)
    TextView terms;
    @Bind(R.id.phone_register)
    TextView phoneRegister;
    @Bind(R.id.scrollView)
    ScrollView scrollView;
    private int from;
    private String title="",goTo="";
    private String email;
    private boolean isAgree=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handIntent();
        setContentView(R.layout.activity_email_register);
        initView();
        setUltimateBar(R.color.white0);
    }

    private void handIntent() {
        from=getIntent().getIntExtra("from",-1);
        if(from==0){/*来自email注册*/
            title=getString(R.string.email_register);
            goTo=getString(R.string.phone_register);
        }else if(from==1){/*来自email找回密码*/
            title=getString(R.string.find_password);
            goTo=getString(R.string.find_by_phone);
        }
    }

    private void initView() {
        if(from==0){
            terms.setVisibility(View.VISIBLE);
            agree.setVisibility(View.VISIBLE);
        }else if(from==1){
            terms.setVisibility(View.GONE);
            agree.setVisibility(View.GONE);
        }
        phoneRegister.setText(goTo);

        etEmail.addTextChangedListener(new MyTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty())
                    setClickable(next, true);
            }
        });
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

    public boolean isNext() {
        email = etEmail.getText().toString().trim();
        if (!MatchUtil.isEmail(email)) {
            showTip(getString(R.string.email_format_fail));
            return false;
        } else if (!isAgree) {
            showTip(getString(R.string.please_read_terms));
            return false;
        }
        return true;
    }

    @OnClick({R.id.next, R.id.agree, R.id.terms, R.id.phone_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.next:
                if (isNext() && isHasNet()) {
                    if(from==0){/*来自email注册*/
                        startActivity(new Intent(this, SetPasswordActivity.class).putExtra("from", 1).putExtra("email", email));
                    }else if(from==1){/*来自email找回密码*/
                        startActivity(new Intent(this, SetPasswordActivity.class).putExtra("from", 3).putExtra("email", email));
                    }
                }
                break;
            case R.id.agree:
                if (isAgree) {
                    isAgree = false;
                    setLeftDrawable(agree, R.mipmap.circle);
                } else {
                    isAgree = true;
                    setLeftDrawable(agree, R.mipmap.circle_click);
                }
                break;
            case R.id.terms:
                toActivity(TermsActivity.class);
                break;
            case R.id.phone_register:
                if(from==0){
                    toActivity(PhoneRegisterActivity.class);
                }else if(from==1){
                    toActivity(FindPasswordActivity.class);
                }
                break;
        }
    }

    public Map<String,Object> getRegisterMap() {
        Map<String,Object> map=new HashMap<>();
        map.put("EmailOrPhoneNumber","15007253417");
        map.put("Password","Jixin1314");
        return map;
    }
    public void setRegister(boolean isSuccess){

    }
}
