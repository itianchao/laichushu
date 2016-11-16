package com.laichushu.book.mvp.home;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * home 全部
 * Created by wangtong on 2016/11/2.
 */
public class HomeAllModel implements Parcelable {

    /**
     * success : true
     * data : [{"articleId":"69","articleName":"涓夊浗婕斾箟","authorId":"103","authorName":"娴嬭瘯","status":"1","introduce":"涓�缇ょ敺浜哄コ浜轰簰鐩告挄閫肩殑鏁呬簨","subscribeNum":68,"browseNum":555,"commentNum":48,"awardNum":9,"price":200,"awardMoney":90}]
     */

    private boolean success;
    /**
     * articleId : 69
     * articleName : 涓夊浗婕斾箟
     * authorId : 103
     * authorName : 娴嬭瘯
     * status : 1
     * introduce : 涓�缇ょ敺浜哄コ浜轰簰鐩告挄閫肩殑鏁呬簨
     * subscribeNum : 68
     * browseNum : 555
     * commentNum : 48
     * awardNum : 9
     * price : 200.0
     * awardMoney : 90.0
     */
    private String errMsg;

    private ArrayList<DataBean> data;


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
        private int subscribeNum;
        private int commentNum;
        private int awardNum;
        private double price;
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

        public int getSubscribeNum() {
            return subscribeNum;
        }

        public void setSubscribeNum(int subscribeNum) {
            this.subscribeNum = subscribeNum;
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

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
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
            dest.writeInt(this.subscribeNum);
            dest.writeInt(this.commentNum);
            dest.writeInt(this.awardNum);
            dest.writeDouble(this.price);
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
            this.subscribeNum = in.readInt();
            this.commentNum = in.readInt();
            this.awardNum = in.readInt();
            this.price = in.readDouble();
            this.awardMoney = in.readDouble();
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
        dest.writeTypedList(this.data);
    }

    public HomeAllModel() {
    }

    protected HomeAllModel(Parcel in) {
        this.success = in.readByte() != 0;
        this.errMsg = in.readString();
        this.data = in.createTypedArrayList(DataBean.CREATOR);
    }

    public static final Creator<HomeAllModel> CREATOR = new Creator<HomeAllModel>() {
        @Override
        public HomeAllModel createFromParcel(Parcel source) {
            return new HomeAllModel(source);
        }

        @Override
        public HomeAllModel[] newArray(int size) {
            return new HomeAllModel[size];
        }
    };
}
