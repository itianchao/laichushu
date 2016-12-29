package com.laichushu.book.mvp.findgroup.groupmain;

import java.util.ArrayList;
import java.util.List;

/**
 * 小组列表 modle
 * Created by wangtong on 2016/12/27.
 */

public class GroupListModle {

    /**
     * data : [{"code":"100001","id":"275","joinNum":1,"joinStatus":"2","leaderId":"175","name":"绗竴缁�","photo":"group1/M00/00/2C/wKiTPlhiFGWAdd2OAADnEgoKhPs609.jpg","remarks":"灏辨槸涓�涓粍","status":"0"}]
     * success : true
     */

    private boolean success;
    private String errMsg;
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
        /**
         * code : 100001
         * id : 275
         * joinNum : 1
         * joinStatus : 2
         * leaderId : 175
         * name : 第一个小组
         * photo : group1/M00/00/2C/wKiTPlhiFGWAdd2OAADnEgoKhPs609.jpg
         * remarks : 第一个小组
         * status : 0
         */

        private String code;
        private String id;
        private int joinNum;
        private String joinStatus;
        private String leaderId;
        private String name;
        private String photo;
        private String remarks;
        private String status;
        private String markContent;
        private String createDate;

        public String getMarkContent() {
            return markContent;
        }

        public void setMarkContent(String markContent) {
            this.markContent = markContent;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getJoinNum() {
            return joinNum;
        }

        public void setJoinNum(int joinNum) {
            this.joinNum = joinNum;
        }

        public String getJoinStatus() {
            return joinStatus;
        }

        public void setJoinStatus(String joinStatus) {
            this.joinStatus = joinStatus;
        }

        public String getLeaderId() {
            return leaderId;
        }

        public void setLeaderId(String leaderId) {
            this.leaderId = leaderId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
