package com.block.vtCoin.mine.setting.modify_password;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.block.net.mvp.presenter.BasePresenter;
import com.block.vtCoin.constant.Constant;
import com.block.vtCoin.R;
import com.block.vtCoin.main.BaseActivity;
import com.block.vtCoin.login.SetPasswordActivity;
import com.block.vtCoin.util.CountDownUtil;
import com.block.vtCoin.widget.editText.ClearEditText;
import com.block.vtCoin.widget.editText.MyTextWatcher;
import java.util.List;
import butterknife.Bind;
import butterknife.OnClick;

public class EmailFindBackPasswordActivity extends BaseActivity {
    @Bind(R.id.tv_email)
    TextView tvEmail;
    @Bind(R.id.et_code)
    ClearEditText etCode;
    @Bind(R.id.get_code)
    TextView getCode;
    @Bind(R.id.next)
    TextView next;
    private String  code;
    private CountDownUtil countDownUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_find_back_password);
        setUltimateBar(R.color.blue1);
        initView();

    }

    private void initView() {
        etCode.addTextChangedListener(new MyTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().isEmpty())
                    setClickable(next, false);
                else
                    setClickable(next, true);
            }
        });

        countDownUtil = new CountDownUtil(this, getCode, Constant.SendCodeTime, 1000,
                new CountDownUtil.onFinishListener() {
                    @Override
                    public void finish() {
                        setClickable(getCode, true);
                    }
                });
    }

    /**
     * @return
     */
    @Override
    public View getTitleBar() {
        return getNormalTitleBar().setTitle(getString(R.string.find_password));
    }

    @Override
    protected List<BasePresenter> getPresenter(List<BasePresenter> presenters) {
        return null;
    }

    public boolean isNext() {
        code = etCode.getText().toString().trim();
        if (code.isEmpty()) {
            return false;
        }
        return true;
    }


    @Override
    protected void onDestroy() {
        countDownUtil.cancel();
        super.onDestroy();
    }

    @OnClick({R.id.get_code, R.id.next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.get_code:
                countDownUtil.start();
                break;
            case R.id.next:
                if (isNext() && isHasNet()) {
                    startActivity(new Intent(this, SetPasswordActivity.class).putExtra("from", 2));
                }
                break;
        }
    }
}
