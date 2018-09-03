package com.block.vtCoin.login.phone_location;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.block.net.mvp.presenter.BasePresenter;
import com.block.vtCoin.R;
import com.block.vtCoin.main.BaseActivity;
import com.block.vtCoin.login.phone_location.adapter.CityAdapter;
import com.block.vtCoin.login.phone_location.decoration.StickyDecoration;
import com.block.vtCoin.login.phone_location.m.City;
import com.block.vtCoin.login.phone_location.m.Country;
import com.block.vtCoin.login.phone_location.weiget.CircleTextView;
import com.block.vtCoin.login.phone_location.weiget.MySlideView;
import com.block.vtCoin.util.AppUtil;
import com.block.vtCoin.util.FileUtils;
import com.github.promeg.pinyinhelper.Pinyin;
import com.google.gson.Gson;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class TelephoneLocationActivity extends BaseActivity implements MySlideView.onTouchListener, CityAdapter.onItemClickListener {
    private List<Country.DataBean> CountryList = null;
    private PinyinComparator pinyinComparator;
    /*中文*/
    public List<City> ChineseList = new ArrayList<>();
    public Set<String> cPinyinList = new LinkedHashSet<>();
    /*英文*/
    private List<City> UsaList = new ArrayList<>();
    public  Set<String> uPinyinList = new LinkedHashSet<>();
    public static List<String> pinyinList=new ArrayList<>();

    private MySlideView mySlideView;
    private CircleTextView circleTxt;

    private RecyclerView recyclerView;
    private CityAdapter adapter;
    private LinearLayoutManager layoutManager;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handIntent();
        setContentView(R.layout.activity_telphone_location);
        setUltimateBar(R.color.blue0);
        initData();
        initView();
    }

    private void handIntent() {
        int from = getIntent().getIntExtra("from", -1);
        switch (from){
            case 0://注册
                title=getString(R.string.telephone_area);
                break;
            case 1://地区
                title=getString(R.string.area_choose);
                break;
        }
    }

    @Override
    protected List<BasePresenter> getPresenter(List<BasePresenter> presenters) {
        return null;
    }

    @Override
    public View getTitleBar() {
        return getNormalTitleBar().setTitle(title);
    }


    private void initData() {
        InputStream inputStream = getResources().openRawResource(R.raw.chinese_city);
        String jsonString = FileUtils.getString(inputStream);
        Country country = new Gson().fromJson(jsonString, Country.class);
        CountryList = country.getData();
        ChineseList.clear();
        UsaList.clear();
        cPinyinList.clear();
        uPinyinList.clear();
        pinyinList.clear();
        for (int i = 0; i < CountryList.size(); i++) {
            String pinyin = transformPinYin(CountryList.get(i).getZh_CN());
            City cc = new City(pinyin, CountryList.get(i).getZh_CN(), pinyin.substring(0, 1), CountryList.get(i).getPhoneCode());
            City uc = new City(CountryList.get(i).getEn_US(), CountryList.get(i).getEn_US(), CountryList.get(i).getEn_US().substring(0, 1), CountryList.get(i).getPhoneCode());
            ChineseList.add(cc);
            UsaList.add(uc);
        }
        pinyinComparator = new PinyinComparator();
        /*中文*/
        Collections.sort(ChineseList, pinyinComparator);
        for (City city : ChineseList) {
            cPinyinList.add(city.getCityPinyin().substring(0, 1));
        }
        /*英文*/
        Collections.sort(UsaList, pinyinComparator);
        for (City city : UsaList) {
            uPinyinList.add(city.getCityPinyin().substring(0, 1));
        }
        if (AppUtil.isZh(this))
            for (String s : cPinyinList) {
                pinyinList.add(s);
            }
        else
            for (String s : uPinyinList) {
                pinyinList.add(s);
            }
    }

    private void initView() {
        mySlideView = (MySlideView) findViewById(R.id.my_slide_view);
        circleTxt = (CircleTextView) findViewById(R.id.my_circle_view);
        mySlideView.setListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.rv_sticky_example);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        if (AppUtil.isZh(this))
            adapter = new CityAdapter(getApplicationContext(), ChineseList);
        else
            adapter = new CityAdapter(getApplicationContext(), UsaList);
        adapter.setListener(this);
        recyclerView.setAdapter(adapter);
        if (AppUtil.isZh(this))
            recyclerView.addItemDecoration(new StickyDecoration(getApplicationContext(), ChineseList));
        else
            recyclerView.addItemDecoration(new StickyDecoration(getApplicationContext(), UsaList));
    }

    @Override
    public void itemClick(int position) {
        Intent intent = getIntent();
        if (AppUtil.isZh(this))
            intent.putExtra("city",ChineseList.get(position).getCityName());
        else
            intent.putExtra("city",UsaList.get(position).getCityName());
        intent.putExtra("areaCode",ChineseList.get(position).getNumber());
        this.setResult(RESULT_OK,intent);
        finish();
    }

    public String transformPinYin(String character) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < character.length(); i++) {
            buffer.append(Pinyin.toPinyin(character.charAt(i)));
        }
        return buffer.toString();
    }

    @Override
    public void showTextView(String textView, boolean dismiss) {

        if (dismiss) {
            circleTxt.setVisibility(View.GONE);
        } else {
            circleTxt.setVisibility(View.VISIBLE);
            circleTxt.setText(textView);
        }

        int selectPosition = 0;
        for (int i = 0; i < ChineseList.size(); i++) {
            if (ChineseList.get(i).getFirstPinYin().equals(textView)) {
                selectPosition = i;
                break;
            }
        }
        scrollPosition(selectPosition);
    }


    public class PinyinComparator implements Comparator<City> {
        @Override
        public int compare(City cityFirst, City citySecond) {
            return cityFirst.getCityPinyin().compareTo(citySecond.getCityPinyin());
        }
    }


    private void scrollPosition(int index) {
        int firstPosition = layoutManager.findFirstVisibleItemPosition();
        int lastPosition = layoutManager.findLastVisibleItemPosition();
        if (index <= firstPosition) {
            recyclerView.scrollToPosition(index);
        } else if (index <= lastPosition) {
            int top = recyclerView.getChildAt(index - firstPosition).getTop();
            recyclerView.scrollBy(0, top);
        } else {
            recyclerView.scrollToPosition(index);
        }
    }

}
