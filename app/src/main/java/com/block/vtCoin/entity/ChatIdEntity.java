package com.block.vtCoin.entity;

/**
 * Created by liubin on 2017/11/9.
 */

public class ChatIdEntity extends BaseEntity {


    /**
     * data : {"IsOnLine":false,"NickName":"羊","UserId":"VT8B22L64X","Logo":"http://1711255wc1.iok.la/Uploads/616fb15c2dcdce49/Logo/d729dd81f1664515b9c2649ca7b53047.png"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * IsOnLine : false
         * NickName : 羊
         * UserId : VT8B22L64X
         * Logo : http://1711255wc1.iok.la/Uploads/616fb15c2dcdce49/Logo/d729dd81f1664515b9c2649ca7b53047.png
         */

        private boolean IsOnLine;
        private String NickName;
        private String UserId;
        private String Logo;

        public boolean isIsOnLine() {
            return IsOnLine;
        }

        public void setIsOnLine(boolean IsOnLine) {
            this.IsOnLine = IsOnLine;
        }

        public String getNickName() {
            return NickName;
        }

        public void setNickName(String NickName) {
            this.NickName = NickName;
        }

        public String getUserId() {
            return UserId;
        }

        public void setUserId(String UserId) {
            this.UserId = UserId;
        }

        public String getLogo() {
            return Logo;
        }

        public void setLogo(String Logo) {
            this.Logo = Logo;
        }
    }
}
