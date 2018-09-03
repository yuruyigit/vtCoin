package com.block.vtCoin.mine.setting.trader_ad;

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

public class AddAdActivity extends BaseActivity {
    @Bind(R.id.et_name)
    EditText etName;
    @Bind(R.id.et_content)
    EditText etContent;
    private String name,content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ad);
        setUltimateBar(R.color.blue1);
        setBackground(R.color.gray7);
        initView();
    }

    private void initView() {
    }

    @Override
    public View getTitleBar() {
        NormalTitleBar normalTitleBar = getNormalTitleBar().setTitle(getString(R.string.new_add_ad)).setRightText(R.string.save);
        normalTitleBar.setRightListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
        normalTitleBar.setLeftListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });
        return normalTitleBar;
    }

    public void save() {
        name=etName.getText().toString().trim();
        content=etContent.getText().toString().trim();
        if(!name.isEmpty()&&!content.isEmpty()) {
            Intent intent = getIntent();
            intent.putExtra("addName", name);
            setResult(RESULT_OK, intent);
            finish();
        }else
            showTip("请填写完整信息");
    }

    public void back() {
        name=etName.getText().toString().trim();
        content=etContent.getText().toString().trim();
        if(!name.isEmpty()&&!content.isEmpty()) {
            Intent intent = getIntent();
            intent.putExtra("addName", name);
            setResult(RESULT_OK, intent);
            finish();
        }else
            finish();
    }

    @Override
    protected List<BasePresenter> getPresenter(List<BasePresenter> presenters) {
        return null;
    }
}
