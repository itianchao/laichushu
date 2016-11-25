package com.laichushu.book.bean.JsonBean;

import java.util.ArrayList;

/**
 * 机构列表modle
 * Created by wt on 2016/11/24.
 */

public class MechanismListBean {
    private boolean success;
    private String errMsg;
    private ArrayList<DataBean> data;

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

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
        /**
         * id : 1
         * name : 涓浗涔︾睄鍑虹増绀�
         * grade : 1
         * address : 鍖椾含甯傛捣娣�鍖鸿嫃宸炶52鍙�
         * logoUrl : http://192.168.147.62/group1/M00/00/00/wKiTPlgRoDqAMg8jAABHZg_FRL8660.jpg
         */

        private String id;
        private String name;
        private String grade;
        private String address;
        private String logoUrl;

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

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getLogoUrl() {
            return logoUrl;
        }

        public void setLogoUrl(String logoUrl) {
            this.logoUrl = logoUrl;
        }
    }
}
