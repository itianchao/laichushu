package com.laichushu.book.mvp.home;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * 首页热门
 * Created by wangtong on 2016/11/1.
 */
public class HomeHotModel implements Parcelable {

    /**
     * success : true
     * data : [{"articleId":"16","articleName":"西游记","authorId":"103","authorName":"测试","coverUrl":"http://192.168.147.62/group1/M00/00/00/wKiTPlgNxbuADGT0ABB9bkYLzjI276.jpg","coverName":"Mypsd_29771_201206250844140005B.jpg","status":"0","topCategoryId":"28","topCategoryName":"神话修真","introduce":"","wordNum":500,"subscribeNum":21,"browseNum":3333,"commentNum":99,"awardNum":3,"awardMoney":30},{"articleId":"74","articleName":"乖,摸摸头","authorId":"103","authorName":"测试","coverUrl":"http://192.168.147.62/group1/M00/00/00/wKiTPlgWui2AfU8TAABqYh718yY506.jpg","coverName":"乖,摸摸头.jpg","status":"3","topCategoryId":"18","topCategoryName":"文学","introduce":"feng","wordNum":5000,"subscribeNum":11,"commentNum":2,"awardNum":3,"level":3,"score":6,"awardMoney":30},{"articleId":"16","articleName":"西游记","authorId":"103","authorName":"测试","coverUrl":"http://192.168.147.62/group1/M00/00/00/wKiTPlgNxbuADGT0ABB9bkYLzjI276.jpg","coverName":"Mypsd_29771_201206250844140005B.jpg","status":"0","topCategoryId":"28","topCategoryName":"神话修真","introduce":"","wordNum":500,"subscribeNum":21,"browseNum":3333,"commentNum":99,"awardNum":3,"awardMoney":30}]
     */

    private boolean success;
    /**
     * articleId : 16
     * articleName : 西游记
     * authorId : 103
     * authorName : 测试
     * coverUrl : http://192.168.147.62/group1/M00/00/00/wKiTPlgNxbuADGT0ABB9bkYLzjI276.jpg
     * coverName : Mypsd_29771_201206250844140005B.jpg
     * status : 0
     * topCategoryId : 28
     * topCategoryName : 神话修真
     * introduce :
     * wordNum : 500
     * subscribeNum : 21
     * browseNum : 3333
     * commentNum : 99
     * awardNum : 3
     * awardMoney : 30.0
     */
    private String errMsg;

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

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

    public static class DataBean implements Parcelable {
        private String articleId;
        private String articleName;
        private String authorId;
        private String authorName;
        private String coverUrl;
        private String coverName;
        private String status;
        private String topCategoryId;
        private String topCategoryName;
        private String introduce;
        private int wordNum;
        private int subscribeNum;
        private int browseNum;
        private int commentNum;
        private int awardNum;
        private double awardMoney;
        private boolean isPurchase;
        private boolean isSubscribe;
        private boolean isParticipate;
        private boolean isCollect;
        private boolean isMake;

        public boolean isMake() {
            return isMake;
        }

        public void setMake(boolean make) {
            isMake = make;
        }

        public void setPurchase(boolean purchase) {
            isPurchase = purchase;
        }

        public void setSubscribe(boolean subscribe) {
            isSubscribe = subscribe;
        }

        public void setParticipate(boolean participate) {
            isParticipate = participate;
        }

        public boolean isCollect() {
            return isCollect;
        }

        public void setCollect(boolean collect) {
            isCollect = collect;
        }

        public boolean isParticipate() {
            return isParticipate;
        }

        public void setIsParticipate(boolean isParticipate) {
            this.isParticipate = isParticipate;
        }

        /**
         * level : 3
         * score : 6.0
         * price : 1000.0
         * createDate : 1476341995000
         * updateDate : 1477901107000
         */

        private int level;
        private double score;

        private double price;

        private String createDate;
        private String updateDate;
        /**
         * 活动
         * activityId : 11
         * activityName : 鍏呭�奸�佸ソ绀�
         * beginTime : 1476787389000
         * endTime : 1476787391000
         * imgName : money.gif
         * imgUrl : http://192.168.147.62/group1/M00/00/01/wKiTPlgbCPmACv6lAA1w0N-Cv1A434.gif
         * detail : 鍏�100杩�100
         * applyAmount : 1
         */

        private String activityId;
        private String activityName;
        private String beginTime;
        private String endTime;
        private String imgName;
        private String imgUrl;
        private String detail;
        private int applyAmount;

