package com.block.vtCoin.mine.issue_buy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.vtCoin.R;
import com.block.vtCoin.main.BaseActivity;
import com.block.vtCoin.mine.issue_buy.pay_way.PayWayActivity;
import com.block.vtCoin.mine.setting.trader_ad.TraderAdActivity;
import com.block.vtCoin.widget.title.NormalTitleBar;
import com.zhy.android.percent.support.PercentRelativeLayout;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class IssueBuyActivity2 extends BaseActivity {

    @Bind(R.id.tv_pay_way)
    TextView tvPayWay;
    @Bind(R.id.rl_pay_way)
    PercentRelativeLayout rlPayWay;
    @Bind(R.id.tv_contact_way)
    TextView tvContactWay;
    @Bind(R.id.rl_contact_way)
    PercentRelativeLayout rlContactWay;
    @Bind(R.id.tv_dealer_ad)
    TextView tvDealerAd;
    @Bind(R.id.rl_dealer_ad)
    PercentRelativeLayout rlDealerAd;
    @Bind(R.id.only_phone)
    TextView onlyPhone;
    @Bind(R.id.only_id_card)
    TextView onlyIdCard;
    @Bind(R.id.only_google)
    TextView onlyGoogle;
    @Bind(R.id.release)
    TextView release;
    /*支付方式*/
    private List<String> list;
    /*手机号*/
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue_buy2);
        setUltimateBar(R.color.blue1);
        initView();
    }

    private void initView() {
    }
    /**
     * @return
     */
    @Override
    public View getTitleBar() {
        NormalTitleBar normalTitleBar = getNormalTitleBar().setTitle(getString(R.string.issue_buy));
        return normalTitleBar;
    }

    @Override
    protected List<BasePresenter> getPresenter(List<BasePresenter> presenters) {
        return null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_FIRST_USER && resultCode == RESULT_OK) {//选择支付方式
            String chooseList = data.getStringExtra("chooseList");
//            list = SPUtils.getList(chooseList);
//            String s = SPUtils.getString(list);
//            tvPayWay.setText(s);
        } else if (requestCode == 2 && resultCode == RESULT_OK) {//添加手机号
            phone = data.getStringExtra("phone");
            tvContactWay.setText(phone.trim());
        } else if (requestCode == 3 && resultCode == RESULT_OK) {//添加广告
            String chooseName = data.getStringExtra("chooseName");
            tvDealerAd.setText(chooseName);
            setClickable(release,true);
        }
    }

    private boolean isPhone, isCard, isGoogle;

    @OnClick({R.id.rl_pay_way, R.id.rl_contact_way, R.id.rl_dealer_ad, R.id.only_phone, R.id.only_id_card, R.id.only_google, R.id.release})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_pay_way:
                startActivityForResult(new Intent(this, PayWayActivity.class), RESULT_FIRST_USER);
                break;
            case R.id.rl_contact_way:
                startActivityForResult(new Intent(this, InputPhoneActivity.class), 2);
                break;
            case R.id.rl_dealer_ad:
                startActivityForResult(new Intent(this, TraderAdActivity.class), 3);
                break;
            case R.id.only_phone:
                if (isPhone) {
                    setRightDrawable(onlyPhone, false);
                    isPhone = false;
                } else {
                    setRightDrawable(onlyPhone, true);
                    isPhone = true;
                }
                break;
            case R.id.only_id_card:
                if (isCard) {
                    setRightDrawable(onlyIdCard, false);
                    isCard = false;
                } else {
                    setRightDrawable(onlyIdCard, true);
                    isCard = true;
                }
                break;
            case R.id.only_google:
                if (isGoogle) {
                    setRightDrawable(onlyGoogle, false);
                    isGoogle = false;
                } else {
                    setRightDrawable(onlyGoogle, true);
                    isGoogle = true;
                }
                break;
            case R.id.release:
                showTip("发布成功");
                break;
        }
    }

    public void setRightDrawable(TextView tv, boolean b) {
        if (b)
            tv.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.release_slide_click, 0);
        else
            tv.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.release_slide, 0);
    }
}
