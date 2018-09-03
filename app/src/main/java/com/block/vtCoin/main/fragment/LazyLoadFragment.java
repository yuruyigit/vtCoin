package com.block.vtCoin.main.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.block.net.util.L;
import com.lsxiao.apllo.Apollo;
import com.lsxiao.apllo.entity.SubscriptionBinder;

import java.util.Map;

import butterknife.ButterKnife;

/**
 * Fragment预加载问题的解决方案：
 * 1.可以懒加载的Fragment
 * 2.切换到其他页面时停止加载数据（可选）
 * blog ：http://blog.csdn.net/linglongxin24/article/details/53205878
 */
public abstract class LazyLoadFragment extends Fragment {
    /**
     * 是否已经初初始化布局
     */
    protected boolean isInit = false;
    /**
     * 是否已经在前台过
     */
    protected boolean isLoad = false;
    private View view;

    private SubscriptionBinder mBinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinder = Apollo.get().bind(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(setContentView(), container, false);
        ButterKnife.bind(this, view);
        initView();
        isInit = true;
        isLoad = true;
        return view;
    }

    /**
     * 视图是否已经对用户可见，系统的方法
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
//        L.i("setUserVisibleHint");
        isCanLoadData();
    }

    /**
     * 是否可以加载数据
     * 可以加载数据的条件：
     * 1.视图已经初始化
     * 2.视图对用户可见
     */
    private void isCanLoadData() {
        if (!isInit) {//试图没有初始化
            return;
        }
        if (getUserVisibleHint()) {//试图可见
            inCurrent();
            onWakeOrCurrent();
            isLoad = true;
        } else {//试图不可见
            if (isLoad) {
                notCurrent();
            }
        }
    }

    @Override
    public void onResume() {
        if (isInit && getUserVisibleHint()) {
            onWakeOrCurrent();
        }
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        notCurrent();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isInit = false;
        isLoad = false;
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        if (mBinder != null) {
            mBinder.unbind();
        }
        super.onDestroy();
    }

    protected abstract int setContentView();

    /**
     * 加载完布局后加载其他view
     */
    protected abstract void initView();

    /**
     * 当fragment可见时调用
     */
    protected void inCurrent() {
    }

    /**
     * 1.当fragment只是唤醒不是切换到前台
     * 2.切换到前台不是唤醒
     */
    protected void onWakeOrCurrent() {
    }

    /**
     * 当fragment不可见时
     */
    protected void notCurrent() {
    }


    @Nullable
    @Override
    public View getView() {
        return view;
    }
}
