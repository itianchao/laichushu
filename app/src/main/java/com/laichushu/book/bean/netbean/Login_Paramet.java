package com.laichushu.book.bean.netbean;

/**
 * 登录 参数
 * Created by wangtong on 2016/10/12.
 */
public class Login_Paramet {

    /**
     * loginName : mPhonenum
     * password :  mPassword
     */

    private String loginName;
    private String password;

    public Login_Paramet(String loginName, String password) {
        this.loginName = loginName;
        this.password = password;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
