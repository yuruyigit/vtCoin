package com.block.vtCoin.deal.order.apply_detail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.vtCoin.R;
import com.block.vtCoin.main.BaseActivity;
import com.block.vtCoin.photo_picker.widget.MyMultiPickResultView;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * @Description
 * @Author lijie
 * @Date 2017/8/26.
 */
public class ApplyActivity extends BaseActivity  {
    @Bind(R.id.order_detail)
    TextView orderDetail;
    @Bind(R.id.sell_question)
    TextView sellQuestion;
    @Bind(R.id.buy_question)
    TextView buyQuestion;
    @Bind(R.id.other_question)
    EditText otherQuestion;
    @Bind(R.id.confirm_provide)
    TextView confirmProvide;
    @Bind(R.id.pickPhoto)
    MyMultiPickResultView pickPhoto;
    private ArrayList<String> pics = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arbitrament);
        setUltimateBar(R.color.blue1);
        initView();
    }

    @Override
    protected List<BasePresenter> getPresenter(List<BasePresenter> presenters) {
        return null;
    }

    private void initView() {
        pickPhoto.init(this, MyMultiPickResultView.ACTION_SELECT, pics, 5, null);
    }

    @Override
    public View getTitleBar() {
        return getNormalTitleBar().setTitle(getString(R.string.apply_arbitrament));
    }


    private void getPic(Map<String, Object> map)
    {
        if (pics.size() == 0)
            return;
        showTip(pics.size()+"");
        for (String picAddress : pics)
        {
            map.put("pic", new File(picAddress));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (pickPhoto != null)
        {
            pickPhoto.onActivityResult(requestCode, resultCode, data);
        }

    }

    @OnClick({R.id.order_detail, R.id.sell_question, R.id.buy_question, R.id.confirm_provide})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.order_detail:
                finish();
                break;
            case R.id.sell_question:
                changeIcon(sellQuestion, R.mipmap.business_lected);
                changeIcon(buyQuestion, R.mipmap.business_chec);
                break;
            case R.id.buy_question:
                changeIcon(buyQuestion, R.mipmap.business_lected);
                changeIcon(sellQuestion, R.mipmap.business_chec);
                break;
            case R.id.confirm_provide:
                break;
        }
    }


    public void changeIcon(TextView textView, int rec) {
        textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, rec, 0);
    }

}
