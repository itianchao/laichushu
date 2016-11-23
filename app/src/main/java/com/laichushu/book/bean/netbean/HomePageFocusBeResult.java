package com.laichushu.book.bean.netbean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by PCPC on 2016/11/23.
 */

public class HomePageFocusBeResult implements Serializable {

    /**
     * success : true
     * data : [{"photo":"","nickName":"澶忓ぉ","grade":"1","sourceUserId":"130","targetUserId":"112"},{"photo":"","grade":"1","sourceUserId":"122","targetUserId":"112"}]
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
         * photo :
         * nickName : 澶忓ぉ
         * grade : 1
         * sourceUserId : 130
         * targetUserId : 112
         */

        private String photo;
        private String nickName;
        private String grade;
        private String sourceUserId;
        private String targetUserId;

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

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public String getSourceUserId() {
            return sourceUserId;
        }

        public void setSourceUserId(String sourceUserId) {
            this.sourceUserId = sourceUserId;
        }

        public String getTargetUserId() {
            return targetUserId;
        }

        public void setTargetUserId(String targetUserId) {
            this.targetUserId = targetUserId;
        }
    }
}
