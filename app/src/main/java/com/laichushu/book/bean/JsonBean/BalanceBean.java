package com.laichushu.book.bean.JsonBean;

/**
 * 查询余额
 * Created by wangtong on 2016/11/8.
 */

public class BalanceBean {

    /**
     * success : true
     * data : 0.0
     */

    private boolean success;
    private String errMsg;
    private double data;

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

    public double getData() {
        return data;
    }

    public void setData(double data) {
        this.data = data;
    }
}
