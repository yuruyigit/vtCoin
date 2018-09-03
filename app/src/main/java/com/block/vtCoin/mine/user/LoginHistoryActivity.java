package com.block.vtCoin.mine.user;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.block.net.mvp.presenter.BasePresenter;
import com.block.vtCoin.R;
import com.block.vtCoin.entity.LoginHistoryEntity;
import com.block.vtCoin.entity.SmsEntity;
import com.block.vtCoin.main.BaseActivity;
import com.block.vtCoin.mine.user.adapter.LoginHistoryAdapter;
import com.block.vtCoin.mine.user.p.LoginHistoryPresenter;
import com.block.vtCoin.mine.user.v.LoginHistoryViewImpl;
import com.block.vtCoin.request.ApiAction;
import com.block.vtCoin.request.MyModel;
import com.block.vtCoin.widget.title.NormalTitleBar;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import java.util.List;
import butterknife.Bind;

public class LoginHistoryActivity extends BaseActivity{
    @Bind(R.id.tv_times)
    TextView tvTimes;
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    @Bind(R.id.refresh_layout)
    TwinklingRefreshLayout refreshLayout;
    @Bind(R.id.ivStatus)
    ImageView ivStatus;
    @Bind(R.id.tvStatus)
    TextView tvStatus;
    @Bind(R.id.null_layout)
    LinearLayout nullLayout;
    private LoginHistoryPresenter loginHistoryPresenter;
    private LoginHistoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_history);
        setUltimateBar(R.color.blue1);
        initView();
    }

    private void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        adapter = new LoginHistoryAdapter(this);
        recyclerView.setAdapter(adapter);
        loginHistoryPresenter.loginHistory();
    }

    @Override
    protected List<BasePresenter> getPresenter(List<BasePresenter> presenters) {
        loginHistoryPresenter=new LoginHistoryPresenter();
        loginHistoryPresenter.setModelView(new MyModel<LoginHistoryEntity>(),new LoginHistoryViewImpl(this, ApiAction.Account_LoginHistory));
        presenters.add(loginHistoryPresenter);
        return presenters;
    }

    @Override
    public View getTitleBar() {
        return new NormalTitleBar(this).setTitle("登录历史纪录");
    }

    public void setLoginHistory(LoginHistoryEntity entity) {
        if(entity!=null){
            tvTimes.setText(entity.getData().getCount()+"");
            adapter.setData(entity.getData().getHis());
        }
    }
}
