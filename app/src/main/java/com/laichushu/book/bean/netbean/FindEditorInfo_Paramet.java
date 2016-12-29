package com.laichushu.book.bean.netbean;

import java.io.Serializable;

/**
 * Created by PCPC on 2016/12/27.
 */

public class FindEditorInfo_Paramet implements Serializable {
    private String userId, loginUserId;

    public FindEditorInfo_Paramet(String userId, String loginUserId) {
        this.userId = userId;
        this.loginUserId = loginUserId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLoginUserId() {
        return loginUserId;
    }

    public void setLoginUserId(String loginUserId) {
        this.loginUserId = loginUserId;
    }
}
