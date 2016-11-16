package com.laichushu.book.mvp.forgetpwd;

/**
 * 重置密码数据模型
 * Created by wangtong on 2016/10/12.
 */
public class ForgetPwdModel {

    /**
     * success : false
     * errMsg : 错误信息
     * userId : 112
     * type : 4
     * token : H7a1cis
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
