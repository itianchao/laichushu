package com.laichushu.book.mvp.home.bookdetail;

/**
 * 图书详情 - 作者详情
 * Created by wangtong on 2016/11/3.
 */
public class AuthorDetailModle {

    /**
     * success : true
     * data : {"authorId":"1","nickName":"admin","photo":"010-88888888","articleNum":19}
     */

    private boolean success;
    /**
     * authorId : 1 id
     * nickName : admin 名字
     * photo : 010-88888888 头像
     * articleNum : 19  发表数
     * authorIntroduction 简介
     */
    private String errMsg;

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    private DataBean data;

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

    public static class DataBean {
        private String authorId;
        private String nickName;
        private String photo;
        private int articleNum;
        private String authorIntroduction;

        public String getAuthorIntroduction() {
            return authorIntroduction;
        }

        public void setAuthorIntroduction(String authorIntroduction) {
            this.authorIntroduction = authorIntroduction;
        }

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
