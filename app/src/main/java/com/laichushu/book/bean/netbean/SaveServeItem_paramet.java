package com.laichushu.book.bean.netbean;

/**
 * Created by PCPC on 2016/12/31.
 */

public class SaveServeItem_paramet {
    private String userId,
            name,
            content;

    public SaveServeItem_paramet(String userId, String name, String content) {
        this.userId = userId;
        this.name = name;
        this.content = content;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
