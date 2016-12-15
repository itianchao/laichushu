package com.laichushu.book.mvp.mechanismsearch;

/**
 * 机构搜索数据模型
 * Created by wangtong on 2016/12/15.
 */
public class MechanismSearchModle {

    /**
     * success : 请求成功
     * errMsg : 错误信息
     */

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
