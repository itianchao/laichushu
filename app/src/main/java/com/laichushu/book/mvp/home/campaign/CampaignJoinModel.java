package com.laichushu.book.mvp.home.campaign;

/**
 * 参加活动
 * Created by wangtong on 2016/11/4.
 */
public class CampaignJoinModel {
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
