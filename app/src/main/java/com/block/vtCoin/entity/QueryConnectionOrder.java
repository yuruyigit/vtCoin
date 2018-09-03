package com.block.vtCoin.entity;

import java.util.List;

/**
 * Created by liubin on 2017/11/8.
 */

public class QueryConnectionOrder extends BaseEntity{

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * Tx_ID : 01709220T6N08
         * Order_Status : 0
         * Fee_Rate : 0.055
         * Payment : 支付宝|W|
         * Price : 25046.72335
         * Setup_Time : 2017-09-22T14:52:26.563
         * Plan_Sell : 222
         * CMP_AMT : 0
         * CoinType : BTC
         * TX_Cury : CNY
         */

        private String Tx_ID;
        private String Order_Status;
        private double Fee_Rate;
        private String Payment;
        private double Price;
        private String Setup_Time;
        private int Plan_Sell;
        private int CMP_AMT;
        private String CoinType;
        private String TX_Cury;

        public String getTx_ID() {
            return Tx_ID;
        }

        public void setTx_ID(String Tx_ID) {
            this.Tx_ID = Tx_ID;
        }

        public String getOrder_Status() {
            return Order_Status;
        }

        public void setOrder_Status(String Order_Status) {
            this.Order_Status = Order_Status;
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

        public double getPrice() {
            return Price;
        }

        public void setPrice(double Price) {
            this.Price = Price;
        }

        public String getSetup_Time() {
            return Setup_Time;
        }

        public void setSetup_Time(String Setup_Time) {
            this.Setup_Time = Setup_Time;
        }

        public int getPlan_Sell() {
            return Plan_Sell;
        }

        public void setPlan_Sell(int Plan_Sell) {
            this.Plan_Sell = Plan_Sell;
        }

        public int getCMP_AMT() {
            return CMP_AMT;
        }

        public void setCMP_AMT(int CMP_AMT) {
            this.CMP_AMT = CMP_AMT;
        }

        public String getCoinType() {
            return CoinType;
        }

        public void setCoinType(String CoinType) {
            this.CoinType = CoinType;
        }

        public String getTX_Cury() {
            return TX_Cury;
        }

        public void setTX_Cury(String TX_Cury) {
            this.TX_Cury = TX_Cury;
        }
    }
}

