package com.block.vtCoin.mine.issue_buy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.vtCoin.R;
import com.block.vtCoin.main.BaseActivity;
import com.block.vtCoin.widget.title.NormalTitleBar;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class InputPhoneActivity extends BaseActivity {

    @Bind(R.id.et_phone)
    EditText etPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_phone);
        ButterKnife.bind(this);
        setUltimateBar(R.color.blue1);
        setBackground(R.color.gray7);
        initView();
    }

    private void initView() {
    }

    /**
     * @return
     */
    @Override
    public View getTitleBar() {
        NormalTitleBar normalTitleBar = getNormalTitleBar().setTitle(getString(R.string.phone)).setRightText(getString(R.string.save));
        normalTitleBar.setRightListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
        return normalTitleBar;
    }
    private void save() {
        Intent intent = getIntent();
        intent.putExtra("phone", etPhone.getText().toString().trim());
        this.setResult(RESULT_OK,intent);
        finish();
    }
    @Override
    protected List<BasePresenter> getPresenter(List<BasePresenter> presenters) {
        return null;
    }
}