        public boolean isPurchase() {
            return isPurchase;
        }

        public void setIsPurchase(boolean isPurchase) {
            this.isPurchase = isPurchase;
        }

        public boolean isSubscribe() {
            return isSubscribe;
        }

        public void setIsSubscribe(boolean isSubscribe) {
            this.isSubscribe = isSubscribe;
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

        public double getAwardMoney() {
            return awardMoney;
        }

        public void setAwardMoney(double awardMoney) {
            this.awardMoney = awardMoney;
        }

        public DataBean() {
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

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
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

        public String getActivityId() {
            return activityId;
        }

        public void setActivityId(String activityId) {
            this.activityId = activityId;
        }

        public String getActivityName() {
            return activityName;
        }

        public void setActivityName(String activityName) {
            this.activityName = activityName;
        }

        public String getBeginTime() {
            return beginTime;
        }

        public void setBeginTime(String beginTime) {
            this.beginTime = beginTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getImgName() {
            return imgName;
        }

        public void setImgName(String imgName) {
            this.imgName = imgName;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public int getApplyAmount() {
            return applyAmount;
        }

        public void setApplyAmount(int applyAmount) {
            this.applyAmount = applyAmount;
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
            dest.writeString(this.topCategoryName);
            dest.writeString(this.introduce);
            dest.writeInt(this.wordNum);
            dest.writeInt(this.subscribeNum);
            dest.writeInt(this.browseNum);
            dest.writeInt(this.commentNum);
            dest.writeInt(this.awardNum);
            dest.writeDouble(this.awardMoney);
            dest.writeByte(this.isPurchase ? (byte) 1 : (byte) 0);
            dest.writeByte(this.isSubscribe ? (byte) 1 : (byte) 0);
            dest.writeByte(this.isParticipate ? (byte) 1 : (byte) 0);
            dest.writeByte(this.isCollect ? (byte) 1 : (byte) 0);
            dest.writeByte(this.isMake ? (byte) 1 : (byte) 0);
            dest.writeInt(this.level);
            dest.writeDouble(this.score);
            dest.writeDouble(this.price);
            dest.writeString(this.createDate);
            dest.writeString(this.updateDate);
            dest.writeString(this.activityId);
            dest.writeString(this.activityName);
            dest.writeString(this.beginTime);
            dest.writeString(this.endTime);
            dest.writeString(this.imgName);
            dest.writeString(this.imgUrl);
            dest.writeString(this.detail);
            dest.writeInt(this.applyAmount);
        }

        protected DataBean(Parcel in) {
            this.articleId = in.readString();
            this.articleName = in.readString();
            this.authorId = in.readString();
            this.authorName = in.readString();
            this.coverUrl = in.readString();
            this.coverName = in.readString();
            this.status = in.readString();
            this.topCategoryId = in.readString();
            this.topCategoryName = in.readString();
            this.introduce = in.readString();
            this.wordNum = in.readInt();
            this.subscribeNum = in.readInt();
            this.browseNum = in.readInt();
            this.commentNum = in.readInt();
            this.awardNum = in.readInt();
            this.awardMoney = in.readDouble();
            this.isPurchase = in.readByte() != 0;
            this.isSubscribe = in.readByte() != 0;
            this.isParticipate = in.readByte() != 0;
            this.isCollect = in.readByte() != 0;
            this.isMake = in.readByte() != 0;
            this.level = in.readInt();
            this.score = in.readDouble();
            this.price = in.readDouble();
            this.createDate = in.readString();
            this.updateDate = in.readString();
            this.activityId = in.readString();
            this.activityName = in.readString();
            this.beginTime = in.readString();
            this.endTime = in.readString();
            this.imgName = in.readString();
            this.imgUrl = in.readString();
            this.detail = in.readString();
            this.applyAmount = in.readInt();
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

    public HomeHotModel() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.success ? (byte) 1 : (byte) 0);
        dest.writeString(this.errMsg);
        dest.writeTypedList(this.data);
    }

    protected HomeHotModel(Parcel in) {
        this.success = in.readByte() != 0;
        this.errMsg = in.readString();
        this.data = in.createTypedArrayList(DataBean.CREATOR);
    }

    public static final Creator<HomeHotModel> CREATOR = new Creator<HomeHotModel>() {
        @Override
        public HomeHotModel createFromParcel(Parcel source) {
            return new HomeHotModel(source);
        }

        @Override
        public HomeHotModel[] newArray(int size) {
            return new HomeHotModel[size];
        }
    };
}
