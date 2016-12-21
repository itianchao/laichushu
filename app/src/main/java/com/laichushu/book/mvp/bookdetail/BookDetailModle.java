package com.laichushu.book.mvp.bookdetail;

import com.laichushu.book.mvp.home.HomeHotModel;

import java.util.ArrayList;

/**
 * Created by wangtong on 2016/12/21.
 */

public class BookDetailModle {

    /**
     * data : {"articleData":{"articleId":"216","articleName":"愿无岁月可回头","authorId":"150","authorName":"无尽之夏","awardMoney":540,"awardNum":7,"browseNum":7,"commentNum":5,"coverName":"20161209194753.jpg","coverUrl":"http://101.254.183.67:9980/group1/M00/00/1A/wKiTPlhKmfCAWIm6AAEuwbmelvg817.jpg","createDate":"2016-12-09 19:47","expressStatus":"0","freezeStatus":"1","introduce":"的点点滴滴点点滴滴","isAward":false,"isCollect":false,"isPurchase":false,"isSubscribe":false,"level":4,"score":8,"status":"1","subscribeNum":3,"topCategoryId":"1","updateDate":"2016-12-16 11:31","wordNum":0},"authorData":{"articleNum":4,"authorId":"150","authorIntroduction":"","nickName":"无尽之夏","photo":"http://101.254.183.67:9980/group1/M00/00/1F/wKiTPlhSE0uAZ7BfAAEuwbmelvg627.jpg"},"bestLikeData":[{"articleId":"253","articleName":"琥珀世界","authorId":"173","authorName":"琥珀","awardMoney":0,"awardNum":0,"browseNum":2,"commentNum":2,"coverUrl":"http://101.254.183.67:9980/group1/M00/00/1E/wKiTPlhQsIiAReXpAAFRtujQZxI008.jpg","createDate":"2016-12-14 10:38","expressStatus":"0","freezeStatus":"1","introduce":"琥珀世界","isAward":false,"isCollect":false,"isPurchase":false,"isSubscribe":false,"level":4,"score":9,"status":"1","subscribeNum":2,"topCategoryId":"63","topCategoryName":"二次元","updateDate":"2016-12-15 16:14","wordNum":10018}],"scoreCattleData":[{"articleId":"216","commentTime":"2016-12-20 15:46:12","content":"我是大咖哦","isLike":false,"likeNum":0,"nickName":"永生","photo":"","replyNum":0,"sourceId":"121","userId":"195"}],"scoreReaderData":[{"articleId":"216","commentTime":"2016-12-16 10:56:53","content":"狗哈哈哈","isLike":false,"likeNum":0,"nickName":"逗你玩","photo":"http://101.254.183.67:9980/group1/M00/00/22/wKiTPlhTUauAXN3_AAAH6nNWI3k479.jpg","replyNum":1,"sourceId":"97","userId":"180"},{"articleId":"216","commentTime":"2016-12-16 11:15:50","content":"你好","isLike":false,"likeNum":0,"nickName":"大酒神","photo":"http://101.254.183.67:9980/group1/M00/00/23/wKiTPlhXqMOAafndAACiakRLsdc478.jpg","replyNum":1,"sourceId":"98","userId":"175"},{"articleId":"216","commentTime":"2016-12-16 13:31:24","content":"请留下你对本书的想法及感受","isLike":false,"likeNum":2,"nickName":"喵了个咪呀！在线","photo":"http://101.254.183.67:9980/group1/M00/00/21/wKiTPlhTSfaARvIgAADEH4vElKc174.jpg","replyNum":0,"sourceId":"100","userId":"159"},{"articleId":"216","commentTime":"2016-12-15 14:08:30","content":"某人的微笑，一如那年的夏天，阳光明媚","isLike":false,"likeNum":6,"nickName":"好好先生","photo":"http://101.254.183.67:9980/group1/M00/00/24/wKiTPlhX5umAdjSOAADFt00Uye4513.jpg","replyNum":2,"sourceId":"89","userId":"151"}],"success":true}
     * success : true
     */

