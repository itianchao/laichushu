package com.laichushu.book.bean.netbean;

import java.io.Serializable;

/**
 * Created by PCPC on 2016/11/24.
 */

public class PersonInfoResultReward implements Serializable {

    /**
     * success : true
     * data : {"id":"112","photo":"http://101.254.183.67:9980/group1/M00/00/0E/wKiTPlg2yCyAdmQ8AACIERC8O-Q714.jpg","nickName":"哦哦哦","sign":"123456789","city":"01","sex":"男","birthday":-2209017600000}
     */

    private boolean success;
    private DataBean data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 112
         * photo : http://101.254.183.67:9980/group1/M00/00/0E/wKiTPlg2yCyAdmQ8AACIERC8O-Q714.jpg
         * nickName : 哦哦哦
         * sign : 123456789
         * city : 01
         * sex : 男
         * birthday : -2209017600000
         */

        private String id;
        private String photo;
        private String nickName;
        private String sign;
        private String city;
        private String sex;
        private String birthday;
        private String identityAuthStatus;

        public String getIdentityAuthStatus() {
            return identityAuthStatus;
        }

        public void setIdentityAuthStatus(String identityAuthStatus) {
            this.identityAuthStatus = identityAuthStatus;
        }


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }
    }
}
