package com.sofacity.laichushu.mvp.allcomment;

/**
 * 发送评论
 * Created by wangtong on 2016/11/4.
 */
public class SendCommentMoudle {
    private boolean success;
    private String errorMsg;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
