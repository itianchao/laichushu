package com.laichushu.book.bean.netbean;

/**
 * 获取作者详情
 * Created by wangtong on 2016/11/3.
 */
public class AuthorDetail_Paramet {

    /**
     * authorId : 1
     */

    private String authorId;

    public AuthorDetail_Paramet(String authorId) {
        this.authorId = authorId;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }
}
