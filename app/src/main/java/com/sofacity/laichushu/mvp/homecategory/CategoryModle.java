package com.sofacity.laichushu.mvp.homecategory;

import java.util.ArrayList;
import java.util.List;

/**
 * 分类
 * Created by wangtong on 2016/11/10.
 */

public class CategoryModle {

    /**
     * success : true
     * data : [{"id":"35","name":"浜屾鍏�","type":"1","articleNum":4,"child":[{"id":"38","name":"闈掓槬骞绘兂","type":"2","articleNum":3},{"id":"37","name":"鎼炵瑧鍚愭Ы","type":"2","articleNum":1},{"id":"36","name":"琛嶇敓骞绘兂","type":"2","articleNum":0}]},{"id":"29","name":"閮藉競","type":"1","articleNum":1,"child":[{"id":"34","name":"鐜板疄鐧炬��","type":"2","articleNum":1},{"id":"33","name":"闈掓槬鏍\u2033洯","type":"2","articleNum":0},{"id":"32","name":"鎭╂�ㄦ儏浠�","type":"2","articleNum":0},{"id":"31","name":"鐖辨儏濠氬Щ","type":"2","articleNum":0},{"id":"30","name":"閮藉競鐢熸椿","type":"2","articleNum":0}]},{"id":"24","name":"浠欎緺","type":"1","articleNum":1,"child":[{"id":"28","name":"绁炶瘽淇湡","type":"2","articleNum":0},{"id":"27","name":"鐜颁唬淇湡","type":"2","articleNum":0},{"id":"26","name":"骞绘兂淇粰","type":"2","articleNum":0},{"id":"25","name":"淇湡鏂囨槑","type":"2","articleNum":0}]},{"id":"18","name":"鏂囧","type":"1","articleNum":2,"child":[{"id":"20","name":"鏁ｆ枃","type":"2","articleNum":2}]}]
     */

    private boolean success;
    private String errMsg;

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    /**
     * id : 35
     * name : 浜屾鍏�
     * type : 1
     * articleNum : 4
     * child : [{"id":"38","name":"闈掓槬骞绘兂","type":"2","articleNum":3},{"id":"37","name":"鎼炵瑧鍚愭Ы","type":"2","articleNum":1},{"id":"36","name":"琛嶇敓骞绘兂","type":"2","articleNum":0}]
     */

    private ArrayList<DataBean> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ArrayList<DataBean> getData() {
        return data;
    }

    public void setData(ArrayList<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String id;
        private String name;
        private String type;
        private int articleNum;
        /**
         * id : 38
         * name : 闈掓槬骞绘兂
         * type : 2
         * articleNum : 3
         */

        private ArrayList<ChildBean> child;

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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getArticleNum() {
            return articleNum;
        }

        public void setArticleNum(int articleNum) {
            this.articleNum = articleNum;
        }

        public ArrayList<ChildBean> getChild() {
            return child;
        }

        public void setChild(ArrayList<ChildBean> child) {
            this.child = child;
        }

        public static class ChildBean {
            private String id;
            private String name;
            private String type;
            private int articleNum;

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

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public int getArticleNum() {
                return articleNum;
            }

            public void setArticleNum(int articleNum) {
                this.articleNum = articleNum;
            }
        }
    }
}
