package com.laichushu.book.bean.netbean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/23.
 */

public class HomeUserFocusState_Paramet implements Serializable {
    private String userId, targetId;
    private boolean status;

    public HomeUserFocusState_Paramet(String userId, String targetId, boolean status) {
        this.userId = userId;
        this.targetId = targetId;
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
