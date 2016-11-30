package com.laichushu.book.bean.netbean;

/**
 * Created by PCPC on 2016/11/30.
 */

public class BookDetails_Paramet {
    private String userId, id;

    public BookDetails_Paramet(String userId, String id) {
        this.userId = userId;
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
