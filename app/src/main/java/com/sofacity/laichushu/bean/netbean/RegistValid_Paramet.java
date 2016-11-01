package com.sofacity.laichushu.bean.netbean;

/**
 * 注册校验手机和验证码
 * Created by wangtong on 2016/11/1.
 */
public class RegistValid_Paramet {

    /**
     * loginName : 18787878787
     * code : 123456
     */

    private String loginName;
    private String code;

    public RegistValid_Paramet(String loginName, String code) {
        this.loginName = loginName;
        this.code = code;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
