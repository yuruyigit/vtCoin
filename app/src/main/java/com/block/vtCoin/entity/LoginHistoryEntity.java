package com.block.vtCoin.entity;

import java.util.List;

/**
 * @Description
 * @Author lijie
 * @Date 2017/7/13.
 */
public class LoginHistoryEntity extends BaseEntity{


        /**
         * data : {"Count":150,"His":[{"Ip":"103.44.145.248","Type":null,"Date":"2017-10-31T17:31:03.227"},{"Ip":"103.44.145.248","Type":null,"Date":"2017-10-31T17:14:23.66"},{"Ip":"103.44.145.248","Type":null,"Date":"2017-10-31T17:06:32.467"},{"Ip":"103.44.145.248","Type":null,"Date":"2017-10-31T17:00:51.42"},{"Ip":"103.44.145.248","Type":null,"Date":"2017-10-31T16:56:58.727"},{"Ip":"103.44.145.248","Type":null,"Date":"2017-10-31T16:41:53.293"},{"Ip":"103.44.145.248","Type":null,"Date":"2017-10-31T16:37:40.783"},{"Ip":"103.44.145.248","Type":null,"Date":"2017-10-31T16:36:32.94"},{"Ip":"103.44.145.248","Type":null,"Date":"2017-10-31T16:35:35.497"},{"Ip":"103.44.145.248","Type":null,"Date":"2017-10-31T16:30:04.523"}]}
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
                 * Count : 150
                 * His : [{"Ip":"103.44.145.248","Type":null,"Date":"2017-10-31T17:31:03.227"},{"Ip":"103.44.145.248","Type":null,"Date":"2017-10-31T17:14:23.66"},{"Ip":"103.44.145.248","Type":null,"Date":"2017-10-31T17:06:32.467"},{"Ip":"103.44.145.248","Type":null,"Date":"2017-10-31T17:00:51.42"},{"Ip":"103.44.145.248","Type":null,"Date":"2017-10-31T16:56:58.727"},{"Ip":"103.44.145.248","Type":null,"Date":"2017-10-31T16:41:53.293"},{"Ip":"103.44.145.248","Type":null,"Date":"2017-10-31T16:37:40.783"},{"Ip":"103.44.145.248","Type":null,"Date":"2017-10-31T16:36:32.94"},{"Ip":"103.44.145.248","Type":null,"Date":"2017-10-31T16:35:35.497"},{"Ip":"103.44.145.248","Type":null,"Date":"2017-10-31T16:30:04.523"}]
                 */

                private int Count;
                private List<HisBean> His;

                public int getCount() {
                        return Count;
                }

                public void setCount(int Count) {
                        this.Count = Count;
                }

                public List<HisBean> getHis() {
                        return His;
                }

                public void setHis(List<HisBean> His) {
                        this.His = His;
                }

                public static class HisBean {
                        /**
                         * Ip : 103.44.145.248
                         * Type : null
                         * Date : 2017-10-31T17:31:03.227
                         */

                        private String Ip;
                        private Object Type;
                        private String Date;

                        public String getIp() {
                                return Ip;
                        }

                        public void setIp(String Ip) {
                                this.Ip = Ip;
                        }

                        public Object getType() {
                                return Type;
                        }

                        public void setType(Object Type) {
                                this.Type = Type;
                        }

                        public String getDate() {
                                return Date;
                        }

                        public void setDate(String Date) {
                                this.Date = Date;
                        }
                }
        }
}
