package com.block.vtCoin.main.fragment.v;

import android.content.Context;

import com.block.net.mvp.view.ICommonView;
import com.block.vtCoin.entity.PersonInfoEntity;
import com.block.vtCoin.entity.SmsEntity;
import com.block.vtCoin.main.fragment.MineFragment;

import java.util.Map;

/**
 * @Description 获取验证码的公共view
 */
public class PersonInfoViewImpl implements ICommonView<PersonInfoEntity> {
    private final MineFragment fragment;
    private String urlAction = "";

    public PersonInfoViewImpl(MineFragment fragment, String urlAction) {
        this.fragment = fragment;
        this.urlAction = urlAction;
    }

    @Override
    public Map<String, Object> getParameters() {
        return null;
    }

    @Override
    public void onError(int code, String msg) {
        fragment.setPersonInfo(null);
    }

    @Override
    public void onCompleted(PersonInfoEntity entity) {
        fragment.setPersonInfo(entity);
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
    public Class<PersonInfoEntity> getEClass() {
        return PersonInfoEntity.class;
    }
}
