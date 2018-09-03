package com.block.net.mvp.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.net.util.L;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 此Activity只是借助生命周期来控制presenter来注销网络请求
 */

public abstract class PresenterActivity extends AppCompatActivity {
    protected List<BasePresenter> presenters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenters = new ArrayList<>();
        getPresenter(presenters);
    }

    @Override
    protected void onDestroy() {
        if (getPresenter(presenters) != null) {
            for (BasePresenter presenter : getPresenter(presenters)) {
                presenter.unRegisterRx();
            }
            presenters=null;
        }
        super.onDestroy();
    }

    protected abstract List<BasePresenter> getPresenter(List<BasePresenter> presenters);
}
