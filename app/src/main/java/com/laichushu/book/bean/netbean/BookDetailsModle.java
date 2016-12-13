package com.laichushu.book.bean.netbean;

import java.io.Serializable;

/**
 * Created by PCPC on 2016/12/9.
 */

public class BookDetailsModle implements Serializable {

    /**
     * success : true
     * data : {"articleId":"110","articleName":"飞翔的翔","authorId":"1","authorName":"小小管理员","status":"2","freezeStatus":"1","wordNum":0,"subscribeNum":0,"browseNum":0,"commentNum":0,"awardNum":0,"level":0,"score":0,"awardMoney":0,"createDate":"2016-11-21 10:52","updateDate":"2016-12-07 14:10","isPurchase":false,"isSubscribe":false,"isCollect":false}
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
         * articleId : 110
         * articleName : 飞翔的翔
         * authorId : 1
         * authorName : 小小管理员
         * status : 2
         * freezeStatus : 1
         * wordNum : 0
         * subscribeNum : 0
         * browseNum : 0
         * commentNum : 0
         * awardNum : 0
         * level : 0
         * score : 0.0
         * awardMoney : 0.0
         * createDate : 2016-11-21 10:52
         * updateDate : 2016-12-07 14:10
         * isPurchase : false
         * isSubscribe : false
         * isCollect : false
         */

        private String articleId;
        private String articleName;
        private String authorId;
        private String authorName;
        private String status;
        private String freezeStatus;
        private int wordNum;
        private int subscribeNum;
        private int browseNum;
        private int commentNum;
        private int awardNum;
        private int level;
        private double score;
        private double awardMoney;
        private String createDate;
        private String updateDate;
        private boolean isPurchase;
        private boolean isSubscribe;
        private boolean isCollect;

        public String getArticleId() {
            return articleId;
        }

        public void setArticleId(String articleId) {
            this.articleId = articleId;
        }

        public String getArticleName() {
            return articleName;
        }

        public void setArticleName(String articleName) {
            this.articleName = articleName;
        }

        public String getAuthorId() {
            return authorId;
        }

        public void setAuthorId(String authorId) {
            this.authorId = authorId;
        }

        public String getAuthorName() {
            return authorName;
        }

        public void setAuthorName(String authorName) {
            this.authorName = authorName;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getFreezeStatus() {
            return freezeStatus;
        }

        public void setFreezeStatus(String freezeStatus) {
            this.freezeStatus = freezeStatus;
        }

        public int getWordNum() {
            return wordNum;
        }

        public void setWordNum(int wordNum) {
            this.wordNum = wordNum;
        }

        public int getSubscribeNum() {
            return subscribeNum;
        }

        public void setSubscribeNum(int subscribeNum) {
            this.subscribeNum = subscribeNum;
        }

        public int getBrowseNum() {
            return browseNum;
        }

        public void setBrowseNum(int browseNum) {
            this.browseNum = browseNum;
        }

        public int getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(int commentNum) {
            this.commentNum = commentNum;
        }

        public int getAwardNum() {
            return awardNum;
        }

        public void setAwardNum(int awardNum) {
            this.awardNum = awardNum;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public double getAwardMoney() {
            return awardMoney;
        }

        public void setAwardMoney(double awardMoney) {
            this.awardMoney = awardMoney;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public boolean isIsPurchase() {
            return isPurchase;
        }

        public void setIsPurchase(boolean isPurchase) {
            this.isPurchase = isPurchase;
        }

        public boolean isIsSubscribe() {
            return isSubscribe;
        }

        public void setIsSubscribe(boolean isSubscribe) {
            this.isSubscribe = isSubscribe;
        }

        public boolean isIsCollect() {
            return isCollect;
        }

        public void setIsCollect(boolean isCollect) {
            this.isCollect = isCollect;
        }
    }
}
