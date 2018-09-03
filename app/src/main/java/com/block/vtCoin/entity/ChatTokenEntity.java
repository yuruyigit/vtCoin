package com.block.vtCoin.entity;

import java.util.List;

/**
 * Created by liubin on 2017/11/9.
 */

public class ChatTokenEntity extends BaseEntity {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * RonyYuAppId : VT8B22L64X
         * IsOnline : false
         * UserName : 杨羊杨羊
         * LogoUrl : http://1711255wc1.iok.la/Uploads/616fb15c2dcdce49/Logo/d729dd81f1664515b9c2649ca7b53047.png
         */

        private String RonyYuAppId;
        private boolean IsOnline;
        private String UserName;
        private String LogoUrl;

        public String getRonyYuAppId() {
            return RonyYuAppId;
        }

        public void setRonyYuAppId(String RonyYuAppId) {
            this.RonyYuAppId = RonyYuAppId;
        }

        public boolean isIsOnline() {
            return IsOnline;
        }

        public void setIsOnline(boolean IsOnline) {
            this.IsOnline = IsOnline;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public String getLogoUrl() {
            return LogoUrl;
        }

        public void setLogoUrl(String LogoUrl) {
            this.LogoUrl = LogoUrl;
        }
    }
}
