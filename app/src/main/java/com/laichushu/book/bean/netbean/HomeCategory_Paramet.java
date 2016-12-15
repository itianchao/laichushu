package com.laichushu.book.bean.netbean;

/**
 * Created by wangtong on 2016/12/8.
 */

public class HomeCategory_Paramet {
    private String userId;
    private String type;

    public HomeCategory_Paramet(String userId, String type) {
        this.userId = userId;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
