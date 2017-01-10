package com.laichushu.book.mvp.find.coursera.video;

import java.util.ArrayList;

/**
 * 课程数据模型
 * Created by wangtong on 2017/1/9.
 */

public class CourseraModle {

    /**
     * success : true
     * data : {"lessonList":[{"id":"27","name":"闊虫偊","lessonType":"1","fileType":"1","status":"1","baseName":"6D0A02E333AA66F59C33DC5901307461.mp4","thumbUrl":"http://101.254.183.67:9980/group1/M00/00/31/wKiTPlh0iOOAJvJfAAAKXJgwL64639.jpg","thumbName":"u=768075676,4292609768&fm=206&gp=0.jpg","clickNum":3,"speakerName":"姘哥敓","remarks":"","ccVideoId":"82B79DAAB32E88F09C33DC5901307461"},{"id":"26","name":"澶╀笅涓哄叕","lessonType":"1","fileType":"1","status":"1","baseName":"QQ鐭棰�20161214144041.mp4","thumbUrl":"http://101.254.183.67:9980/group1/M00/00/31/wKiTPlh0hL2AHZhOAAywyXxXmzM832.jpg","thumbName":"1.jpg","clickNum":5,"speakerName":"姘哥敓","remarks":"","ccVideoId":"9DDB677C21E381E19C33DC5901307461"}],"lessonCategoryList":[{"lessonCategoryId":"45","lessonCategoryName":"瀹¤"},{"lessonCategoryId":"44","lessonCategoryName":"浼氳璇剧▼"},{"lessonCategoryId":"38","lessonCategoryName":"鍙告硶鑰冭瘯"},{"lessonCategoryId":"37","lessonCategoryName":"鑱屼笟鏁欒偛璇剧▼"}],"totalNum":2}
     */

    private boolean success;
    private DataBean data;
    private String errMsg;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * lessonList : [{"id":"27","name":"闊虫偊","lessonType":"1","fileType":"1","status":"1","baseName":"6D0A02E333AA66F59C33DC5901307461.mp4","thumbUrl":"http://101.254.183.67:9980/group1/M00/00/31/wKiTPlh0iOOAJvJfAAAKXJgwL64639.jpg","thumbName":"u=768075676,4292609768&fm=206&gp=0.jpg","clickNum":3,"speakerName":"姘哥敓","remarks":"","ccVideoId":"82B79DAAB32E88F09C33DC5901307461"},{"id":"26","name":"澶╀笅涓哄叕","lessonType":"1","fileType":"1","status":"1","baseName":"QQ鐭棰�20161214144041.mp4","thumbUrl":"http://101.254.183.67:9980/group1/M00/00/31/wKiTPlh0hL2AHZhOAAywyXxXmzM832.jpg","thumbName":"1.jpg","clickNum":5,"speakerName":"姘哥敓","remarks":"","ccVideoId":"9DDB677C21E381E19C33DC5901307461"}]
         * lessonCategoryList : [{"lessonCategoryId":"45","lessonCategoryName":"瀹¤"},{"lessonCategoryId":"44","lessonCategoryName":"浼氳璇剧▼"},{"lessonCategoryId":"38","lessonCategoryName":"鍙告硶鑰冭瘯"},{"lessonCategoryId":"37","lessonCategoryName":"鑱屼笟鏁欒偛璇剧▼"}]
         * totalNum : 2
         */

        private int totalNum;
        private ArrayList<LessonListBean> lessonList;
        private ArrayList<LessonCategoryListBean> lessonCategoryList;

        public int getTotalNum() {
            return totalNum;
        }

        public void setTotalNum(int totalNum) {
            this.totalNum = totalNum;
        }

        public ArrayList<LessonListBean> getLessonList() {
            return lessonList;
        }

        public void setLessonList(ArrayList<LessonListBean> lessonList) {
            this.lessonList = lessonList;
        }

        public ArrayList<LessonCategoryListBean> getLessonCategoryList() {
            return lessonCategoryList;
        }

        public void setLessonCategoryList(ArrayList<LessonCategoryListBean> lessonCategoryList) {
            this.lessonCategoryList = lessonCategoryList;
        }

        public static class LessonListBean {
            /**
             * id : 27
             * name : 闊虫偊
             * lessonType : 1
             * fileType : 1
             * status : 1
             * baseName : 6D0A02E333AA66F59C33DC5901307461.mp4
             * thumbUrl : http://101.254.183.67:9980/group1/M00/00/31/wKiTPlh0iOOAJvJfAAAKXJgwL64639.jpg
             * thumbName : u=768075676,4292609768&fm=206&gp=0.jpg
             * clickNum : 3
             * speakerName : 姘哥敓
             * remarks :
             * ccVideoId : 82B79DAAB32E88F09C33DC5901307461
             */

            private String id;
            private String name;
            private String lessonType;
            private String fileType;
            private String status;
            private String baseName;
            private String thumbUrl;
            private String thumbName;
            private int clickNum;
            private String speakerName;
            private String remarks;
            private String ccVideoId;

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

            public String getLessonType() {
                return lessonType;
            }

            public void setLessonType(String lessonType) {
                this.lessonType = lessonType;
            }

            public String getFileType() {
                return fileType;
            }

            public void setFileType(String fileType) {
                this.fileType = fileType;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getBaseName() {
                return baseName;
            }

            public void setBaseName(String baseName) {
                this.baseName = baseName;
            }

            public String getThumbUrl() {
                return thumbUrl;
            }

            public void setThumbUrl(String thumbUrl) {
                this.thumbUrl = thumbUrl;
            }

            public String getThumbName() {
                return thumbName;
            }

            public void setThumbName(String thumbName) {
                this.thumbName = thumbName;
            }

            public int getClickNum() {
                return clickNum;
            }

            public void setClickNum(int clickNum) {
                this.clickNum = clickNum;
            }

            public String getSpeakerName() {
                return speakerName;
            }

            public void setSpeakerName(String speakerName) {
                this.speakerName = speakerName;
            }

            public String getRemarks() {
                return remarks;
            }

            public void setRemarks(String remarks) {
                this.remarks = remarks;
            }

            public String getCcVideoId() {
                return ccVideoId;
            }

            public void setCcVideoId(String ccVideoId) {
                this.ccVideoId = ccVideoId;
            }
        }

        public static class LessonCategoryListBean {
            /**
             * lessonCategoryId : 45
             * lessonCategoryName : 瀹¤
             */

            private String lessonCategoryId;
            private String lessonCategoryName;

            public String getLessonCategoryId() {
                return lessonCategoryId;
            }

            public void setLessonCategoryId(String lessonCategoryId) {
                this.lessonCategoryId = lessonCategoryId;
            }

            public String getLessonCategoryName() {
                return lessonCategoryName;
            }

            public void setLessonCategoryName(String lessonCategoryName) {
                this.lessonCategoryName = lessonCategoryName;
            }
        }
    }
}
