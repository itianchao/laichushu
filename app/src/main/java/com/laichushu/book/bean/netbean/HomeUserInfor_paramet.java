package com.laichushu.book.bean.netbean;

import java.io.Serializable;

/**
 * Created by PCPC on 2016/11/22.
 */

public class HomeUserInfor_paramet implements Serializable {
    private String loginUserId, userId;

    public HomeUserInfor_paramet(String loginUserId, String userId) {
        this.loginUserId = loginUserId;
        this.userId = userId;
    }

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
}
