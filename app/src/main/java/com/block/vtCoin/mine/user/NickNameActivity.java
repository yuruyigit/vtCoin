package com.block.vtCoin.mine.user;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.vtCoin.R;
import com.block.vtCoin.constant.ApolloAction;
import com.block.vtCoin.entity.SmsEntity;
import com.block.vtCoin.main.BaseActivity;
import com.block.vtCoin.mine.user.p.UpdateNickNamePresenter;
import com.block.vtCoin.mine.user.v.UpdateNickNameViewImpl;
import com.block.vtCoin.request.ApiAction;
import com.block.vtCoin.request.MyModel;
import com.block.vtCoin.util.AppManager;
import com.lsxiao.apllo.Apollo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;

public class NickNameActivity extends BaseActivity {
    @Bind(R.id.nick_name)
    EditText nikeName;
    private UpdateNickNamePresenter updateNickNamePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nick_name);
        setUltimateBar(R.color.blue1);
    }

    @Override
    public View getTitleBar() {
        return getNormalTitleBar()
                .setTitle(getResources().getString(R.string.nickname))
                .setRightText(getString(R.string.save))
                .setRightListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!TextUtils.isEmpty(nikeName.getText().toString())) {
                            updateNickNamePresenter.updateNikeName();
                        } else {
                            showTip("不能为空");
                        }
                    }
                });
    }

    @Override
    protected List<BasePresenter> getPresenter(List<BasePresenter> presenters) {
        updateNickNamePresenter = new UpdateNickNamePresenter();
        updateNickNamePresenter.setModelView(new MyModel<SmsEntity>(), new UpdateNickNameViewImpl(this, ApiAction.Account_UpdateNickName));
        presenters.add(updateNickNamePresenter);
        return presenters;
    }


    public Map<String, Object> getNikeMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("NickName", nikeName.getText().toString().trim());
        return map;
    }

    public void setNickName() {
        AppManager.getPersonInfo().setNickName(nikeName.getText().toString().trim());
        Apollo.get().send(ApolloAction.Update_NickName);
        finish();
    }
}
