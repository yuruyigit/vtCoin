package com.block.vtCoin.entity;

import java.util.List;

/**
 * Created by liubin on 2017/11/8.
 */

public class QueryOrder extends BaseEntity{

    /**
     * data : {"Rows":29,"Orders":[{"NickName":"杨羊杨羊","Tx_ID":"01709220T6N08","Fee_Rate":0.055,"Payment":"支付宝|W|","Curt":"CNY","Min_Per_Unit":100,"Max_Per_Unit":1000000,"DealSum":34,"ScroeAve":4.75},{"NickName":"杨羊杨羊","Tx_ID":"0170706040HJ0","Fee_Rate":0.055,"Payment":"微信|W|","Curt":"CNY","Min_Per_Unit":100,"Max_Per_Unit":1000000,"DealSum":34,"ScroeAve":4.75},{"NickName":"杨羊杨羊","Tx_ID":"01705270L6HJP","Fee_Rate":0.066,"Payment":"支付宝|W|","Curt":"CNY","Min_Per_Unit":444,"Max_Per_Unit":555,"DealSum":34,"ScroeAve":4.75},{"NickName":"马云爸爸背后的女人♛��","Tx_ID":"0170518062R64","Fee_Rate":0.001,"Payment":"微信|W|","Curt":"CNY","Min_Per_Unit":100,"Max_Per_Unit":100000,"DealSum":44,"ScroeAve":1},{"NickName":"马云爸爸背后的女人♛��","Tx_ID":"017051604B0L6","Fee_Rate":0.001,"Payment":"微信|W|","Curt":"USD","Min_Per_Unit":100,"Max_Per_Unit":100000,"DealSum":44,"ScroeAve":1},{"NickName":"杨羊杨羊","Tx_ID":"01705160N6V2D","Fee_Rate":0.001,"Payment":"支付宝|W|国内银行转账|W|微信|W|","Curt":"CNY","Min_Per_Unit":100,"Max_Per_Unit":100000,"DealSum":34,"ScroeAve":4.75},{"NickName":"杨羊杨羊","Tx_ID":"017051600HBPN","Fee_Rate":0.056,"Payment":"支付宝|W|国内银行转账|W|微信|W|","Curt":"CNY","Min_Per_Unit":100,"Max_Per_Unit":100000,"DealSum":34,"ScroeAve":4.75},{"NickName":"杨羊 杨羊","Tx_ID":"01705150PN806","Fee_Rate":0.01,"Payment":"微信|W|","Curt":"CNY","Min_Per_Unit":100,"Max_Per_Unit":100000,"DealSum":0,"ScroeAve":0},{"NickName":"杨羊杨羊","Tx_ID":"01705130XN2D8","Fee_Rate":0.055,"Payment":"国内银行转账|W|","Curt":"CNY","Min_Per_Unit":100,"Max_Per_Unit":100000,"DealSum":34,"ScroeAve":4.75},{"NickName":"杨羊杨羊","Tx_ID":"0170511024VF0","Fee_Rate":-0.5,"Payment":"支付宝|W|","Curt":"CNY","Min_Per_Unit":100,"Max_Per_Unit":100000,"DealSum":34,"ScroeAve":4.75}]}
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
         * Rows : 29
         * Orders : [{"NickName":"杨羊杨羊","Tx_ID":"01709220T6N08","Fee_Rate":0.055,"Payment":"支付宝|W|","Curt":"CNY","Min_Per_Unit":100,"Max_Per_Unit":1000000,"DealSum":34,"ScroeAve":4.75},{"NickName":"杨羊杨羊","Tx_ID":"0170706040HJ0","Fee_Rate":0.055,"Payment":"微信|W|","Curt":"CNY","Min_Per_Unit":100,"Max_Per_Unit":1000000,"DealSum":34,"ScroeAve":4.75},{"NickName":"杨羊杨羊","Tx_ID":"01705270L6HJP","Fee_Rate":0.066,"Payment":"支付宝|W|","Curt":"CNY","Min_Per_Unit":444,"Max_Per_Unit":555,"DealSum":34,"ScroeAve":4.75},{"NickName":"马云爸爸背后的女人♛��","Tx_ID":"0170518062R64","Fee_Rate":0.001,"Payment":"微信|W|","Curt":"CNY","Min_Per_Unit":100,"Max_Per_Unit":100000,"DealSum":44,"ScroeAve":1},{"NickName":"马云爸爸背后的女人♛��","Tx_ID":"017051604B0L6","Fee_Rate":0.001,"Payment":"微信|W|","Curt":"USD","Min_Per_Unit":100,"Max_Per_Unit":100000,"DealSum":44,"ScroeAve":1},{"NickName":"杨羊杨羊","Tx_ID":"01705160N6V2D","Fee_Rate":0.001,"Payment":"支付宝|W|国内银行转账|W|微信|W|","Curt":"CNY","Min_Per_Unit":100,"Max_Per_Unit":100000,"DealSum":34,"ScroeAve":4.75},{"NickName":"杨羊杨羊","Tx_ID":"017051600HBPN","Fee_Rate":0.056,"Payment":"支付宝|W|国内银行转账|W|微信|W|","Curt":"CNY","Min_Per_Unit":100,"Max_Per_Unit":100000,"DealSum":34,"ScroeAve":4.75},{"NickName":"杨羊 杨羊","Tx_ID":"01705150PN806","Fee_Rate":0.01,"Payment":"微信|W|","Curt":"CNY","Min_Per_Unit":100,"Max_Per_Unit":100000,"DealSum":0,"ScroeAve":0},{"NickName":"杨羊杨羊","Tx_ID":"01705130XN2D8","Fee_Rate":0.055,"Payment":"国内银行转账|W|","Curt":"CNY","Min_Per_Unit":100,"Max_Per_Unit":100000,"DealSum":34,"ScroeAve":4.75},{"NickName":"杨羊杨羊","Tx_ID":"0170511024VF0","Fee_Rate":-0.5,"Payment":"支付宝|W|","Curt":"CNY","Min_Per_Unit":100,"Max_Per_Unit":100000,"DealSum":34,"ScroeAve":4.75}]
         */

