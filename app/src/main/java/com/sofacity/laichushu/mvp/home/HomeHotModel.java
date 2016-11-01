package com.sofacity.laichushu.mvp.home;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * 首页热门
 * Created by wangtong on 2016/11/1.
 */
public class HomeHotModel {

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
        }

        public DataBean() {
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
        }

        public static final Parcelable.Creator<DataBean> CREATOR = new Parcelable.Creator<DataBean>() {
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
}
