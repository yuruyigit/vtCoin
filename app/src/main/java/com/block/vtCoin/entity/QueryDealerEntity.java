package com.block.vtCoin.entity;

/**
 * @Description
 * @Author lijie
 * @Date 2017/7/14.
 */
public class QueryDealerEntity extends BaseEntity {

    /**
     * data : {"PhoneQLN":false,"IdentityQLN":false,"CapitalQLN":true,"PaymentQLN":false,"CommicationQLN":false}
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
         * PhoneQLN : false
         * IdentityQLN : false
         * CapitalQLN : true
         * PaymentQLN : false
         * CommicationQLN : false
         */

        private boolean PhoneQLN;
        private boolean IdentityQLN;
        private boolean CapitalQLN;
        private boolean PaymentQLN;
        private boolean CommicationQLN;

        public boolean isPhoneQLN() {
            return PhoneQLN;
        }

        public void setPhoneQLN(boolean PhoneQLN) {
            this.PhoneQLN = PhoneQLN;
        }

        public boolean isIdentityQLN() {
            return IdentityQLN;
        }

        public void setIdentityQLN(boolean IdentityQLN) {
            this.IdentityQLN = IdentityQLN;
        }

        public boolean isCapitalQLN() {
            return CapitalQLN;
        }

        public void setCapitalQLN(boolean CapitalQLN) {
            this.CapitalQLN = CapitalQLN;
        }

        public boolean isPaymentQLN() {
            return PaymentQLN;
        }

        public void setPaymentQLN(boolean PaymentQLN) {
            this.PaymentQLN = PaymentQLN;
        }

        public boolean isCommicationQLN() {
            return CommicationQLN;
        }

        public void setCommicationQLN(boolean CommicationQLN) {
            this.CommicationQLN = CommicationQLN;
        }
    }
}