        private int Rows;
        private List<OrdersBean> Orders;

        public int getRows() {
            return Rows;
        }

        public void setRows(int Rows) {
            this.Rows = Rows;
        }

        public List<OrdersBean> getOrders() {
            return Orders;
        }

        public void setOrders(List<OrdersBean> Orders) {
            this.Orders = Orders;
        }

        public static class OrdersBean {
            /**
             * NickName : 杨羊杨羊
             * Tx_ID : 01709220T6N08
             * Fee_Rate : 0.055
             * Payment : 支付宝|W|
             * Curt : CNY
             * Min_Per_Unit : 100
             * Max_Per_Unit : 1000000
             * DealSum : 34
             * ScroeAve : 4.75
             */

            private String NickName;
            private String Tx_ID;
            private double Fee_Rate;
            private String Payment;
            private String Curt;
            private int Min_Per_Unit;
            private int Max_Per_Unit;
            private int DealSum;
            private double ScroeAve;
            /*增加的字段*/
            private boolean isOnline;
            private double beforeBuyPrice;
            private double nowBuyPrice;

            public String getNickName() {
                return NickName;
            }

            public void setNickName(String NickName) {
                this.NickName = NickName;
            }

            public String getTx_ID() {
                return Tx_ID;
            }

            public void setTx_ID(String Tx_ID) {
                this.Tx_ID = Tx_ID;
            }

            public double getFee_Rate() {
                return Fee_Rate;
            }

            public void setFee_Rate(double Fee_Rate) {
                this.Fee_Rate = Fee_Rate;
            }

            public String getPayment() {
                return Payment;
            }

            public void setPayment(String Payment) {
                this.Payment = Payment;
            }

            public String getCurt() {
                return Curt;
            }

            public void setCurt(String Curt) {
                this.Curt = Curt;
            }

            public int getMin_Per_Unit() {
                return Min_Per_Unit;
            }

            public void setMin_Per_Unit(int Min_Per_Unit) {
                this.Min_Per_Unit = Min_Per_Unit;
            }

            public int getMax_Per_Unit() {
                return Max_Per_Unit;
            }

            public void setMax_Per_Unit(int Max_Per_Unit) {
                this.Max_Per_Unit = Max_Per_Unit;
            }

            public int getDealSum() {
                return DealSum;
            }

            public void setDealSum(int DealSum) {
                this.DealSum = DealSum;
            }

            public double getScroeAve() {
                return ScroeAve;
            }

            public void setScroeAve(double ScroeAve) {
                this.ScroeAve = ScroeAve;
            }

             /*增加的字段*/
            public boolean isOnline() {
                return isOnline;
            }

            public void setOnline(boolean online) {
                isOnline = online;
            }

            public double getBeforeBuyPrice() {
                return beforeBuyPrice;
            }

            public void setBeforeBuyPrice(double beforeBuyPrice) {
                this.beforeBuyPrice = beforeBuyPrice;
            }

            public double getNowBuyPrice() {
                return nowBuyPrice;
            }

            public void setNowBuyPrice(double nowBuyPrice) {
                this.nowBuyPrice = nowBuyPrice;
            }
        }
    }
}

