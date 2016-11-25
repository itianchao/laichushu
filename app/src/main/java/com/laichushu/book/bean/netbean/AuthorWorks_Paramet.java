package com.laichushu.book.bean.netbean;

/**
 * 作者作品
 * Created by wangtong on 2016/11/4.
 */
public class AuthorWorks_Paramet {
    private String userId;

    public AuthorWorks_Paramet(String userId) {
        this.userId = userId;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
