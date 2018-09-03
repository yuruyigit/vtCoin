package com.block.vtCoin.entity;

/**
 * @Description
 * @Author lijie
 * @Date 2017/7/13.
 */
public class UserInfoEntity extends BaseEntity{

        /**
         * data : {"Email":"2*******4@qq.com","Phone":"","AdvancedValid":true,"PassIdentityConfirm":false,"GoogleConfirm":false,"PhoneConfirm":false,"EmainConfirm":true,"TwoFactorGoogle":false,"TwoFactorSMS":false,"AgentQLN":false}
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
                 * Email : 2*******4@qq.com
                 * Phone :
                 * AdvancedValid : true
                 * PassIdentityConfirm : false
                 * GoogleConfirm : false
                 * PhoneConfirm : false
                 * EmainConfirm : true
                 * TwoFactorGoogle : false
                 * TwoFactorSMS : false
                 * AgentQLN : false
                 */

                private String Email;
                private String Phone;
                private boolean AdvancedValid;
                private boolean PassIdentityConfirm;
                private boolean GoogleConfirm;
                private boolean PhoneConfirm;
                private boolean EmainConfirm;
                private boolean TwoFactorGoogle;
                private boolean TwoFactorSMS;
                private boolean AgentQLN;

                public String getEmail() {
                        return Email;
                }

                public void setEmail(String Email) {
                        this.Email = Email;
                }

                public String getPhone() {
                        return Phone;
                }

                public void setPhone(String Phone) {
                        this.Phone = Phone;
                }

                public boolean isAdvancedValid() {
                        return AdvancedValid;
                }

                public void setAdvancedValid(boolean AdvancedValid) {
                        this.AdvancedValid = AdvancedValid;
                }

                public boolean isPassIdentityConfirm() {
                        return PassIdentityConfirm;
                }

                public void setPassIdentityConfirm(boolean PassIdentityConfirm) {
                        this.PassIdentityConfirm = PassIdentityConfirm;
                }

                public boolean isGoogleConfirm() {
                        return GoogleConfirm;
                }

                public void setGoogleConfirm(boolean GoogleConfirm) {
                        this.GoogleConfirm = GoogleConfirm;
                }

                public boolean isPhoneConfirm() {
                        return PhoneConfirm;
                }

                public void setPhoneConfirm(boolean PhoneConfirm) {
                        this.PhoneConfirm = PhoneConfirm;
                }

                public boolean isEmainConfirm() {
                        return EmainConfirm;
                }

                public void setEmainConfirm(boolean EmainConfirm) {
                        this.EmainConfirm = EmainConfirm;
                }

                public boolean isTwoFactorGoogle() {
                        return TwoFactorGoogle;
                }

                public void setTwoFactorGoogle(boolean TwoFactorGoogle) {
                        this.TwoFactorGoogle = TwoFactorGoogle;
                }

                public boolean isTwoFactorSMS() {
                        return TwoFactorSMS;
                }

                public void setTwoFactorSMS(boolean TwoFactorSMS) {
                        this.TwoFactorSMS = TwoFactorSMS;
                }

                public boolean isAgentQLN() {
                        return AgentQLN;
                }

                public void setAgentQLN(boolean AgentQLN) {
                        this.AgentQLN = AgentQLN;
                }
        }
}
