package com.block.vtCoin.entity;

/**
 * @Description
 * @Author lijie
 * @Date 2017/7/13.
 */
public class PersonInfoEntity extends BaseEntity{

        /**
         * data : {"Logo":"https://www.vtcoin.cn/VTO2O/img/myimg.png","NickName":"VT8H02LX6P","UserName":"VT8H02LX6P","Comment":"","Country":"","City":"","State":"","Region":"","Addr":"","Lang":"中文","SetupTime":"2017-05-24T12:33:58.19Z","IsOnline":true,"LastOnlineTime":"2017-10-30T09:37:59.155Z"}
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
                 * Logo : https://www.vtcoin.cn/VTO2O/img/myimg.png
                 * NickName : VT8H02LX6P
                 * UserName : VT8H02LX6P
                 * Comment :
                 * Country :
                 * City :
                 * State :
                 * Region :
                 * Addr :
                 * Lang : 中文
                 * SetupTime : 2017-05-24T12:33:58.19Z
                 * IsOnline : true
                 * LastOnlineTime : 2017-10-30T09:37:59.155Z
                 */

                private String Logo;
                private String NickName;
                private String UserName;
                private String Comment;
                private String Country;
                private String City;
                private String State;
                private String Region;
                private String Addr;
                private String Lang;
                private String SetupTime;
                private boolean IsOnline;
                private String LastOnlineTime;

                public String getLogo() {
                        return Logo;
                }

                public void setLogo(String Logo) {
                        this.Logo = Logo;
                }

                public String getNickName() {
                        return NickName;
                }

                public void setNickName(String NickName) {
                        this.NickName = NickName;
                }

                public String getUserName() {
                        return UserName;
                }

                public void setUserName(String UserName) {
                        this.UserName = UserName;
                }

                public String getComment() {
                        return Comment;
                }

                public void setComment(String Comment) {
                        this.Comment = Comment;
                }

                public String getCountry() {
                        return Country;
                }

                public void setCountry(String Country) {
                        this.Country = Country;
                }

                public String getCity() {
                        return City;
                }

                public void setCity(String City) {
                        this.City = City;
                }

                public String getState() {
                        return State;
                }

                public void setState(String State) {
                        this.State = State;
                }

                public String getRegion() {
                        return Region;
                }

                public void setRegion(String Region) {
                        this.Region = Region;
                }

                public String getAddr() {
                        return Addr;
                }

                public void setAddr(String Addr) {
                        this.Addr = Addr;
                }

                public String getLang() {
                        return Lang;
                }

                public void setLang(String Lang) {
                        this.Lang = Lang;
                }

                public String getSetupTime() {
                        return SetupTime;
                }

                public void setSetupTime(String SetupTime) {
                        this.SetupTime = SetupTime;
                }

                public boolean isIsOnline() {
                        return IsOnline;
                }

                public void setIsOnline(boolean IsOnline) {
                        this.IsOnline = IsOnline;
                }

                public String getLastOnlineTime() {
                        return LastOnlineTime;
                }

                public void setLastOnlineTime(String LastOnlineTime) {
                        this.LastOnlineTime = LastOnlineTime;
                }
        }
}
