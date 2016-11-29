package com.laichushu.book.bean.netbean;

import java.io.Serializable;

/**
 * Created by PCPC on 2016/11/28.
 */

public class HomeInfo_paramet implements Serializable{
    private String loginUserId, userId;

    public String getLoginUserId() {
        return loginUserId;
    }

    public void setLoginUserId(String loginUserId) {
        this.loginUserId = loginUserId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public HomeInfo_paramet(String loginUserId, String userId) {
        this.loginUserId = loginUserId;
        this.userId = userId;
    }
}
