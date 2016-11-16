package com.laichushu.book.mvp.allcomment;

/**
 * 发送评论
 * Created by wangtong on 2016/11/4.
 */
public class SendCommentMoudle {
    private boolean success;
    private String errMsg;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
