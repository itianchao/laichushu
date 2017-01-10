package com.laichushu.book.mvp.entry.forgetpwd;

/**
 * 重置密码 验证码 数据模型
 * Created by wangtong on 2016/10/12.
 */
public class ForgetPwdCodeModel {

    /**
     * success : false
     * errMsg : 错误信息
     * userId : 112
     * type : 4
     * token : H7a1cis
     */

    private boolean success;
    private String errMsg;
    private String code;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
