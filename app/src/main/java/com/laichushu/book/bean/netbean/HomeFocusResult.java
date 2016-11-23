package com.laichushu.book.bean.netbean;

/**
 * Created by PCPC on 2016/11/23.
 */

public class HomeFocusResult {
    private boolean success;
    private String errorCode, errorMsg;

    public HomeFocusResult(boolean success, String errorCode, String errorMsg) {
        this.success = success;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
