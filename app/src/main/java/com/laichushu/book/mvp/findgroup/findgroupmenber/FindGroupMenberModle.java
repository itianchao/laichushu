package com.laichushu.book.mvp.findgroup.findgroupmenber;

import java.util.ArrayList;

/**
 * 小组成员模型
 * Created by wangtong on 2016/12/30.
 */

public class FindGroupMenberModle {

    /**
     * data : [{"id":"280","name":"大酒神","photo":"group1/M00/00/23/wKiTPlhXqMOAafndAACiakRLsdc478.jpg","role":"1","status":"3","teamId":"282","userId":"175"}]
     * success : true
     */
    private String errMsg;

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    private boolean success;
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
         * id : 280
         * name : 大酒神
         * photo : group1/M00/00/23/wKiTPlhXqMOAafndAACiakRLsdc478.jpg
         * role : 1
         * status : 3
         * teamId : 282
         * userId : 175
         */

        private String id;
        private String name;
        private String photo;
        private String role;
        private String status;
        private String teamId;
        private String userId;
        private boolean isFollow;//是否关注

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

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTeamId() {
            return teamId;
        }

        public void setTeamId(String teamId) {
            this.teamId = teamId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public boolean isFollow() {
            return isFollow;
        }

        public void setFollow(boolean follow) {
            isFollow = follow;
        }
    }
}
