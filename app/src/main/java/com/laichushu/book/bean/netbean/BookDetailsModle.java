package com.laichushu.book.bean.netbean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PCPC on 2016/12/9.
 */

public class BookDetailsModle implements Parcelable {

    /**
     * success : true
     * data : {"success":true,"articleData":{"articleId":"216","articleName":"愿无岁月可回头","authorId":"150","authorName":"无尽之夏","coverUrl":"http://101.254.183.67:9980/group1/M00/00/1A/wKiTPlhKmfCAWIm6AAEuwbmelvg817.jpg","coverName":"20161209194753.jpg","status":"1","topCategoryId":"1","introduce":"的点点滴滴点点滴滴","freezeStatus":"1","expressStatus":"0","wordNum":0,"subscribeNum":3,"browseNum":12,"commentNum":7,"awardNum":45,"level":4,"score":8,"awardMoney":1142.5,"createDate":"2016-12-09 19:47","updateDate":"2016-12-27 19:56","isPurchase":false,"isSubscribe":true,"isCollect":false,"isAward":false},"authorData":{"authorId":"150","nickName":"无尽之夏","photo":"http://101.254.183.67:9980/group1/M00/00/2C/wKiTPlhh3M2AeuqXAADCsbGO7uw091.jpg","authorIntroduction":"","articleNum":5,"level":"铜牌作家","levelType":"3"},"bestLikeData":[{"articleId":"274","articleName":"图","authorId":"152","authorName":"测试","coverUrl":"http://101.254.183.67:9980/group1/M00/00/21/wKiTPlhSjA-AQCvtAAAEwg7_-Yk933.jpg","coverName":"cover.jpg","status":"1","topCategoryId":"64","topCategoryName":"短篇","freezeStatus":"1","permission":"3","materialPermission":"1","wordNum":0,"commentNum":2,"awardNum":0,"level":5,"score":10,"awardMoney":0},{"articleId":"231","articleName":"啊啊","authorId":"159","authorName":"eiowoe","coverUrl":"http://101.254.183.67:9980/group1/M00/00/1B/wKiTPlhLyJGAZlnxAAFBsqEBiEM694.jpg","status":"1","topCategoryId":"64","topCategoryName":"短篇","freezeStatus":"1","permission":"0","materialPermission":"1","wordNum":0,"commentNum":1,"awardNum":0,"level":2,"score":4,"awardMoney":0},{"articleId":"263","articleName":"你好","authorId":"175","authorName":"大酒神","coverUrl":"http://101.254.183.67:9980/group1/M00/00/1F/wKiTPlhSB4mAK5sFAADnEgoKhPs710.jpg","coverName":"cover.jpg","status":"1","topCategoryId":"64","topCategoryName":"短篇","freezeStatus":"1","permission":"3","materialPermission":"2","wordNum":0,"commentNum":3,"awardNum":0,"level":4,"score":8,"awardMoney":0},{"articleId":"284","articleName":"收藏测试","authorId":"180","authorName":"逗你玩","coverUrl":"http://101.254.183.67:9980/group1/M00/00/24/wKiTPlhTk2eAQRWMAAFYAFiQlHI312.jpg","status":"1","topCategoryId":"64","topCategoryName":"短篇","freezeStatus":"1","permission":"0","materialPermission":"1","wordNum":0,"commentNum":1,"awardNum":0,"level":5,"score":10,"awardMoney":0}],"scoreReaderData":[{"sourceId":"89","userId":"151","articleId":"216","content":"某人的微笑，一如那年的夏天，阳光明媚","replyNum":6,"likeNum":3,"nickName":"好好先生","photo":"http://101.254.183.67:9980/group1/M00/00/24/wKiTPlhY62SAKLt0AAFaY0hYxBg407.jpg","commentTime":"2016-12-15 14:08:30","isLike":false,"level":"铜牌作家","levelType":"3"},{"sourceId":"100","userId":"159","articleId":"216","content":"请留下你对本书的想法及感受","replyNum":9,"likeNum":3,"nickName":"eiowoe","photo":"http://101.254.183.67:9980/group1/M00/00/27/wKiTPlhbev2AaYIaAALwRZGKJpE163.jpg","commentTime":"2016-12-16 13:31:24","isLike":false,"level":"铜牌作家","levelType":"3"},{"sourceId":"97","userId":"180","articleId":"216","content":"狗哈哈哈","replyNum":2,"likeNum":2,"nickName":"逗你玩","photo":"http://101.254.183.67:9980/group1/M00/00/22/wKiTPlhTUauAXN3_AAAH6nNWI3k479.jpg","commentTime":"2016-12-16 10:56:53","isLike":false,"level":"铜牌作家","levelType":"3"},{"sourceId":"124","userId":"155","articleId":"216","content":"哈哈哈","replyNum":4,"likeNum":2,"nickName":"一毛","photo":"http://101.254.183.67:9980/group1/M00/00/1F/wKiTPlhRIoeADHcOAAD3uYI7bCA432.jpg","commentTime":"2016-12-21 14:57:59","isLike":false,"level":"铜牌作家","levelType":"3"},{"sourceId":"98","userId":"175","articleId":"216","content":"你好","replyNum":5,"likeNum":1,"nickName":"大酒神","photo":"http://101.254.183.67:9980/group1/M00/00/23/wKiTPlhXqMOAafndAACiakRLsdc478.jpg","commentTime":"2016-12-16 11:15:50","isLike":false,"level":"铜牌作家","levelType":"3"}],"scoreCattleData":[{"sourceId":"121","userId":"195","articleId":"216","content":"我是大咖哦","replyNum":0,"likeNum":0,"nickName":"永生","photo":"","commentTime":"2016-12-20 15:46:12","isLike":false,"level":"铜牌作家","levelType":"3"}]}
     */

