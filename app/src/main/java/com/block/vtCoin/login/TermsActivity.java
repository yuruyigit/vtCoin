package com.block.vtCoin.login;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.vtCoin.R;
import com.block.vtCoin.main.BaseActivity;

import java.util.List;

import butterknife.Bind;

public class TermsActivity extends BaseActivity {


    @Bind(R.id.webView)
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);
        setUltimateBar(R.color.white0);
        initView();

    }

    private void initView() {
        WebSettings wSet = webView.getSettings();
        wSet.setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/ServerClause.html");
    }

    /**
     * @return
     */
    @Override
    public View getTitleBar() {
        return getNormalTitleBar().setTitle(getString(R.string.service_terms)).setTitleTextColor(R.color.black1).setLeftDrawable(R.mipmap.return_01).setBackground(R.color.white0);
    }

    @Override
    protected List<BasePresenter> getPresenter(List<BasePresenter> presenters) {
        return null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
