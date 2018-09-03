package com.block.vtCoin.entity;

import com.google.gson.Gson;

public class MarketEntity {

        /**
         * data : {"vol":11416.11490153,"high":39337.6298,"low":37641.71404,"sell":38195.85206,"buy":38171.90332}
         * type : btc_cny
         * ProvierName : BitStamp
         */

        private DataBean data;
        private String type;
        private String ProvierName;

        public DataBean getData() {
                return data;
        }

        public void setData(DataBean data) {
                this.data = data;
        }

        public String getType() {
                return type;
        }

        public void setType(String type) {
                this.type = type;
        }

        public String getProvierName() {
                return ProvierName;
        }

        public void setProvierName(String ProvierName) {
                this.ProvierName = ProvierName;
        }

        public static class DataBean {
                /**
                 * vol : 11416.11490153
                 * high : 39337.6298
                 * low : 37641.71404
                 * sell : 38195.85206
                 * buy : 38171.90332
                 */

                private double vol;
                private double high;
                private double low;
                private double sell;
                private double buy;
                private double beforeBuy;

                public double getVol() {
                        return vol;
                }

                public void setVol(double vol) {
                        this.vol = vol;
                }

                public double getHigh() {
                        return high;
                }

                public void setHigh(double high) {
                        this.high = high;
                }

                public double getLow() {
                        return low;
                }

                public void setLow(double low) {
                        this.low = low;
                }

                public double getSell() {
                        return sell;
                }

                public void setSell(double sell) {
                        this.sell = sell;
                }

                public double getBuy() {
                        return buy;
                }

                public void setBuy(double buy) {
                        this.buy = buy;
                }

                public double getBeforeBuy() {
                        return beforeBuy;
                }

                public void setBeforeBuy(double beforeBuy) {
                        this.beforeBuy = beforeBuy;
                }
        }

        @Override
        public String toString() {
//                return super.toString();
                return new Gson().toJson(this);
        }
}