    private boolean success;
    private String errMsg;
    private DataBean data;

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

    public static class DataBean implements Parcelable {
        /**
         * success : true
         * articleData : {"articleId":"216","articleName":"愿无岁月可回头","authorId":"150","authorName":"无尽之夏","coverUrl":"http://101.254.183.67:9980/group1/M00/00/1A/wKiTPlhKmfCAWIm6AAEuwbmelvg817.jpg","coverName":"20161209194753.jpg","status":"1","topCategoryId":"1","introduce":"的点点滴滴点点滴滴","freezeStatus":"1","expressStatus":"0","wordNum":0,"subscribeNum":3,"browseNum":12,"commentNum":7,"awardNum":45,"level":4,"score":8,"awardMoney":1142.5,"createDate":"2016-12-09 19:47","updateDate":"2016-12-27 19:56","isPurchase":false,"isSubscribe":true,"isCollect":false,"isAward":false}
         * authorData : {"authorId":"150","nickName":"无尽之夏","photo":"http://101.254.183.67:9980/group1/M00/00/2C/wKiTPlhh3M2AeuqXAADCsbGO7uw091.jpg","authorIntroduction":"","articleNum":5,"level":"铜牌作家","levelType":"3"}
         * bestLikeData : [{"articleId":"274","articleName":"图","authorId":"152","authorName":"测试","coverUrl":"http://101.254.183.67:9980/group1/M00/00/21/wKiTPlhSjA-AQCvtAAAEwg7_-Yk933.jpg","coverName":"cover.jpg","status":"1","topCategoryId":"64","topCategoryName":"短篇","freezeStatus":"1","permission":"3","materialPermission":"1","wordNum":0,"commentNum":2,"awardNum":0,"level":5,"score":10,"awardMoney":0},{"articleId":"231","articleName":"啊啊","authorId":"159","authorName":"eiowoe","coverUrl":"http://101.254.183.67:9980/group1/M00/00/1B/wKiTPlhLyJGAZlnxAAFBsqEBiEM694.jpg","status":"1","topCategoryId":"64","topCategoryName":"短篇","freezeStatus":"1","permission":"0","materialPermission":"1","wordNum":0,"commentNum":1,"awardNum":0,"level":2,"score":4,"awardMoney":0},{"articleId":"263","articleName":"你好","authorId":"175","authorName":"大酒神","coverUrl":"http://101.254.183.67:9980/group1/M00/00/1F/wKiTPlhSB4mAK5sFAADnEgoKhPs710.jpg","coverName":"cover.jpg","status":"1","topCategoryId":"64","topCategoryName":"短篇","freezeStatus":"1","permission":"3","materialPermission":"2","wordNum":0,"commentNum":3,"awardNum":0,"level":4,"score":8,"awardMoney":0},{"articleId":"284","articleName":"收藏测试","authorId":"180","authorName":"逗你玩","coverUrl":"http://101.254.183.67:9980/group1/M00/00/24/wKiTPlhTk2eAQRWMAAFYAFiQlHI312.jpg","status":"1","topCategoryId":"64","topCategoryName":"短篇","freezeStatus":"1","permission":"0","materialPermission":"1","wordNum":0,"commentNum":1,"awardNum":0,"level":5,"score":10,"awardMoney":0}]
         * scoreReaderData : [{"sourceId":"89","userId":"151","articleId":"216","content":"某人的微笑，一如那年的夏天，阳光明媚","replyNum":6,"likeNum":3,"nickName":"好好先生","photo":"http://101.254.183.67:9980/group1/M00/00/24/wKiTPlhY62SAKLt0AAFaY0hYxBg407.jpg","commentTime":"2016-12-15 14:08:30","isLike":false,"level":"铜牌作家","levelType":"3"},{"sourceId":"100","userId":"159","articleId":"216","content":"请留下你对本书的想法及感受","replyNum":9,"likeNum":3,"nickName":"eiowoe","photo":"http://101.254.183.67:9980/group1/M00/00/27/wKiTPlhbev2AaYIaAALwRZGKJpE163.jpg","commentTime":"2016-12-16 13:31:24","isLike":false,"level":"铜牌作家","levelType":"3"},{"sourceId":"97","userId":"180","articleId":"216","content":"狗哈哈哈","replyNum":2,"likeNum":2,"nickName":"逗你玩","photo":"http://101.254.183.67:9980/group1/M00/00/22/wKiTPlhTUauAXN3_AAAH6nNWI3k479.jpg","commentTime":"2016-12-16 10:56:53","isLike":false,"level":"铜牌作家","levelType":"3"},{"sourceId":"124","userId":"155","articleId":"216","content":"哈哈哈","replyNum":4,"likeNum":2,"nickName":"一毛","photo":"http://101.254.183.67:9980/group1/M00/00/1F/wKiTPlhRIoeADHcOAAD3uYI7bCA432.jpg","commentTime":"2016-12-21 14:57:59","isLike":false,"level":"铜牌作家","levelType":"3"},{"sourceId":"98","userId":"175","articleId":"216","content":"你好","replyNum":5,"likeNum":1,"nickName":"大酒神","photo":"http://101.254.183.67:9980/group1/M00/00/23/wKiTPlhXqMOAafndAACiakRLsdc478.jpg","commentTime":"2016-12-16 11:15:50","isLike":false,"level":"铜牌作家","levelType":"3"}]
         * scoreCattleData : [{"sourceId":"121","userId":"195","articleId":"216","content":"我是大咖哦","replyNum":0,"likeNum":0,"nickName":"永生","photo":"","commentTime":"2016-12-20 15:46:12","isLike":false,"level":"铜牌作家","levelType":"3"}]
         */

