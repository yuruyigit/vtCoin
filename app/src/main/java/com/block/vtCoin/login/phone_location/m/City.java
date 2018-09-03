package com.block.vtCoin.login.phone_location.m;

/**
 * Created by liubin on 2017/8/3.
 */

public class City {
    private String cityPinyin;
    private String cityName;
    private String firstPinYin;
    private String number;

    public City(String cityPinyin, String cityName, String firstPinYin, String number) {
        this.cityPinyin = cityPinyin;
        this.cityName = cityName;
        this.firstPinYin = firstPinYin;
        this.number=number;
    }

    public String getCityPinyin() {
        return cityPinyin;
    }

    public void setCityPinyin(String cityPinyin) {
        this.cityPinyin = cityPinyin;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getFirstPinYin() {
        firstPinYin = cityPinyin.substring(0, 1);
        return firstPinYin;
    }

    public void setFirstPinYin(String firstPinYin) {
        this.firstPinYin = firstPinYin;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "cityPinyin="+cityPinyin+"  cityName="+cityName+"  firstPinYin="+firstPinYin;
    }
}
