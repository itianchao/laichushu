package com.laichushu.book.mvp.topicdetail;

import com.laichushu.book.mvp.commentdetail.CommentDetailModle;

import java.util.ArrayList;

/**
 * 话题详情数据模型
 * Created by wangtong on 2016/10/12.
 */
public class TopicdetailModel {

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

    public static class DataBean {
        private String id;
        private String scoreId;
        private String userId;
        private String articleId;
        private String content;
        private int replyNum;
        private int likeNum;
        private String nickName;
        private String photo;
        private String createDate;
        private boolean isLike;

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

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public boolean isIsLike() {
            return isLike;
        }

        public void setIsLike(boolean isLike) {
            this.isLike = isLike;
        }
    }
}
