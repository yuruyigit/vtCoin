package com.block.vtCoin.entity;


/**
 * Created by liubin on 2017/9/5.
 */

public class AttentionEntity extends BaseEntity{

    /**
     * data : {"Total":0,"Data":null}
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
         * Total : 0
         * Data : null
         */

        private int Total;
        private Object Data;

        public int getTotal() {
            return Total;
        }

        public void setTotal(int Total) {
            this.Total = Total;
        }

        public Object getData() {
            return Data;
        }

        public void setData(Object Data) {
            this.Data = Data;
        }
    }
}
