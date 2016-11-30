package com.laichushu.book.bean.netbean;

import java.io.Serializable;

/**
 * Created by PCPC on 2016/11/30.
 */

public class BookDetailsResult implements Serializable {

    /**
     * success : true
     * data : {"userId":"130","authorId":"130","coverUrl":"group1/M00/00/0D/wKiTPlg2aReAdEzMAADOPm4o5dU906.jpg","name":"12331313","introduce":"11111111","id":"132","status":"2","hitsNumber":1}
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

    public static class DataBean implements Serializable{
        /**
         * userId : 130
         * authorId : 130
         * coverUrl : group1/M00/00/0D/wKiTPlg2aReAdEzMAADOPm4o5dU906.jpg
         * name : 12331313
         * introduce : 11111111
         * id : 132
         * status : 2
         * hitsNumber : 1
         */

        private String userId;
        private String authorId;
        private String coverUrl;
        private String name;
        private String introduce;
        private String id;
        private String status;
        private int hitsNumber;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getAuthorId() {
            return authorId;
        }

        public void setAuthorId(String authorId) {
            this.authorId = authorId;
        }

        public String getCoverUrl() {
            return coverUrl;
        }

        public void setCoverUrl(String coverUrl) {
            this.coverUrl = coverUrl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getHitsNumber() {
            return hitsNumber;
        }

        public void setHitsNumber(int hitsNumber) {
            this.hitsNumber = hitsNumber;
        }
    }
}
