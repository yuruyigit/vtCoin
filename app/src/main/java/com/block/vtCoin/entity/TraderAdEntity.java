package com.block.vtCoin.entity;

/**
 * Created by liubin on 2017/11/9.
 */

public class TraderAdEntity extends BaseEntity {

    /**
     * data : {"ExFee":0.003,"OutFee":0}
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
         * ExFee : 0.003
         * OutFee : 0
         */
        private double ExFee;
        private int OutFee;

        public double getExFee() {
            return ExFee;
        }

        public void setExFee(double ExFee) {
            this.ExFee = ExFee;
        }

        public int getOutFee() {
            return OutFee;
        }

        public void setOutFee(int OutFee) {
            this.OutFee = OutFee;
        }
    }
}
