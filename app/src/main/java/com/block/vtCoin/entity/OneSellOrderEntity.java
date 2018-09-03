package com.block.vtCoin.entity;

/**
 * Created by liubin on 2017/11/9.
 */

public class OneSellOrderEntity extends BaseEntity {

    /**
     * data : {"CoinType":"BTC","NickName":"杨羊杨羊","Country":"South Korea","Last_Attp_Login":"2017-11-13T11:45:01","Tx_ID":"01709220T6N08","Fee_Rate":0.055,"Payment":"支付宝|W|","Plan_Sell_Amt":222,"Curt":"CNY","Price":25046.72335,"Min_Per_Unit":100,"Max_Per_Unit":1000000,"ID_TYPE":"N","PHE_TYPE":"N","GOLE_TYPE":"N","TX_PROVI":"170705V0BR8FHJ|W|","DealSum":34,"ScroeAve":4.75}
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
         * CoinType : BTC
         * NickName : 杨羊杨羊
         * Country : South Korea
         * Last_Attp_Login : 2017-11-13T11:45:01
         * Tx_ID : 01709220T6N08
         * Fee_Rate : 0.055
         * Payment : 支付宝|W|
         * Plan_Sell_Amt : 222
         * Curt : CNY
         * Price : 25046.72335
         * Min_Per_Unit : 100
         * Max_Per_Unit : 1000000
         * ID_TYPE : N
         * PHE_TYPE : N
         * GOLE_TYPE : N
         * TX_PROVI : 170705V0BR8FHJ|W|
         * DealSum : 34
         * ScroeAve : 4.75
         */

        private String CoinType;
        private String NickName;
        private String Country;
        private String Last_Attp_Login;
        private String Tx_ID;
        private double Fee_Rate;
        private String Payment;
        private int Plan_Sell_Amt;
        private String Curt;
        private double Price;
        private int Min_Per_Unit;
        private int Max_Per_Unit;
        private String ID_TYPE;
        private String PHE_TYPE;
        private String GOLE_TYPE;
        private String TX_PROVI;
        private int DealSum;
        private double ScroeAve;

        public String getCoinType() {
            return CoinType;
        }

        public void setCoinType(String CoinType) {
            this.CoinType = CoinType;
        }

        public String getNickName() {
            return NickName;
        }

        public void setNickName(String NickName) {
            this.NickName = NickName;
        }

        public String getCountry() {
            return Country;
        }

        public void setCountry(String Country) {
            this.Country = Country;
        }

        public String getLast_Attp_Login() {
            return Last_Attp_Login;
        }

        public void setLast_Attp_Login(String Last_Attp_Login) {
            this.Last_Attp_Login = Last_Attp_Login;
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

        public int getPlan_Sell_Amt() {
            return Plan_Sell_Amt;
        }

        public void setPlan_Sell_Amt(int Plan_Sell_Amt) {
            this.Plan_Sell_Amt = Plan_Sell_Amt;
        }

        public String getCurt() {
            return Curt;
        }

        public void setCurt(String Curt) {
            this.Curt = Curt;
        }

        public double getPrice() {
            return Price;
        }

        public void setPrice(double Price) {
            this.Price = Price;
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

        public String getID_TYPE() {
            return ID_TYPE;
        }

        public void setID_TYPE(String ID_TYPE) {
            this.ID_TYPE = ID_TYPE;
        }

        public String getPHE_TYPE() {
            return PHE_TYPE;
        }

        public void setPHE_TYPE(String PHE_TYPE) {
            this.PHE_TYPE = PHE_TYPE;
        }

        public String getGOLE_TYPE() {
            return GOLE_TYPE;
        }

        public void setGOLE_TYPE(String GOLE_TYPE) {
            this.GOLE_TYPE = GOLE_TYPE;
        }

        public String getTX_PROVI() {
            return TX_PROVI;
        }

        public void setTX_PROVI(String TX_PROVI) {
            this.TX_PROVI = TX_PROVI;
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
    }
}
