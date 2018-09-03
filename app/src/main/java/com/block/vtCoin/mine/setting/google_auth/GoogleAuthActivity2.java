package com.block.vtCoin.mine.setting.google_auth;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.block.net.mvp.presenter.BasePresenter;
import com.block.vtCoin.R;
import com.block.vtCoin.main.BaseActivity;
import com.block.vtCoin.widget.editText.ClearEditText;
import com.block.vtCoin.widget.editText.MyTextWatcher;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GoogleAuthActivity2 extends BaseActivity {
    @Bind(R.id.et_code)
    ClearEditText etCode;
    @Bind(R.id.next)
    TextView next;
    private String code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_auth2);
        ButterKnife.bind(this);
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
    }

    @Override
    public View getTitleBar() {
        return getNormalTitleBar().setTitle(getString(R.string.google_attest));
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
    @OnClick(R.id.next)
    public void onClick() {
        if (isNext() && isHasNet()) {
            showTip("提交成功");
        }
    }
}
