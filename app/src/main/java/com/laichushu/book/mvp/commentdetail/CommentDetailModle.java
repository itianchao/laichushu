package com.laichushu.book.mvp.commentdetail;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * 评价详情 modle
 * Created by wangtong on 2016/11/4.
 */
public class CommentDetailModle {

    /**
     * success : true
     * data : [{"scoreId":"3","userId":"105","articleId":"74","content":"杩欐湰涔︿笉閿欑殑鏍峰瓙","replyNum":100,"likeNum":123,"nickName":"澶у啺","photo":"http://192.168.147.62/group1/M00/00/01/wKiTPlgYWiWAAJ8oAALeCroPT5I540.jpg","createDate":"2016-10-31 15:38:42","isLike":false}]
     */

    private boolean success;
    /**
     * scoreId : 3
     * userId : 105
     * articleId : 74
     * content : 杩欐湰涔︿笉閿欑殑鏍峰瓙
     * replyNum : 100
     * likeNum : 123
     * nickName : 澶у啺
     * photo : http://192.168.147.62/group1/M00/00/01/wKiTPlgYWiWAAJ8oAALeCroPT5I540.jpg
     * createDate : 2016-10-31 15:38:42
     * isLike : false
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
        private String id;
        private String userId;
        private String articleId;
        private String sourceId;
        private String content;
        private int replyNum;
        private int likeNum;
        private String nickName;
        private String photo;
        private String commentTime;
        private boolean isLike;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public boolean isLike() {
            return isLike;
        }

        public void setLike(boolean like) {
            isLike = like;
        }

        public boolean isIsLike() {
            return isLike;
        }

        public void setIsLike(boolean isLike) {
            this.isLike = isLike;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.userId);
            dest.writeString(this.articleId);
            dest.writeString(this.sourceId);
            dest.writeString(this.content);
            dest.writeInt(this.replyNum);
            dest.writeInt(this.likeNum);
            dest.writeString(this.nickName);
            dest.writeString(this.photo);
            dest.writeString(this.commentTime);
            dest.writeByte(this.isLike ? (byte) 1 : (byte) 0);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.id = in.readString();
            this.userId = in.readString();
            this.articleId = in.readString();
            this.sourceId = in.readString();
            this.content = in.readString();
            this.replyNum = in.readInt();
            this.likeNum = in.readInt();
            this.nickName = in.readString();
            this.photo = in.readString();
            this.commentTime = in.readString();
            this.isLike = in.readByte() != 0;
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
