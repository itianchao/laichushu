package com.laichushu.book.bean.netbean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by PCPC on 2016/12/13.
 */

public class SignStateResult implements Serializable {

    /**
     * success : true
     * data : [{"id":"9","name":"娌冲寳鏁欒偛鍑虹増绀�","editors":[{"id":"169","nickName":"sllE002a"}]},{"id":"10","name":"灞变笢浜烘皯鍑虹増绀�","editors":[]},{"id":"1","name":"涓浗涔︾睄鍑虹増绀�","editors":[{"id":"156","nickName":"sllE001a涓浗涔︾睄缂栬緫鏄电О"}]},{"id":"2","name":"璇昏�呯敤鎴风粍","editors":[]}]
     */

    private boolean success;
    private List<DataBean> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 9
         * name : 娌冲寳鏁欒偛鍑虹増绀�
         * editors : [{"id":"169","nickName":"sllE002a"}]
         */

        private String id;
        private String name;
        private List<EditorsBean> editors;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<EditorsBean> getEditors() {
            return editors;
        }

        public void setEditors(List<EditorsBean> editors) {
            this.editors = editors;
        }

        public static class EditorsBean {
            /**
             * id : 169
             * nickName : sllE002a
             */

            private String id;
            private String nickName;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }
        }
    }
}
