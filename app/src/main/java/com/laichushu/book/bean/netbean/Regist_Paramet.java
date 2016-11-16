package com.laichushu.book.bean.netbean;

/**
 * 注册 参数
 * Created by wangtong on 2016/11/1.
 */
public class Regist_Paramet {

    /**
     * loginName : 18787878787
     * nickName : nihoa
     * sex : 1
     * password : dsfafadsfa
     */

    private String loginName;
    private String nickName;
    private String sex;
    private String password;

    public Regist_Paramet(String loginName, String nickName, String sex, String password) {
        this.loginName = loginName;
        this.nickName = nickName;
        this.sex = sex;
        this.password = password;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
