package com.laichushu.book.bean.netbean;

/**
 * 删除 新书
 * Created by wangtong on 2016/11/21.
 */

public class DeleteNewBook_Paramet {
    private String id;
    private String userId;

    public DeleteNewBook_Paramet(String id, String userId) {
        this.id = id;
        this.userId = userId;
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
}
