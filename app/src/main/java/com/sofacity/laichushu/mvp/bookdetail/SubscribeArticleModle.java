package com.sofacity.laichushu.mvp.bookdetail;

/**
 * 订阅
 * Created by wangtong on 2016/11/3.
 */
public class SubscribeArticleModle {
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
