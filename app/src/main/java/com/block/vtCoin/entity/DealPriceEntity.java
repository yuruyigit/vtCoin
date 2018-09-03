package com.block.vtCoin.entity;

public class DealPriceEntity {
    /**
     * type : btc_cny
     * data : {"buy":20267.64,"sell":20312.72}
     */

    private String type;
    private DataBean data;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * buy : 20267.64
         * sell : 20312.72
         */

        private double buy;
        private double sell;

        public double getBuy() {
            return buy;
        }

        public void setBuy(double buy) {
            this.buy = buy;
        }

        public double getSell() {
            return sell;
        }

        public void setSell(double sell) {
            this.sell = sell;
        }
    }
}
