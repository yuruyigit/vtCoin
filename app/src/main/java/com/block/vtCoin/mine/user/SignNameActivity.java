package com.block.vtCoin.mine.user;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.vtCoin.R;
import com.block.vtCoin.constant.ApolloAction;
import com.block.vtCoin.entity.PersonInfoEntity;
import com.block.vtCoin.entity.SmsEntity;
import com.block.vtCoin.main.BaseActivity;
import com.block.vtCoin.mine.user.p.UpdateSignPresenter;
import com.block.vtCoin.mine.user.v.UpdateSignViewImpl;
import com.block.vtCoin.request.ApiAction;
import com.block.vtCoin.request.MyModel;
import com.block.vtCoin.util.AppManager;
import com.lsxiao.apllo.Apollo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;

public class SignNameActivity extends BaseActivity {
    @Bind(R.id.nick_name)
    EditText signName;
    private UpdateSignPresenter updateSignPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_name);
        setUltimateBar(R.color.blue1);
        setBackground(R.color.gray7);
    }

    @Override
    public View getTitleBar() {
        return getNormalTitleBar().setTitle(getResources().getString(R.string.sign_name))
                .setRightText(getString(R.string.save)).setRightListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!TextUtils.isEmpty(signName.getText().toString())) {
                            updateSignPresenter.updateSign();
                        } else {
                            showTip("不能为空");
                        }
                    }
                });
    }

    @Override
    protected List<BasePresenter> getPresenter(List<BasePresenter> presenters) {
        updateSignPresenter = new UpdateSignPresenter();
        updateSignPresenter.setModelView(new MyModel<SmsEntity>(), new UpdateSignViewImpl(this, ApiAction.Account_ResetComment));
        presenters.add(updateSignPresenter);
        return presenters;
    }

    public Map<String, Object> getSignMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("Comment", signName.getText().toString().trim());
        return map;
    }

    public void setSign() {
        AppManager.getPersonInfo().setComment(signName.getText().toString().trim());
        Apollo.get().send(ApolloAction.Update_Sign);
        finish();
    }
}
