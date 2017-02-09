package com.laichushu.book.bean.netbean;

/**
 * 重置密码 参数
 * Created by wangtong on 2016/11/1.
 */
public class ForgetPwd_Paramet {

    /**
     * loginName : 123
     * newPwd : 12312
     * confirmPwd : 12312
     */

    private String loginName;
    private String newPwd;
    private String confirmPwd;
    private String code;

    public ForgetPwd_Paramet(String loginName, String newPwd, String confirmPwd, String code) {
        this.loginName = loginName;
        this.newPwd = newPwd;
        this.confirmPwd = confirmPwd;
        this.code = code;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

    public String getConfirmPwd() {
        return confirmPwd;
    }

    public void setConfirmPwd(String confirmPwd) {
        this.confirmPwd = confirmPwd;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
