package com.block.vtCoin.mine.setting.account_address;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.vtCoin.R;
import com.block.vtCoin.main.BaseActivity;
import com.block.vtCoin.mine.setting.account_address.adapter.AccountAddressAdapter;
import com.block.vtCoin.mine.setting.account_address.adapter.SwipeMenuLayout;
import com.block.vtCoin.mine.setting.account_address.m.AccountAddressEntity;
import com.block.vtCoin.util.ScreenUtils;
import com.block.vtCoin.widget.decoration.RecycleViewDivider;
import com.block.vtCoin.widget.title.NormalTitleBar;
import java.util.ArrayList;
import java.util.List;

public class AccountAddressActivity extends BaseActivity {
    private static final String TAG = "zxt";
    private RecyclerView mRv;
    private AccountAddressAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private List<AccountAddressEntity> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_address);
        setUltimateBar(R.color.blue1);
        mRv = (RecyclerView) findViewById(R.id.rv);
        initDatas();
        mAdapter = new AccountAddressAdapter(this, mDatas);
        mAdapter.setOnSwipeListener(new AccountAddressAdapter.onSwipeListener() {
            @Override
            public void onDel(int pos) {
                if (pos >= 0 && pos < mDatas.size()) {
                    mDatas.remove(pos);
                    mAdapter.notifyItemRemoved(pos);//推荐用这个
                    //如果删除时，不使用mAdapter.notifyItemRemoved(pos)，则删除没有动画效果，
                    //且如果想让侧滑菜单同时关闭，需要同时调用 ((CstSwipeDelMenu) holder.itemView).quickClose();
                    //mAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onEdit(int pos) {
               toActivity(EditAddressActivity.class);
            }
        });
        mAdapter.setOnItemListener(new AccountAddressAdapter.OnItemListener() {
            @Override
            public void onItem(int position) {
                toActivity(EditAddressActivity.class);
            }
        });

        mRv.addItemDecoration(new RecycleViewDivider(this,R.color.gray6,ScreenUtils.dip2px(10)));

        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRv.setLayoutManager(mLayoutManager);
        mRv.setAdapter(mAdapter);


        //6 2016 10 21 add , 增加viewChache 的 get()方法，
        // 可以用在：当点击外部空白处时，关闭正在展开的侧滑菜单。我个人觉得意义不大，
        mRv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    SwipeMenuLayout viewCache = SwipeMenuLayout.getViewCache();
                    if (null != viewCache) {
                        viewCache.smoothClose();
                    }
                }
                return false;
            }
        });
    }

    @Override
    public View getTitleBar() {
        NormalTitleBar normalTitleBar=new NormalTitleBar(this).setTitle(getString(R.string.account_address));
        normalTitleBar.setRightDrawable(R.mipmap.set_up_plus).setRightListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toActivity(AddAddressActivity.class);
            }
        });
        return normalTitleBar;
    }
    @Override
    protected List<BasePresenter> getPresenter(List<BasePresenter> presenters) {
        return null;
    }

    private void initDatas() {
        mDatas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            AccountAddressEntity entity = new AccountAddressEntity();
            entity.type = 0;
            entity.name = "阳刚听听";
            entity.address = "sdasdfasldfalsdfkadsfd";
            mDatas.add(entity);
        }
    }
}
