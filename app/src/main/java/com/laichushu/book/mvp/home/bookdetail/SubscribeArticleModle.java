package com.laichushu.book.mvp.home.bookdetail;

/**
 * 订阅
 * Created by wangtong on 2016/11/3.
 */
public class SubscribeArticleModle {
    private boolean success;
    private String errMsg;
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorMsg() {
        return errMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errMsg = errorMsg;
    }
}