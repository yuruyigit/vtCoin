package com.block.vtCoin.main;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.block.net.request.HttpClient;
import com.block.vtCoin.request.ApiAction;
import com.block.vtCoin.util.AppManager;
import com.block.vtCoin.util.AppUtil;
import com.block.vtCoin.util.JsonUtils;
import com.lcodecore.tkrefreshlayout.footer.MyFooterView;
import com.lcodecore.tkrefreshlayout.header.MyHeaderView;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lsxiao.apllo.Apollo;
import com.lsxiao.apollo.generate.SubscriberBinderImplement;

import rx.android.schedulers.AndroidSchedulers;

import com.squareup.leakcanary.LeakCanary;

/**
 * @Description
 * @Author lijie
 * @Date 2017/7/12.
 */
public class BApplication extends MultiDexApplication {
    public static Context mContext;

    public void onCreate() {
        super.onCreate();
        mContext = this;
        AppManager.setContext(mContext);
        init();
    }

    private void init() {
        /*检测内存泄漏*/
        if (LeakCanary.isInAnalyzerProcess(this))
            return;
        LeakCanary.install(this);
        /*给请求添加header*/
        HttpClient.init(this, ApiAction.VtCoinUrl);
        /*给当前版本赋值，以及判断是否第一次进入app*/
        AppUtil.checkVersion(this);
        /*初始化apollo，编译时注解，要使用过才能编译成功*/
        Apollo.get().init(SubscriberBinderImplement.instance(), AndroidSchedulers.mainThread());
         /*默认刷新头部和底部*/
        TwinklingRefreshLayout.setDefaultHeader(MyHeaderView.class.getName());
        TwinklingRefreshLayout.setDefaultFooter(MyFooterView.class.getName());
        JsonUtils.initCodeMessage(this);
    }
}

