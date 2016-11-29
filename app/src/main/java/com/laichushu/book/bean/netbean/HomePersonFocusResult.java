package com.laichushu.book.bean.netbean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by PCPC on 2016/11/23.
 */

public class HomePersonFocusResult implements Serializable {

    /**
     * success : true
     * data : [{"nickName":"閫椾綘鐜�","grade":"1","sourceUserId":"112","targetUserId":"125"},{"photo":"","nickName":"涓�姣�","grade":"1","sourceUserId":"112","targetUserId":"127"}]
     */

    private boolean success;


    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    private String errMsg;
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

    public static class DataBean implements Serializable {
        /**
         * nickName : 閫椾綘鐜�
         * grade : 1
         * sourceUserId : 112
         * targetUserId : 125
         * photo :
         */

        private String nickName;
        private String grade;
        private String sourceUserId;
        private String targetUserId;
        private String photo;
        private boolean status;

        public boolean isStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
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

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }
    }
}
