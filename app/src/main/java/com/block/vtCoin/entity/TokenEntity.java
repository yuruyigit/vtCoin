package com.block.vtCoin.entity;

/**
 * @Description
 * @Author lijie
 * @Date 2017/7/13.
 */
public class TokenEntity extends BaseEntity{

        /**
         * data : {"NickName":"VT8JFFT8H0","ChatToken":"VT8JFFT8H0","DecChatToken":"WoWRetFFtv+njwfwXtSjSwLP64wvriEwQaI2NHNexaOdQGGwSRnKjt8ZHpBoaJ/f5hqpDSVwwQGWiQOiOgU+lX/Q+geBpXjW","LogoUrl":"DEFAULT"}
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
                 * NickName : VT8JFFT8H0
                 * ChatToken : VT8JFFT8H0
                 * DecChatToken : WoWRetFFtv+njwfwXtSjSwLP64wvriEwQaI2NHNexaOdQGGwSRnKjt8ZHpBoaJ/f5hqpDSVwwQGWiQOiOgU+lX/Q+geBpXjW
                 * LogoUrl : DEFAULT
                 */

                private String NickName;
                private String ChatToken;
                private String DecChatToken;
                private String LogoUrl;

                public String getNickName() {
                        return NickName;
                }

                public void setNickName(String NickName) {
                        this.NickName = NickName;
                }

                public String getChatToken() {
                        return ChatToken;
                }

                public void setChatToken(String ChatToken) {
                        this.ChatToken = ChatToken;
                }

                public String getDecChatToken() {
                        return DecChatToken;
                }

                public void setDecChatToken(String DecChatToken) {
                        this.DecChatToken = DecChatToken;
                }

                public String getLogoUrl() {
                        return LogoUrl;
                }

                public void setLogoUrl(String LogoUrl) {
                        this.LogoUrl = LogoUrl;
                }
        }
}
