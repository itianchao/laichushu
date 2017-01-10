package com.laichushu.book.mvp.msg.topic.topicdetail;

import android.os.Parcel;
import android.os.Parcelable;

import com.laichushu.book.mvp.home.commentdetail.CommentDetailModle;

import java.util.ArrayList;

/**
 * 话题详情数据模型
 * Created by wangtong on 2016/10/12.
 */
public class TopicdetailModel implements Parcelable {

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

    private ArrayList<CommentDetailModle.DataBean> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ArrayList<CommentDetailModle.DataBean> getData() {
        return data;
    }

    public void setData(ArrayList<CommentDetailModle.DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Parcelable {
        private String id;
        private String scoreId;
        private String userId;
        private String articleId;
        private String content;
        private int replyNum;
        private int likeNum;
        private String editorId;
        private String nickName;
        private String photo;
        private String commentTime;
        private boolean isLike;

        public String getEditorId() {
            return editorId;
        }

        public void setEditorId(String editorId) {
            this.editorId = editorId;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public boolean isLike() {
            return isLike;
        }

        public void setLike(boolean like) {
            isLike = like;
        }

        public String getScoreId() {
            return scoreId;
        }

        public void setScoreId(String scoreId) {
            this.scoreId = scoreId;
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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.scoreId);
            dest.writeString(this.userId);
            dest.writeString(this.articleId);
            dest.writeString(this.content);
            dest.writeInt(this.replyNum);
            dest.writeInt(this.likeNum);
            dest.writeString(this.editorId);
            dest.writeString(this.nickName);
            dest.writeString(this.photo);
            dest.writeString(this.commentTime);
            dest.writeByte(this.isLike ? (byte) 1 : (byte) 0);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.id = in.readString();
            this.scoreId = in.readString();
            this.userId = in.readString();
            this.articleId = in.readString();
            this.content = in.readString();
            this.replyNum = in.readInt();
            this.likeNum = in.readInt();
            this.editorId = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.success ? (byte) 1 : (byte) 0);
        dest.writeString(this.errMsg);
        dest.writeList(this.data);
    }

    public TopicdetailModel() {
    }

    protected TopicdetailModel(Parcel in) {
        this.success = in.readByte() != 0;
        this.errMsg = in.readString();
        this.data = new ArrayList<CommentDetailModle.DataBean>();
        in.readList(this.data, CommentDetailModle.DataBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<TopicdetailModel> CREATOR = new Parcelable.Creator<TopicdetailModel>() {
        @Override
        public TopicdetailModel createFromParcel(Parcel source) {
            return new TopicdetailModel(source);
        }

        @Override
        public TopicdetailModel[] newArray(int size) {
            return new TopicdetailModel[size];
        }
    };
}
