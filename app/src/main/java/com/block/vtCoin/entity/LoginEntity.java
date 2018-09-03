package com.block.vtCoin.entity;

/**
 * @Description
 * @Author lijie
 * @Date 2017/7/13.
 */
public class LoginEntity extends BaseEntity{
        private DataBean data;
        public DataBean getData() {
                return data;
        }

        public void setData(DataBean data) {
                this.data = data;
        }

        public static class DataBean {
                /**
                 * name : VT8JFFT8H0
                 * retry : 0
                 */

                private String name;
                private int retry;

                public String getName() {
                        return name;
                }

                public void setName(String name) {
                        this.name = name;
                }

                public int getRetry() {
                        return retry;
                }

                public void setRetry(int retry) {
                        this.retry = retry;
                }
        }
}
