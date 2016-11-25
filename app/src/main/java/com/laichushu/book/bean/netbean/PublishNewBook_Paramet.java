package com.laichushu.book.bean.netbean;

/**
 * 发表
 * Created by wangtong on 2016/11/21.
 */

public class PublishNewBook_Paramet {
    private String id,userId,type;

    public PublishNewBook_Paramet(String id, String userId, String type) {
        this.id = id;
        this.userId = userId;
        this.type = type;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