    private DataBean data;
    private boolean success;
    private String errMsg;

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static class DataBean {
        /**
         * articleData : {"articleId":"216","articleName":"愿无岁月可回头","authorId":"150","authorName":"无尽之夏","awardMoney":540,"awardNum":7,"browseNum":7,"commentNum":5,"coverName":"20161209194753.jpg","coverUrl":"http://101.254.183.67:9980/group1/M00/00/1A/wKiTPlhKmfCAWIm6AAEuwbmelvg817.jpg","createDate":"2016-12-09 19:47","expressStatus":"0","freezeStatus":"1","introduce":"的点点滴滴点点滴滴","isAward":false,"isCollect":false,"isPurchase":false,"isSubscribe":false,"level":4,"score":8,"status":"1","subscribeNum":3,"topCategoryId":"1","updateDate":"2016-12-16 11:31","wordNum":0}
         * authorData : {"articleNum":4,"authorId":"150","authorIntroduction":"","nickName":"无尽之夏","photo":"http://101.254.183.67:9980/group1/M00/00/1F/wKiTPlhSE0uAZ7BfAAEuwbmelvg627.jpg"}
         * bestLikeData : [{"articleId":"253","articleName":"琥珀世界","authorId":"173","authorName":"琥珀","awardMoney":0,"awardNum":0,"browseNum":2,"commentNum":2,"coverUrl":"http://101.254.183.67:9980/group1/M00/00/1E/wKiTPlhQsIiAReXpAAFRtujQZxI008.jpg","createDate":"2016-12-14 10:38","expressStatus":"0","freezeStatus":"1","introduce":"琥珀世界","isAward":false,"isCollect":false,"isPurchase":false,"isSubscribe":false,"level":4,"score":9,"status":"1","subscribeNum":2,"topCategoryId":"63","topCategoryName":"二次元","updateDate":"2016-12-15 16:14","wordNum":10018}]
         * scoreCattleData : [{"articleId":"216","commentTime":"2016-12-20 15:46:12","content":"我是大咖哦","isLike":false,"likeNum":0,"nickName":"永生","photo":"","replyNum":0,"sourceId":"121","userId":"195"}]
         * scoreReaderData : [{"articleId":"216","commentTime":"2016-12-16 10:56:53","content":"狗哈哈哈","isLike":false,"likeNum":0,"nickName":"逗你玩","photo":"http://101.254.183.67:9980/group1/M00/00/22/wKiTPlhTUauAXN3_AAAH6nNWI3k479.jpg","replyNum":1,"sourceId":"97","userId":"180"},{"articleId":"216","commentTime":"2016-12-16 11:15:50","content":"你好","isLike":false,"likeNum":0,"nickName":"大酒神","photo":"http://101.254.183.67:9980/group1/M00/00/23/wKiTPlhXqMOAafndAACiakRLsdc478.jpg","replyNum":1,"sourceId":"98","userId":"175"},{"articleId":"216","commentTime":"2016-12-16 13:31:24","content":"请留下你对本书的想法及感受","isLike":false,"likeNum":2,"nickName":"喵了个咪呀！在线","photo":"http://101.254.183.67:9980/group1/M00/00/21/wKiTPlhTSfaARvIgAADEH4vElKc174.jpg","replyNum":0,"sourceId":"100","userId":"159"},{"articleId":"216","commentTime":"2016-12-15 14:08:30","content":"某人的微笑，一如那年的夏天，阳光明媚","isLike":false,"likeNum":6,"nickName":"好好先生","photo":"http://101.254.183.67:9980/group1/M00/00/24/wKiTPlhX5umAdjSOAADFt00Uye4513.jpg","replyNum":2,"sourceId":"89","userId":"151"}]
         * success : true
         */

        private ArticleDataBean articleData;
        private AuthorDataBean authorData;
        private boolean success;
        private ArrayList<HomeHotModel.DataBean> bestLikeData;
        private ArrayList<ArticleCommentModle.DataBean> scoreCattleData;
        private ArrayList<ArticleCommentModle.DataBean> scoreReaderData;

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

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public ArrayList<HomeHotModel.DataBean> getBestLikeData() {
            return bestLikeData;
        }

        public void setBestLikeData(ArrayList<HomeHotModel.DataBean> bestLikeData) {
            this.bestLikeData = bestLikeData;
        }

        public ArrayList<ArticleCommentModle.DataBean> getScoreCattleData() {
            return scoreCattleData;
        }

        public void setScoreCattleData(ArrayList<ArticleCommentModle.DataBean> scoreCattleData) {
            this.scoreCattleData = scoreCattleData;
        }

        public ArrayList<ArticleCommentModle.DataBean> getScoreReaderData() {
            return scoreReaderData;
        }

        public void setScoreReaderData(ArrayList<ArticleCommentModle.DataBean> scoreReaderData) {
            this.scoreReaderData = scoreReaderData;
        }

        public static class ArticleDataBean {
            /**
             * articleId : 216
             * articleName : 愿无岁月可回头
             * authorId : 150
             * authorName : 无尽之夏
             * awardMoney : 540.0
             * awardNum : 7
             * browseNum : 7
             * commentNum : 5
             * coverName : 20161209194753.jpg
             * coverUrl : http://101.254.183.67:9980/group1/M00/00/1A/wKiTPlhKmfCAWIm6AAEuwbmelvg817.jpg
             * createDate : 2016-12-09 19:47
             * expressStatus : 0
             * freezeStatus : 1
             * introduce : 的点点滴滴点点滴滴
             * isAward : false
             * isCollect : false
             * isPurchase : false
             * isSubscribe : false
             * level : 4
             * score : 8.0
             * status : 1
             * subscribeNum : 3
             * topCategoryId : 1
             * updateDate : 2016-12-16 11:31
             * wordNum : 0
             */

            private String articleId;
            private String articleName;
            private String authorId;
            private String authorName;
            private double awardMoney;
            private int awardNum;
            private int browseNum;
            private int commentNum;
            private String coverName;
            private String coverUrl;
            private String createDate;
            private String expressStatus;
            private String freezeStatus;
            private String introduce;
            private boolean isAward;
            private boolean isCollect;
            private boolean isPurchase;
            private boolean isSubscribe;
            private int level;
            private double score;
            private String status;
            private int subscribeNum;
            private String topCategoryId;
            private String updateDate;
            private int wordNum;
            private String topCategoryName;
            private double Price;

            public double getPrice() {
                return Price;
            }

            public void setPrice(double price) {
                Price = price;
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

            public boolean isAward() {
                return isAward;
            }

            public void setAward(boolean award) {
                isAward = award;
            }

            public boolean isCollect() {
                return isCollect;
            }

            public void setCollect(boolean collect) {
                isCollect = collect;
            }

            public boolean isPurchase() {
                return isPurchase;
            }

            public void setPurchase(boolean purchase) {
                isPurchase = purchase;
            }

            public boolean isSubscribe() {
                return isSubscribe;
            }

            public void setSubscribe(boolean subscribe) {
                isSubscribe = subscribe;
            }

            public String getTopCategoryName() {
                return topCategoryName;
            }

            public void setTopCategoryName(String topCategoryName) {
                this.topCategoryName = topCategoryName;
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

            public double getAwardMoney() {
                return awardMoney;
            }

            public void setAwardMoney(double awardMoney) {
                this.awardMoney = awardMoney;
            }

            public int getAwardNum() {
                return awardNum;
            }

            public void setAwardNum(int awardNum) {
                this.awardNum = awardNum;
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

            public String getCoverName() {
                return coverName;
            }

            public void setCoverName(String coverName) {
                this.coverName = coverName;
            }

            public String getCoverUrl() {
                return coverUrl;
            }

            public void setCoverUrl(String coverUrl) {
                this.coverUrl = coverUrl;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public String getExpressStatus() {
                return expressStatus;
            }

            public void setExpressStatus(String expressStatus) {
                this.expressStatus = expressStatus;
            }

            public String getFreezeStatus() {
                return freezeStatus;
            }

            public void setFreezeStatus(String freezeStatus) {
                this.freezeStatus = freezeStatus;
            }

            public String getIntroduce() {
                return introduce;
            }

            public void setIntroduce(String introduce) {
                this.introduce = introduce;
            }

            public boolean isIsAward() {
                return isAward;
            }

            public void setIsAward(boolean isAward) {
                this.isAward = isAward;
            }

            public boolean isIsCollect() {
                return isCollect;
            }

            public void setIsCollect(boolean isCollect) {
                this.isCollect = isCollect;
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

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public int getSubscribeNum() {
                return subscribeNum;
            }

            public void setSubscribeNum(int subscribeNum) {
                this.subscribeNum = subscribeNum;
            }

            public String getTopCategoryId() {
                return topCategoryId;
            }

            public void setTopCategoryId(String topCategoryId) {
                this.topCategoryId = topCategoryId;
            }

            public String getUpdateDate() {
                return updateDate;
            }

            public void setUpdateDate(String updateDate) {
                this.updateDate = updateDate;
            }

            public int getWordNum() {
                return wordNum;
            }

            public void setWordNum(int wordNum) {
                this.wordNum = wordNum;
            }
        }

        public static class AuthorDataBean {
            /**
             * articleNum : 4
             * authorId : 150
             * authorIntroduction :
             * nickName : 无尽之夏
             * photo : http://101.254.183.67:9980/group1/M00/00/1F/wKiTPlhSE0uAZ7BfAAEuwbmelvg627.jpg
             */

            private int articleNum;
            private String authorId;
            private String authorIntroduction;
            private String nickName;
            private String photo;

            public int getArticleNum() {
                return articleNum;
            }

            public void setArticleNum(int articleNum) {
                this.articleNum = articleNum;
            }

            public String getAuthorId() {
                return authorId;
            }

            public void setAuthorId(String authorId) {
                this.authorId = authorId;
            }

            public String getAuthorIntroduction() {
                return authorIntroduction;
            }

            public void setAuthorIntroduction(String authorIntroduction) {
                this.authorIntroduction = authorIntroduction;
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
        }
    }
}
