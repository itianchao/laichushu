package com.laichushu.book.bean.JsonBean;

/**
 * Created by wangtong on 2016/12/14.
 */

public class UrlResult {
    private boolean success;
    private String errMsg;
    /**
     * data : http://101.254.183.67:9980/group1/M00/00/1E/wKiTPlhQ5faAZJi6AAFLo-Rs6jQ68.epub
     */

    private String data;

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
