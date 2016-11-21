package com.laichushu.book.bean.netbean;

/**
 * Created by wangtong on 2016/11/21.
 */

public class ArticleVote_Paramet {
    private String id;
    private String userId;
    private String pressId;

    public ArticleVote_Paramet(String id, String userId, String pressId) {
        this.id = id;
        this.userId = userId;
        this.pressId = pressId;
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

    public String getPressId() {
        return pressId;
    }

    public void setPressId(String pressId) {
        this.pressId = pressId;
    }
}
