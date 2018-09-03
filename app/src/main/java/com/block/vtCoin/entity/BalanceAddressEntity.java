package com.block.vtCoin.entity;

import java.util.List;

public class BalanceAddressEntity extends BaseEntity{

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
                 * Addr : mtsQfVRaLcWySoRUxiyXmFthWxc31sE89Q
                 * Balance : 1000
                 * Aval_Balance : 1000
                 */

                private String CoinType;
                private String Addr;
                private int Balance;
                private int Aval_Balance;

                public String getCoinType() {
                        return CoinType;
                }

                public void setCoinType(String CoinType) {
                        this.CoinType = CoinType;
                }

                public String getAddr() {
                        return Addr;
                }

                public void setAddr(String Addr) {
                        this.Addr = Addr;
                }

                public int getBalance() {
                        return Balance;
                }

                public void setBalance(int Balance) {
                        this.Balance = Balance;
                }

                public int getAval_Balance() {
                        return Aval_Balance;
                }

                public void setAval_Balance(int Aval_Balance) {
                        this.Aval_Balance = Aval_Balance;
                }
        }
}
