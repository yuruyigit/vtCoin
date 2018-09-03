package com.block.vtCoin.main;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.block.net.mvp.view.PresenterActivity;
import com.block.vtCoin.R;
import com.block.vtCoin.constant.ApolloAction;
import com.block.vtCoin.login.LoginActivity;
import com.block.vtCoin.util.NetUtil;
import com.block.vtCoin.util.ToastUtil;
import com.block.vtCoin.util.UltimateBar;
import com.block.vtCoin.widget.dialog.LoadDialog;
import com.block.vtCoin.widget.title.NormalTitleBar;
import com.lsxiao.apllo.Apollo;
import com.lsxiao.apllo.annotations.Receive;
import com.lsxiao.apllo.entity.SubscriptionBinder;

import java.io.Serializable;
import java.util.Map;

import butterknife.ButterKnife;

/**
 * @Description
 * @Author lijie
 * @Date 2017/7/12.
 */
public abstract class BaseActivity extends PresenterActivity {
    private Context mContext;
    public LinearLayout baseView;//标题栏
    private SubscriptionBinder mBinder;
    private UltimateBar ultimateBar;
    private LoadDialog mLoadDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = this;
        mBinder = Apollo.get().bind(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);// 锁定竖屏
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        setContentView(getContainView(layoutResID));
        ButterKnife.bind(this);//bind 注解
    }

    private View getContainView(int layoutResID) {
        LayoutInflater inflater = LayoutInflater.from(this);
        baseView = (LinearLayout) inflater.inflate(R.layout.activity_toolbar_base, null);
        View contentView = inflater.inflate(layoutResID, null);
        if (getTitleBar() != null) {
            baseView.addView(getTitleBar());
        }
        baseView.addView(contentView);
        return baseView;
    }

    public void setBackground(int color) {
        baseView.setBackgroundResource(color);
    }

    public void setUltimateBar(int color) {
        if (ultimateBar == null) {
            ultimateBar = new UltimateBaar.r(this);
            ultimateBar.setType(UltimateBONLY_STATUS_BAR);
        }
        if (0 == color)
            ultimateBar.setImmersionBar();
        else
            ultimateBar.setColorBar(ContextCompat.getColor(this, color));
    }

    protected NormalTitleBar getNormalTitleBar() {
        return new NormalTitleBar(this);
    }

    public abstract View getTitleBar();

    public void setLeftDrawable(TextView tv, int rec) {
        tv.setCompoundDrawablesWithIntrinsicBounds(rec, 0, 0, 0);
    }

    public void toActivity(Class<?> cls) {
        Intent it = new Intent(this, cls);
        startActivity(it);
    }

    public void toActivity(Class<?> cls, Map<String, Object> params) {
        Intent it = new Intent(this, cls);
        if (params != null)
            for (String key : params.keySet()) {
                if (params.get(key) instanceof String) {
                    it.putExtra(key, (String) params.get(key));
                } else if (params.get(key) instanceof Serializable) {
                    it.putExtra(key, (Serializable) params.get(key));
                } else if (params.get(key) instanceof Float) {
                    it.putExtra(key,
                            Float.parseFloat(params.get(key).toString()));
                } else if (params.get(key) instanceof Double) {
                    it.putExtra(key,
                            Double.parseDouble(params.get(key).toString()));
                } else if (params.get(key) instanceof Integer) {
                    it.putExtra(key, Integer.parseInt(params.get(key).toString()));
                }
            }
        startActivity(it);
    }


    public void toActivityResult(Class<?> cls, int requestCode, Map<String, Object> params) {
        Intent it = new Intent(this, cls);
        if (params != null)
            for (String key : params.keySet()) {
                if (params.get(key) instanceof String) {
                    it.putExtra(key, (String) params.get(key));
                } else if (params.get(key) instanceof Serializable) {
                    it.putExtra(key, (Serializable) params.get(key));
                }
            }
        startActivityForResult(it, requestCode);
    }


    public void showTip(String message) {
        ToastUtil.getInstance().showBottomMessage(mContext, message);
    }

    public void loadDialog() {
        loadDialog("");
    }

    public void loadDialog(String msg) {
        if (mLoadDialog == null) {
            mLoadDialog = new LoadDialog(this);
        }
        if (msg.isEmpty()) {
            mLoadDialog.setNoText();
        } else
            mLoadDialog.setText(msg);
        mLoadDialog.setCancelable(false);
        mLoadDialog.show();
    }

    public void dismissDialog() {
        if (mLoadDialog != null) {
            mLoadDialog.dismiss();
        }
    }

    public boolean isHasNet() {
        if (NetUtil.getNetworkState(mContext) == NetUtil.NETWORN_NONE) {
            showTip("请检查网络连接");
            return false;
        }
        return true;
    }

    public void setClickable(TextView tv, Boolean b) {
        if (b) {
            tv.setBackgroundResource(R.drawable.bg_blue1_5);
            tv.setClickable(true);
        } else {
            tv.setBackgroundResource(R.drawable.bg_before0_5);
            tv.setClickable(false);
        }
    }

    @Receive(tag = ApolloAction.To_Login)
    public void toLogin() {
        showTip("登录失效，重新登录");
        toActivity(LoginActivity.class);
    }

    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        if (null != mBinder)
            mBinder.unbind();
        super.onDestroy();
    }
}

