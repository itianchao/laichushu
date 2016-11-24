package com.laichushu.book.bean.JsonBean;

/**
 * 机构列表modle
 * Created by wt on 2016/11/24.
 */

public class MechanismListBean {
    private boolean success;
    private String errMsg;

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
