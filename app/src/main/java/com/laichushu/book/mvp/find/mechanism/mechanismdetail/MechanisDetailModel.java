package com.laichushu.book.mvp.find.mechanism.mechanismdetail;

/**
 * 机构详情 modle
 * Created by wangtong on 2016/11/9.
 */

public class MechanisDetailModel {


    private boolean success;
    private String errMsg;

    public MechanisDetailModel(boolean success, String errMsg) {
        this.success = success;
        this.errMsg = errMsg;
    }

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