        private boolean success;
        private ArticleDataBean articleData;
        private AuthorDataBean authorData;
        private List<BestLikeDataBean> bestLikeData;
        private List<ScoreReaderDataBean> scoreReaderData;
        private List<ScoreCattleDataBean> scoreCattleData;

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

        public List<BestLikeDataBean> getBestLikeData() {
            return bestLikeData;
        }

        public void setBestLikeData(List<BestLikeDataBean> bestLikeData) {
            this.bestLikeData = bestLikeData;
        }

        public List<ScoreReaderDataBean> getScoreReaderData() {
            return scoreReaderData;
        }

        public void setScoreReaderData(List<ScoreReaderDataBean> scoreReaderData) {
            this.scoreReaderData = scoreReaderData;
        }

        public List<ScoreCattleDataBean> getScoreCattleData() {
            return scoreCattleData;
        }

        public void setScoreCattleData(List<ScoreCattleDataBean> scoreCattleData) {
            this.scoreCattleData = scoreCattleData;
        }

        public static class ArticleDataBean implements Parcelable {
            /**
             * articleId : 216
             * articleName : 愿无岁月可回头
             * authorId : 150
             * authorName : 无尽之夏
             * coverUrl : http://101.254.183.67:9980/group1/M00/00/1A/wKiTPlhKmfCAWIm6AAEuwbmelvg817.jpg
             * coverName : 20161209194753.jpg
             * status : 1
             * topCategoryId : 1
             * introduce : 的点点滴滴点点滴滴
             * freezeStatus : 1
             * expressStatus : 0
             * wordNum : 0
             * subscribeNum : 3
             * browseNum : 12
             * commentNum : 7
             * awardNum : 45
             * level : 4
             * score : 8
             * awardMoney : 1142.5
             * createDate : 2016-12-09 19:47
             * updateDate : 2016-12-27 19:56
             * isPurchase : false
             * isSubscribe : true
             * isCollect : false
             * isAward : false
             */

