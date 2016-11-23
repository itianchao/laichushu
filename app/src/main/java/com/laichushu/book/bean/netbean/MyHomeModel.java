package com.laichushu.book.bean.netbean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by PCPC on 2016/11/22.
 */

public class MyHomeModel implements Serializable{

    /**
     * success : true
     * data : [{"authorId":"112","authorName":"123","topCategoryId":"35","level":0,"score":0,"createDate":"2016-11-21 13:41","isPurchase":false,"isCollect":false,"id":"55","userId":"112","targetId":"14","collectName":"红楼梦","collectCount":1,"collectType":"1","coverUrl":"http://101.254.183.67:9980/group1/M00/00/00/wKiTPlf_W2WAC_TCAAMW_jC4dU0116.jpg","coverName":"2113444_004433009618_2.jpg","articleId":"14","articleName":"红楼梦","status":"3","topCategoryName":"神话修真","introduce":"杨青","wordNum":500,"subscribeNum":12,"browseNum":222,"commentNum":1,"awardNum":1,"awardMoney":10,"purchase":false,"collect":true}]
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
         * authorId : 112
         * authorName : 123
         * topCategoryId : 35
         * level : 0
         * score : 0
         * createDate : 2016-11-21 13:41
         * isPurchase : false
         * isCollect : false
         * id : 55
         * userId : 112
         * targetId : 14
         * collectName : 红楼梦
         * collectCount : 1
         * collectType : 1
         * coverUrl : http://101.254.183.67:9980/group1/M00/00/00/wKiTPlf_W2WAC_TCAAMW_jC4dU0116.jpg
         * coverName : 2113444_004433009618_2.jpg
         * articleId : 14
         * articleName : 红楼梦
         * status : 3
         * topCategoryName : 神话修真
         * introduce : 杨青
         * wordNum : 500
         * subscribeNum : 12
         * browseNum : 222
         * commentNum : 1
         * awardNum : 1
         * awardMoney : 10
         * purchase : false
         * collect : true
         */

        private String authorId;
        private String authorName;
        private String topCategoryId;
        private int level;
        private int score;
        private String createDate;
        private boolean isPurchase;
        private boolean isCollect;
        private String id;
        private String userId;
        private String targetId;
        private String collectName;
        private int collectCount;
        private String collectType;
        private String coverUrl;
        private String coverName;
        private String articleId;
        private String articleName;
        private String status;
        private String topCategoryName;
        private String introduce;
        private int wordNum;
        private int subscribeNum;
        private int browseNum;
        private int commentNum;
        private int awardNum;
        private int awardMoney;
        private boolean purchase;
        private boolean collect;

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

        public String getTopCategoryId() {
            return topCategoryId;
        }

        public void setTopCategoryId(String topCategoryId) {
            this.topCategoryId = topCategoryId;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public boolean isIsPurchase() {
            return isPurchase;
        }

        public void setIsPurchase(boolean isPurchase) {
            this.isPurchase = isPurchase;
        }

        public boolean isIsCollect() {
            return isCollect;
        }

        public void setIsCollect(boolean isCollect) {
            this.isCollect = isCollect;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getTargetId() {
            return targetId;
        }

        public void setTargetId(String targetId) {
            this.targetId = targetId;
        }

        public String getCollectName() {
            return collectName;
        }

        public void setCollectName(String collectName) {
            this.collectName = collectName;
        }

        public int getCollectCount() {
            return collectCount;
        }

        public void setCollectCount(int collectCount) {
            this.collectCount = collectCount;
        }

        public String getCollectType() {
            return collectType;
        }

        public void setCollectType(String collectType) {
            this.collectType = collectType;
        }

        public String getCoverUrl() {
            return coverUrl;
        }

        public void setCoverUrl(String coverUrl) {
            this.coverUrl = coverUrl;
        }

        public String getCoverName() {
            return coverName;
        }

        public void setCoverName(String coverName) {
            this.coverName = coverName;
        }

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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTopCategoryName() {
            return topCategoryName;
        }

        public void setTopCategoryName(String topCategoryName) {
            this.topCategoryName = topCategoryName;
        }

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
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

        public int getAwardMoney() {
            return awardMoney;
        }

        public void setAwardMoney(int awardMoney) {
            this.awardMoney = awardMoney;
        }

        public boolean isPurchase() {
            return purchase;
        }

        public void setPurchase(boolean purchase) {
            this.purchase = purchase;
        }

        public boolean isCollect() {
            return collect;
        }

        public void setCollect(boolean collect) {
            this.collect = collect;
        }
    }
}
