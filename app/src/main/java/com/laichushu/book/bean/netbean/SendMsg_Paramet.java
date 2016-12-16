package com.laichushu.book.bean.netbean;

/**
 * 发送短信验证码
 * Created by wangtong on 2016/12/15.
 */

public class SendMsg_Paramet {

    /**
     * loginName : 18787876765
     */

    private String loginName;
    /**
     * type : 2
     */

    private String type;

    public SendMsg_Paramet(String loginName, String type) {
        this.loginName = loginName;
        this.type = type;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