            private String articleId;
            private String name;
            private String topCategoryName;
            private String articleName;
            private String authorId;
            private String authorName;
            private String coverUrl;
            private String coverName;
            private String status;
            private String topCategoryId;
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
            private double awardMoney;
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

            public String getTopCategoryName() {
                return topCategoryName;
            }

            public void setTopCategoryName(String topCategoryName) {
                this.topCategoryName = topCategoryName;
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

            public boolean isIsAward() {
                return isAward;
            }

            public void setIsAward(boolean isAward) {
                this.isAward = isAward;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.articleId);
                dest.writeString(this.articleName);
                dest.writeString(this.authorId);
                dest.writeString(this.authorName);
                dest.writeString(this.coverUrl);
                dest.writeString(this.coverName);
                dest.writeString(this.status);
                dest.writeString(this.topCategoryId);
                dest.writeString(this.introduce);
                dest.writeString(this.freezeStatus);
                dest.writeString(this.expressStatus);
                dest.writeInt(this.wordNum);
                dest.writeInt(this.subscribeNum);
                dest.writeInt(this.browseNum);
                dest.writeInt(this.commentNum);
                dest.writeInt(this.awardNum);
                dest.writeInt(this.level);
                dest.writeInt(this.score);
                dest.writeDouble(this.awardMoney);
                dest.writeString(this.createDate);
                dest.writeString(this.updateDate);
                dest.writeByte(this.isPurchase ? (byte) 1 : (byte) 0);
                dest.writeByte(this.isSubscribe ? (byte) 1 : (byte) 0);
                dest.writeByte(this.isCollect ? (byte) 1 : (byte) 0);
                dest.writeByte(this.isAward ? (byte) 1 : (byte) 0);
            }

            public ArticleDataBean() {
            }

            protected ArticleDataBean(Parcel in) {
                this.articleId = in.readString();
                this.articleName = in.readString();
                this.authorId = in.readString();
                this.authorName = in.readString();
                this.coverUrl = in.readString();
                this.coverName = in.readString();
                this.status = in.readString();
                this.topCategoryId = in.readString();
                this.introduce = in.readString();
                this.freezeStatus = in.readString();
                this.expressStatus = in.readString();
                this.wordNum = in.readInt();
                this.subscribeNum = in.readInt();
                this.browseNum = in.readInt();
                this.commentNum = in.readInt();
                this.awardNum = in.readInt();
                this.level = in.readInt();
                this.score = in.readInt();
                this.awardMoney = in.readDouble();
                this.createDate = in.readString();
                this.updateDate = in.readString();
                this.isPurchase = in.readByte() != 0;
                this.isSubscribe = in.readByte() != 0;
                this.isCollect = in.readByte() != 0;
                this.isAward = in.readByte() != 0;
            }

