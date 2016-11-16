package com.laichushu.book.mvp.login;

/**
 * 登录数据模型
 * Created by wangtong on 2016/10/12.
 */
public class LoginModel {

    /**
     * success : false
     * errMsg : 错误信息
     * token:gW3QDd1jnS6NbiB3XMuxEBFCdza3lE70
     * userId:66942b8567314f7f8b00fddb197475ae
     */

    private boolean success;
    private String errMsg;
    private String token;
    private String userId;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
