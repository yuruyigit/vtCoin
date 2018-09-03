package com.block.vtCoin.main.fragment.v;

import android.content.Context;

import com.block.net.mvp.view.ICommonView;
import com.block.vtCoin.R;
import com.block.vtCoin.entity.IsDealerEntity;
import com.block.vtCoin.entity.SmsEntity;
import com.block.vtCoin.login.FindPasswordActivity;
import com.block.vtCoin.login.PhoneRegisterActivity;
import com.block.vtCoin.main.BaseActivity;
import com.block.vtCoin.main.fragment.LazyLoadFragment;
import com.block.vtCoin.main.fragment.MineFragment;
import com.block.vtCoin.mine.setting.modify_password.PhoneFindBackPasswordActivity;

import java.util.Map;

/**
 * @Description 获取验证码的公共view
 */
public class IsDealerViewImpl implements ICommonView<IsDealerEntity> {
    private final MineFragment fragment;
    private String urlAction = "";

    public IsDealerViewImpl(MineFragment fragment, String urlAction) {
        this.fragment = fragment;
        this.urlAction = urlAction;
    }

    @Override
    public Map<String, Object> getParameters() {
        return null;
    }

    @Override
    public void onError(int code, String msg) {
        fragment.setDealer(null);
    }

    @Override
    public void onCompleted(IsDealerEntity entity) {
        fragment.setDealer(entity);
    }

    @Override
    public void loading() {
    }

    @Override
    public void dismiss() {
    }

    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public String getUrlAction() {
        return urlAction;
    }

    @Override
    public Class<IsDealerEntity> getEClass() {
        return IsDealerEntity.class;
    }
}
