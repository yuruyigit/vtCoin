package com.block.vtCoin.login.phone_location.m;

import java.util.List;

/**
 * Created by liubin on 2017/8/3.
 */

public class Country {
    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /*中文名*/
        private String zh_CN;
        /*英文名*/
        private String en_US;
        /*值*/
        private String phoneCode;

        public String getZh_CN() {
            return zh_CN;
        }

        public void setZh_CN(String zh_CN) {
            this.zh_CN = zh_CN;
        }

        public String getEn_US() {
            return en_US;
        }

        public void setEn_US(String en_US) {
            this.en_US = en_US;
        }

        public String getPhoneCode() {
            return phoneCode;
        }

        public void setPhoneCode(String phoneCode) {
            this.phoneCode = phoneCode;
        }

        @Override
        public String toString() {
            return "zh_CN="+zh_CN+"  en_US="+en_US+"  phoneCode="+phoneCode;
        }
    }
}
