package com.laichushu.book.bean.netbean;

import java.io.Serializable;

/**
 * Created by PCPC on 2016/11/28.
 */

public class ChangeFocusState_Paramet implements Serializable {
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

    public ChangeFocusState_Paramet(String loginUserId, String userId) {
        this.loginUserId = loginUserId;
        this.userId = userId;
    }
}