            public static final Creator<ArticleDataBean> CREATOR = new Creator<ArticleDataBean>() {
                @Override
                public ArticleDataBean createFromParcel(Parcel source) {
                    return new ArticleDataBean(source);
                }

                @Override
                public ArticleDataBean[] newArray(int size) {
                    return new ArticleDataBean[size];
                }
            };
        }

        public static class AuthorDataBean implements Parcelable {
            /**
             * authorId : 150
             * nickName : 无尽之夏
             * photo : http://101.254.183.67:9980/group1/M00/00/2C/wKiTPlhh3M2AeuqXAADCsbGO7uw091.jpg
             * authorIntroduction :
             * articleNum : 5
             * level : 铜牌作家
             * levelType : 3
             */

            private String authorId;
            private String nickName;
            private String photo;
            private String authorIntroduction;
            private int articleNum;
            private String level;
            private String levelType;

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

            public String getAuthorIntroduction() {
                return authorIntroduction;
            }

            public void setAuthorIntroduction(String authorIntroduction) {
                this.authorIntroduction = authorIntroduction;
            }

            public int getArticleNum() {
                return articleNum;
            }

            public void setArticleNum(int articleNum) {
                this.articleNum = articleNum;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }

            public String getLevelType() {
                return levelType;
            }

            public void setLevelType(String levelType) {
                this.levelType = levelType;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.authorId);
                dest.writeString(this.nickName);
                dest.writeString(this.photo);
                dest.writeString(this.authorIntroduction);
                dest.writeInt(this.articleNum);
                dest.writeString(this.level);
                dest.writeString(this.levelType);
            }

            public AuthorDataBean() {
            }

            protected AuthorDataBean(Parcel in) {
                this.authorId = in.readString();
                this.nickName = in.readString();
                this.photo = in.readString();
                this.authorIntroduction = in.readString();
                this.articleNum = in.readInt();
                this.level = in.readString();
                this.levelType = in.readString();
            }

            public static final Creator<AuthorDataBean> CREATOR = new Creator<AuthorDataBean>() {
                @Override
                public AuthorDataBean createFromParcel(Parcel source) {
                    return new AuthorDataBean(source);
                }

                @Override
                public AuthorDataBean[] newArray(int size) {
                    return new AuthorDataBean[size];
                }
            };
        }

        public static class BestLikeDataBean {
            /**
             * articleId : 274
             * articleName : 图
             * authorId : 152
             * authorName : 测试
             * coverUrl : http://101.254.183.67:9980/group1/M00/00/21/wKiTPlhSjA-AQCvtAAAEwg7_-Yk933.jpg
             * coverName : cover.jpg
             * status : 1
             * topCategoryId : 64
             * topCategoryName : 短篇
             * freezeStatus : 1
             * permission : 3
             * materialPermission : 1
             * wordNum : 0
             * commentNum : 2
             * awardNum : 0
             * level : 5
             * score : 10
             * awardMoney : 0
             */

            private String articleId;
            private String articleName;
            private String authorId;
            private String authorName;
            private String coverUrl;
            private String coverName;
            private String status;
            private String topCategoryId;
            private String topCategoryName;
            private String freezeStatus;
            private String permission;
            private String materialPermission;
            private int wordNum;
            private int commentNum;
            private int awardNum;
            private int level;
            private int score;
            private int awardMoney;

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

            public String getFreezeStatus() {
                return freezeStatus;
            }

            public void setFreezeStatus(String freezeStatus) {
                this.freezeStatus = freezeStatus;
            }

            public String getPermission() {
                return permission;
            }

            public void setPermission(String permission) {
                this.permission = permission;
            }

            public String getMaterialPermission() {
                return materialPermission;
            }

            public void setMaterialPermission(String materialPermission) {
                this.materialPermission = materialPermission;
            }

            public int getWordNum() {
                return wordNum;
            }

            public void setWordNum(int wordNum) {
                this.wordNum = wordNum;
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
        }

