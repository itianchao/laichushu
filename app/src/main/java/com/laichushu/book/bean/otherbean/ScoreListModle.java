package com.laichushu.book.bean.otherbean;

import java.util.ArrayList;
import java.util.List;

/**
 * 发现 - 课程 -
 * Created by wangtong on 2017/1/10.
 */

public class ScoreListModle {

    /**
     * success : true
     * data : [{"scoreId":"187","sourceId":"15","userId":"175","sourceType":"4","score":10,"content":"hahahhahahahahahhahahaha","replyNum":0,"likeNum":0,"starLevel":5,"userNickName":"澶ч厭绁�","createDate":1484029489000}]
     */

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
         * scoreId : 187
         * sourceId : 15
         * userId : 175
         * sourceType : 4
         * score : 10.0
         * content : hahahhahahahahahhahahaha
         * replyNum : 0
         * likeNum : 0
         * starLevel : 5
         * userNickName : 澶ч厭绁�
         * createDate : 1484029489000
         */

        private String scoreId;
        private String sourceId;
        private String userId;
        private String sourceType;
        private double score;
        private String content;
        private int replyNum;
        private int likeNum;
        private int starLevel;
        private String userNickName;
        private long createDate;
        private String photo;
        private boolean isLike;


        public String getScoreId() {
            return scoreId;
        }

        public void setScoreId(String scoreId) {
            this.scoreId = scoreId;
        }

        public String getSourceId() {
            return sourceId;
        }

        public void setSourceId(String sourceId) {
            this.sourceId = sourceId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getSourceType() {
            return sourceType;
        }

        public void setSourceType(String sourceType) {
            this.sourceType = sourceType;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getReplyNum() {
            return replyNum;
        }

        public void setReplyNum(int replyNum) {
            this.replyNum = replyNum;
        }

        public int getLikeNum() {
            return likeNum;
        }

        public void setLikeNum(int likeNum) {
            this.likeNum = likeNum;
        }

        public int getStarLevel() {
            return starLevel;
        }

        public void setStarLevel(int starLevel) {
            this.starLevel = starLevel;
        }

        public String getUserNickName() {
            return userNickName;
        }

        public void setUserNickName(String userNickName) {
            this.userNickName = userNickName;
        }

        public long getCreateDate() {
            return createDate;
        }

        public void setCreateDate(long createDate) {
            this.createDate = createDate;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public boolean isLike() {
            return isLike;
        }

        public void setLike(boolean like) {
            isLike = like;
        }
    }
}
