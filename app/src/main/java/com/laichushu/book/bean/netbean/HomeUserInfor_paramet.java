package com.laichushu.book.bean.netbean;

import java.io.Serializable;

/**
 * Created by PCPC on 2016/11/22.
 */

public class HomeUserInfor_paramet implements Serializable {
    private String targetId, userId;

    public HomeUserInfor_paramet(String tagetId, String userId) {
        this.targetId = tagetId;
        this.userId = userId;
    }

    public String getTagetId() {
        return targetId;
    }

    public void setTagetId(String tagetId) {
        this.targetId = tagetId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