        public static class ScoreReaderDataBean {
            /**
             * sourceId : 89
             * userId : 151
             * articleId : 216
             * content : 某人的微笑，一如那年的夏天，阳光明媚
             * replyNum : 6
             * likeNum : 3
             * nickName : 好好先生
             * photo : http://101.254.183.67:9980/group1/M00/00/24/wKiTPlhY62SAKLt0AAFaY0hYxBg407.jpg
             * commentTime : 2016-12-15 14:08:30
             * isLike : false
             * level : 铜牌作家
             * levelType : 3
             */

            private String sourceId;
            private String userId;
            private String articleId;
            private String content;
            private int replyNum;
            private int likeNum;
            private String nickName;
            private String photo;
            private String commentTime;
            private boolean isLike;
            private String level;
            private String levelType;

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

            public String getArticleId() {
                return articleId;
            }

            public void setArticleId(String articleId) {
                this.articleId = articleId;
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

            public String getCommentTime() {
                return commentTime;
            }

            public void setCommentTime(String commentTime) {
                this.commentTime = commentTime;
            }

            public boolean isIsLike() {
                return isLike;
            }

            public void setIsLike(boolean isLike) {
                this.isLike = isLike;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }

            public String getLevelType() {
                return levelType;
            }

            public void setLevelType(String levelType) {
                this.levelType = levelType;
            }
        }

        public static class ScoreCattleDataBean {
            /**
             * sourceId : 121
             * userId : 195
             * articleId : 216
             * content : 我是大咖哦
             * replyNum : 0
             * likeNum : 0
             * nickName : 永生
             * photo :
             * commentTime : 2016-12-20 15:46:12
             * isLike : false
             * level : 铜牌作家
             * levelType : 3
             */

            private String sourceId;
            private String userId;
            private String articleId;
            private String content;
            private int replyNum;
            private int likeNum;
            private String nickName;
            private String photo;
            private String commentTime;
            private boolean isLike;
            private String level;
            private String levelType;

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

            public String getArticleId() {
                return articleId;
            }

            public void setArticleId(String articleId) {
                this.articleId = articleId;
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

            public String getCommentTime() {
                return commentTime;
            }

            public void setCommentTime(String commentTime) {
                this.commentTime = commentTime;
            }

            public boolean isIsLike() {
                return isLike;
            }

            public void setIsLike(boolean isLike) {
                this.isLike = isLike;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }

            public String getLevelType() {
                return levelType;
            }

            public void setLevelType(String levelType) {
                this.levelType = levelType;
            }
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeByte(this.success ? (byte) 1 : (byte) 0);
            dest.writeParcelable(this.articleData, flags);
            dest.writeParcelable(this.authorData, flags);
            dest.writeList(this.bestLikeData);
            dest.writeList(this.scoreReaderData);
            dest.writeList(this.scoreCattleData);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.success = in.readByte() != 0;
            this.articleData = in.readParcelable(ArticleDataBean.class.getClassLoader());
            this.authorData = in.readParcelable(AuthorDataBean.class.getClassLoader());
            this.bestLikeData = new ArrayList<BestLikeDataBean>();
            in.readList(this.bestLikeData, BestLikeDataBean.class.getClassLoader());
            this.scoreReaderData = new ArrayList<ScoreReaderDataBean>();
            in.readList(this.scoreReaderData, ScoreReaderDataBean.class.getClassLoader());
            this.scoreCattleData = new ArrayList<ScoreCattleDataBean>();
            in.readList(this.scoreCattleData, ScoreCattleDataBean.class.getClassLoader());
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel source) {
                return new DataBean(source);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.success ? (byte) 1 : (byte) 0);
        dest.writeString(this.errMsg);
        dest.writeParcelable(this.data, flags);
    }

    public BookDetailsModle() {
    }

    protected BookDetailsModle(Parcel in) {
        this.success = in.readByte() != 0;
        this.errMsg = in.readString();
        this.data = in.readParcelable(DataBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<BookDetailsModle> CREATOR = new Parcelable.Creator<BookDetailsModle>() {
        @Override
        public BookDetailsModle createFromParcel(Parcel source) {
            return new BookDetailsModle(source);
        }

        @Override
        public BookDetailsModle[] newArray(int size) {
            return new BookDetailsModle[size];
        }
    };
}