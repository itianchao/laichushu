package com.laichushu.book.bean.netbean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by PCPC on 2016/12/9.
 */

public class BookDetailsModle implements Serializable {

    /**
     * success : true
     * data : {"success":true,"articleData":{"articleId":"256","articleName":"第一部","authorId":"175","authorName":"大酒神","coverUrl":"http://101.254.183.67:9980/group1/M00/00/1E/wKiTPlhQ-4OAOo57AACRv_UOjho827.jpg","coverName":"cover.jpg","status":"1","topCategoryId":"64","topCategoryName":"短篇","introduce":"盗墓笔记","freezeStatus":"1","expressStatus":"0","wordNum":0,"subscribeNum":0,"browseNum":1,"commentNum":0,"awardNum":0,"level":0,"score":0,"awardMoney":0,"createDate":"2016-12-14 15:57","updateDate":"2016-12-15 15:43","isPurchase":false,"isSubscribe":false,"isCollect":false,"isAward":false},"authorData":{"authorId":"175","nickName":"大酒神","photo":"http://101.254.183.67:9980/group1/M00/00/23/wKiTPlhXqMOAafndAACiakRLsdc478.jpg","articleNum":2},"bestLikeData":[],"scoreReaderData":[],"scoreCattleData":[]}
     */

    private boolean success;
    private DataBean data;

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    private String errMsg;

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
         * success : true
         * articleData : {"articleId":"256","articleName":"第一部","authorId":"175","authorName":"大酒神","coverUrl":"http://101.254.183.67:9980/group1/M00/00/1E/wKiTPlhQ-4OAOo57AACRv_UOjho827.jpg","coverName":"cover.jpg","status":"1","topCategoryId":"64","topCategoryName":"短篇","introduce":"盗墓笔记","freezeStatus":"1","expressStatus":"0","wordNum":0,"subscribeNum":0,"browseNum":1,"commentNum":0,"awardNum":0,"level":0,"score":0,"awardMoney":0,"createDate":"2016-12-14 15:57","updateDate":"2016-12-15 15:43","isPurchase":false,"isSubscribe":false,"isCollect":false,"isAward":false}
         * authorData : {"authorId":"175","nickName":"大酒神","photo":"http://101.254.183.67:9980/group1/M00/00/23/wKiTPlhXqMOAafndAACiakRLsdc478.jpg","articleNum":2}
         * bestLikeData : []
         * scoreReaderData : []
         * scoreCattleData : []
         */

        private boolean success;
        private ArticleDataBean articleData;
        private AuthorDataBean authorData;
        private List<?> bestLikeData;
        private List<?> scoreReaderData;
        private List<?> scoreCattleData;

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public ArticleDataBean getArticleData() {
            return articleData;
        }

        public void setArticleData(ArticleDataBean articleData) {
            this.articleData = articleData;
        }

        public AuthorDataBean getAuthorData() {
            return authorData;
        }

        public void setAuthorData(AuthorDataBean authorData) {
            this.authorData = authorData;
        }

        public List<?> getBestLikeData() {
            return bestLikeData;
        }

        public void setBestLikeData(List<?> bestLikeData) {
            this.bestLikeData = bestLikeData;
        }

        public List<?> getScoreReaderData() {
            return scoreReaderData;
        }

        public void setScoreReaderData(List<?> scoreReaderData) {
            this.scoreReaderData = scoreReaderData;
        }

        public List<?> getScoreCattleData() {
            return scoreCattleData;
        }

        public void setScoreCattleData(List<?> scoreCattleData) {
            this.scoreCattleData = scoreCattleData;
        }

        public static class ArticleDataBean {
            /**
             * articleId : 256
             * articleName : 第一部
             * authorId : 175
             * authorName : 大酒神
             * coverUrl : http://101.254.183.67:9980/group1/M00/00/1E/wKiTPlhQ-4OAOo57AACRv_UOjho827.jpg
             * coverName : cover.jpg
             * status : 1
             * topCategoryId : 64
             * topCategoryName : 短篇
             * introduce : 盗墓笔记
             * freezeStatus : 1
             * expressStatus : 0
             * wordNum : 0
             * subscribeNum : 0
             * browseNum : 1
             * commentNum : 0
             * awardNum : 0
             * level : 0
             * score : 0
             * awardMoney : 0
             * createDate : 2016-12-14 15:57
             * updateDate : 2016-12-15 15:43
             * isPurchase : false
             * isSubscribe : false
             * isCollect : false
             * isAward : false
             */

            private String articleId;
            private String articleName;
            private String name;
            private String authorId;
            private String authorName;
            private String coverUrl;
            private String coverName;
            private String status;
            private String topCategoryId;
            private String topCategoryName;
            private String introduce;
            private String freezeStatus;
            private String expressStatus;
            private int wordNum;
            private int subscribeNum;
            private int browseNum;
            private int commentNum;
            private int awardNum;
            private int level;
            private int score;
            private int awardMoney;
            private String createDate;
            private String updateDate;
            private boolean isPurchase;
            private boolean isSubscribe;
            private boolean isCollect;
            private boolean isAward;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
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

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getTopCategoryId() {
                return topCategoryId;
            }

            public void setTopCategoryId(String topCategoryId) {
                this.topCategoryId = topCategoryId;
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

            public String getFreezeStatus() {
                return freezeStatus;
            }

            public void setFreezeStatus(String freezeStatus) {
                this.freezeStatus = freezeStatus;
            }

            public String getExpressStatus() {
                return expressStatus;
            }

            public void setExpressStatus(String expressStatus) {
                this.expressStatus = expressStatus;
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

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
                this.score = score;
            }

            public int getAwardMoney() {
                return awardMoney;
            }

            public void setAwardMoney(int awardMoney) {
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

            public boolean isIsAward() {
                return isAward;
            }

            public void setIsAward(boolean isAward) {
                this.isAward = isAward;
            }
        }

        public static class AuthorDataBean {
            /**
             * authorId : 175
             * nickName : 大酒神
             * photo : http://101.254.183.67:9980/group1/M00/00/23/wKiTPlhXqMOAafndAACiakRLsdc478.jpg
             * articleNum : 2
             */

            private String authorId;
            private String nickName;
            private String photo;
            private int articleNum;

            public String getAuthorId() {
                return authorId;
            }

            public void setAuthorId(String authorId) {
                this.authorId = authorId;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public int getArticleNum() {
                return articleNum;
            }

            public void setArticleNum(int articleNum) {
                this.articleNum = articleNum;
            }
        }
    }
}
