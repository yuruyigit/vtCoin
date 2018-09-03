package com.example;

import java.util.List;

public class WalletEntity extends BaseEntity{

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * CoinType : BTC
         * Banlance : 1000
         * FreezeBanlance : 0
         * AvalBanlance : 1000
         */

        private String CoinType;
        private int Banlance;
        private int FreezeBanlance;
        private int AvalBanlance;

        public String getCoinType() {
            return CoinType;
        }

        public void setCoinType(String CoinType) {
            this.CoinType = CoinType;
        }

        public int getBanlance() {
            return Banlance;
        }

        public void setBanlance(int Banlance) {
            this.Banlance = Banlance;
        }

        public int getFreezeBanlance() {
            return FreezeBanlance;
        }

        public void setFreezeBanlance(int FreezeBanlance) {
            this.FreezeBanlance = FreezeBanlance;
        }

        public int getAvalBanlance() {
            return AvalBanlance;
        }

        public void setAvalBanlance(int AvalBanlance) {
            this.AvalBanlance = AvalBanlance;
        }
    }
}
