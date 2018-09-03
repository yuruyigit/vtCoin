package com.block.vtCoin.mine.user;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.vtCoin.constant.ApolloAction;
import com.block.vtCoin.constant.Constant;
import com.block.vtCoin.R;
import com.block.vtCoin.entity.LoginHistoryEntity;
import com.block.vtCoin.entity.SmsEntity;
import com.block.vtCoin.main.BaseActivity;
import com.block.vtCoin.mine.user.p.LanguagePresenter;
import com.block.vtCoin.mine.user.v.LanguageViewImpl;
import com.block.vtCoin.request.ApiAction;
import com.block.vtCoin.request.MyModel;
import com.block.vtCoin.util.AppManager;
import com.block.vtCoin.util.SPUtils;
import com.block.vtCoin.widget.title.NormalTitleBar;
import com.lsxiao.apllo.Apollo;

import java.util.LinkedList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by liubin on 2017/8/9.
 */

public class ChooseLanguageActivity extends BaseActivity {
    @Bind(R.id.list_view)
    ListView listView;
    private LanguagePresenter languagePresenter;
    private LanguageAdapter adapter;
    private List<String> mData;
    private String currentLanguge;
    private int currentLangugeIndex = 0;
    private int checkPosition = 0;
    private NormalTitleBar titleBar;
    private TextView save;
    private String chooseLanguage;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        setUltimateBar(R.color.blue1);
        initView();
    }

    private void initView() {
        mData = new LinkedList<>();
        mData.add(getResources().getString(R.string.chinese));
        mData.add(getResources().getString(R.string.English));
        currentLanguge = AppManager.currentLanguage();
        if (currentLanguge.equals(Constant.Chinese)) {
            currentLangugeIndex = 0;
        } else {
            currentLangugeIndex = 1;
        }
        checkPosition = currentLangugeIndex;
        adapter = new LanguageAdapter(this, mData);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position != checkPosition) {
                    checkPosition = position;
                    if (position == currentLangugeIndex) {
                        save.setTextColor(getResources().getColor(R.color.gray1));
                        save.setClickable(false);
                    } else {
                        save.setTextColor(getResources().getColor(R.color.white0));
                        save.setClickable(true);
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    protected List<BasePresenter> getPresenter(List<BasePresenter> presenters) {
        languagePresenter = new LanguagePresenter();
        languagePresenter.setModelView(new MyModel<SmsEntity>(), new LanguageViewImpl(this, ApiAction.Account_RestLang));
        presenters.add(languagePresenter);
        return presenters;
    }

    @Override
    public View getTitleBar() {
        titleBar = getNormalTitleBar().setTitle(getResources().getString(R.string.more_language)).setRightText(getResources().getString(R.string.save));
        save = titleBar.getRightView();
        save.setTextColor(getResources().getColor(R.color.gray1));
        save.setTextSize(15);
        titleBar.setRightListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 chooseLanguage = mData.get(checkPosition);
                languagePresenter.changeLanguage(chooseLanguage);
            }
        });
        return titleBar;
    }

    public void setLanguage() {
        SPUtils.putString(Constant.CurrentLanguage,chooseLanguage);
        AppManager.getPersonInfo().setLang(chooseLanguage);
        Apollo.get().send(ApolloAction.Update_Language);
        finish();
    }


    public class LanguageAdapter extends BaseAdapter {
        Context mContext;
        List<String> mData;

        public LanguageAdapter(Context context, List<String> data) {
            this.mContext = context;
            this.mData = data;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHold viewHold = null;
            String name = mData.get(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_pay_way, parent, false);
                viewHold = new ViewHold();
                viewHold.payName = (TextView) convertView.findViewById(R.id.pay_name);
                convertView.setTag(viewHold);
            } else {
                viewHold = (ViewHold) convertView.getTag();
            }
            viewHold.payName.setText(name.trim());
            if (position == checkPosition) {
                setChecked(position, viewHold.payName, true);
            } else {
                setChecked(position, viewHold.payName, false);
            }
            return convertView;
        }

        public void setChecked(int positon, TextView tv, Boolean isChecked) {
            if (positon == 0) {//中文
                if (isChecked)
                    tv.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.set_up_china, 0, R.mipmap.set_up_cted, 0);
                else
                    tv.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.set_up_china, 0, 0, 0);
            }
            if (positon == 1) {//引文
                if (isChecked)
                    tv.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.set_up_english, 0, R.mipmap.set_up_cted, 0);
                else
                    tv.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.set_up_english, 0, 0, 0);
            }
            if (positon == 2) {//引文
                if (isChecked)
                    tv.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.set_up_indonesin, 0, R.mipmap.set_up_cted, 0);
                else
                    tv.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.set_up_indonesin, 0, 0, 0);
            }
        }

        @Override
        public int getCount() {
            if (mData == null) {
                return 0;
            }
            return mData.size();
        }

        @Override
        public Object getItem(int position) {
            if (mData != null && position < mData.size()) {
                return mData.size();
            } else {
                return null;
            }
        }

        @Override
        public long getItemId(int position) {
            return position;
        }
    }

    class ViewHold {
        TextView payName;
    }
}