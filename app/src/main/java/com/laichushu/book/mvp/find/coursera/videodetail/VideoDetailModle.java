package com.laichushu.book.mvp.find.coursera.videodetail;

import com.laichushu.book.bean.netbean.BaseModel;

/**
 * 视频详情
 * Created by wangtong on 2017/1/10.
 */

public class VideoDetailModle extends BaseModel {

    /**
     * success : true
     * data : {"id":"21","name":"娴嬭瘯璇剧▼5","lessonType":"1","fileType":"2","status":"1","baseUrl":"http://101.254.183.67:9980/group1/M00/00/2F/wKiTPlhtqciACIF-AANx44w8ZPY15.docx","baseName":"閭浣跨敤鎵嬪唽.docx","thumbUrl":"http://101.254.183.67:9980/group1/M00/00/2F/wKiTPlhtqceABF-cAABB17py338002.png","thumbName":"8cccd24f53fe4bacb51ce74c1d089119.png","downNum":6,"collectNum":8,"clickNum":3,"remarks":"","isCollect":"2"}
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

    /**
     * data : {"id":"15","name":"璇剧▼娴嬭瘯4","lessonType":"1","fileType":"1","status":"1","baseUrl":"http://101.254.183.67:9980/group1/M00/00/2C/wKiTPlhiG1eAQXWHAAHtis6W7XE71.docx","baseName":"QQ鐭棰�20161214144041.mp4","thumbUrl":"http://101.254.183.67:9980/group1/M00/00/2C/wKiTPlhiGDKAZjLkAAwhqcZFfLs334.jpg","thumbName":"star (6).jpg","downNum":2,"collectNum":2,"clickNum":149,"remarks":"qq鐭浜嬪疄涓婂北涓滅渷鏂规硶浣嗘槸绉戞妧鐣岀鎶�绉戞妧蹇揩鍔犲揩鍔犲揩鍊熻鍗℃枻鏂よ杈� 棰戙�傘�傘�傘�傘�傘�傘�傘�傘�傘�傘�傘�傘�傘�傘�傘�傘��","ccVideoId":"33FDDB28A2EE0CA29C33DC5901307461","isCollect":"2"}
     */

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
         * id : 21
         * name : 娴嬭瘯璇剧▼5
         * lessonType : 1
         * fileType : 2
         * status : 1
         * baseUrl : http://101.254.183.67:9980/group1/M00/00/2F/wKiTPlhtqciACIF-AANx44w8ZPY15.docx
         * baseName : 閭浣跨敤鎵嬪唽.docx
         * thumbUrl : http://101.254.183.67:9980/group1/M00/00/2F/wKiTPlhtqceABF-cAABB17py338002.png
         * thumbName : 8cccd24f53fe4bacb51ce74c1d089119.png
         * downNum : 6
         * collectNum : 8
         * clickNum : 3
         * remarks :
         * isCollect : 2
         * averageStar :"0"
         * isComment :
         * ccVideoId
         */

        private String id;
        private String name;
        private String lessonType;
        private String fileType;
        private String status;
        private String baseUrl;
        private String baseName;
        private String thumbUrl;
        private String thumbName;
        private int downNum;
        private int collectNum;
        private int clickNum;
        private String remarks;
        private String isCollect;//1、收藏 2、已收藏
        private boolean isComment;
        private String createDate;
        private String ccVideoId;

        public String getCcVideoId() {
            return ccVideoId;
        }

        public void setCcVideoId(String ccVideoId) {
            this.ccVideoId = ccVideoId;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

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

        public String getBaseUrl() {
            return baseUrl;
        }

        public void setBaseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
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

        public int getDownNum() {
            return downNum;
        }

        public void setDownNum(int downNum) {
            this.downNum = downNum;
        }

        public int getCollectNum() {
            return collectNum;
        }

        public void setCollectNum(int collectNum) {
            this.collectNum = collectNum;
        }

        public int getClickNum() {
            return clickNum;
        }

        public void setClickNum(int clickNum) {
            this.clickNum = clickNum;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getIsCollect() {
            return isCollect;
        }

        public void setIsCollect(String isCollect) {
            this.isCollect = isCollect;
        }

        public boolean isComment() {
            return isComment;
        }

        public void setComment(boolean comment) {
            isComment = comment;
        }
    }
}
