package com.laichushu.book.bean.netbean;

/**
 * 注册校验手机和验证码
 * Created by wangtong on 2016/11/1.
 */
public class RegistValid_Paramet {

    /**
     * loginName : 18787878787
     * code : 123456
     * type 1注册 2 重置
     */

    private String loginName;
    private String code;
    private String type;

    public RegistValid_Paramet(String loginName, String code, String type) {
        this.loginName = loginName;
        this.code = code;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
