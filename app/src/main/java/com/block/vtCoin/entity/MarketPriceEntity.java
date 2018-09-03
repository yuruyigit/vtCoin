package com.block.vtCoin.entity;

/**
 * Created by liubin on 2017/11/9.
 */

public class MarketPriceEntity extends BaseEntity {

    /**
     * type : sc_usd
     * data : {"buy":1,"sell":1}
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
         * buy : 1
         * sell : 1
         */

        private int buy;
        private int sell;

        public int getBuy() {
            return buy;
        }

        public void setBuy(int buy) {
            this.buy = buy;
        }

        public int getSell() {
            return sell;
        }

        public void setSell(int sell) {
            this.sell = sell;
        }
    }
}
