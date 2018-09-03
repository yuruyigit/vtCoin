package com.block.vtCoin.mine.bill.turn_in;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.bigkoo.pickerview.TimePickerView;
import com.block.net.mvp.presenter.BasePresenter;
import com.block.vtCoin.R;
import com.block.vtCoin.main.BaseActivity;
import com.block.vtCoin.widget.dialog.ListViewDialog;
import com.block.vtCoin.widget.base.OnMyItemClickListener;
import com.block.vtCoin.widget.title.NormalTitleBar;
import com.zhy.android.percent.support.PercentRelativeLayout;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class SearchBillActivity extends BaseActivity {
    @Bind(R.id.tv_bill_type)
    TextView tvBillType;
    @Bind(R.id.rl_bill_type)
    PercentRelativeLayout rlBillType;
    @Bind(R.id.tv_coin_type)
    TextView tvCoinType;
    @Bind(R.id.rl_coin_type)
    PercentRelativeLayout rlCoinType;
    @Bind(R.id.tv_deal_type)
    TextView tvDealType;
    @Bind(R.id.rl_deal_type)
    PercentRelativeLayout rlDealType;
    @Bind(R.id.tv_start_time)
    TextView tvStartTime;
    @Bind(R.id.tv_end_time)
    TextView tvEndTime;
    @Bind(R.id.tv_search)
    TextView tvSearch;

    private String billType, coinType, dealType;
    private ListViewDialog billOrDealDialog, coinDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_bill);
        setUltimateBar(R.color.blue1);
        initView();
    }

    private void initView() {
        createPopup();
    }

    private void createPopup() {
        final String[] typeItem = new String[]{"全部", "转入", "转出", "手续费", "买入交易流水", "卖出交易流水", "币转币"};
        billOrDealDialog = new ListViewDialog(this, typeItem, false);
        billOrDealDialog.setOnItemClickListener(new OnMyItemClickListener() {
            @Override
            public void onItemClick(View parent, View view, int position) {
                if (isBillType) {
                    billType = typeItem[position];
                    tvBillType.setText(billType);
                } else {
                    dealType = typeItem[position];
                    tvDealType.setText(dealType);
                }
            }
        });
        final String[] currencyItem = new String[]{"全部", "BTC比特币", "LTC莱特币", "ETH以太坊", "VTC微特币", "ETC以太币", "SC区块盾"};
        coinDialog = new ListViewDialog(this, currencyItem, false);
        coinDialog.setOnItemClickListener(new OnMyItemClickListener() {
            @Override
            public void onItemClick(View parent, View view, int position) {
                coinType = currencyItem[position];
                tvCoinType.setText(coinType);
            }
        });
    }

    @Override
    public View getTitleBar() {
        NormalTitleBar normalTitleBar = getNormalTitleBar().setTitle("搜索");
        return normalTitleBar;
    }
    @Override
    protected List<BasePresenter> getPresenter(List<BasePresenter> presenters) {
        return null;
    }

    private boolean isBillType = true;

    @OnClick({R.id.rl_bill_type, R.id.rl_coin_type, R.id.rl_deal_type, R.id.tv_start_time, R.id.tv_end_time, R.id.tv_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_bill_type:
                billOrDealDialog.show();
                isBillType = true;
                break;
            case R.id.rl_coin_type:
                coinDialog.show();
                break;
            case R.id.rl_deal_type:
                billOrDealDialog.show();
                isBillType = false;
                break;
            case R.id.tv_start_time:
                showTimePicker("请选择起始时间", 0);
                break;
            case R.id.tv_end_time:
                showTimePicker("请选择起始时间", 1);
                break;
            case R.id.tv_search:
                toActivity(SearchResultActivity.class);
                break;
        }
    }

    public void showTimePicker(String title, final int type) {
        TimePickerView pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date2, View v) {//选中事件回调
                String time = getTime(date2);
                if (type == 0) {
                    tvStartTime.setText(time);
                } else {
                    tvEndTime.setText(time);
                }
            }
        })
                .setType(TimePickerView.Type.YEAR_MONTH_DAY)//默认全部显示
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确定")//确认按钮文字
                .setContentSize(23)//滚轮文字大小
                .setTitleSize(16)//标题文字大小
                .setTitleText(title)//标题文字
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(true)//是否循环滚动
                .setTextColorCenter(getResources().getColor(R.color.black0))//设置选中项的颜色
                .setTitleColor(getResources().getColor(R.color.black0))//标题文字颜色
                .setSubmitColor(getResources().getColor(R.color.blue4))//确定按钮文字颜色
                .setCancelColor(getResources().getColor(R.color.blue4))//取消按钮文字颜色
                .setTitleBgColor(getResources().getColor(R.color.gray_f6))//标题背景颜色 Night mode
                .setBgColor(getResources().getColor(R.color.white0))//滚轮背景颜色 Night mode
//              .setRange(calendar.get(Calendar.YEAR) - 20, calendar.get(Calendar.YEAR) + 20)//默认是1900-2100年
//              .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
//              .setRangDate(startDate,endDate)//起始终止年月日设定
//              .setLabel("年","月","日","时","分","秒")
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
//              .isDialog(true)//是否显示为对话框样式
                .build();
        pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
        pvTime.show();
    }

    public String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        return format.format(date);
    }

    @Override
    protected void onDestroy() {
        if (null != coinDialog)
            coinDialog.unbind();
        if (null != billOrDealDialog)
            billOrDealDialog.unbind();
        super.onDestroy();
    }
}
