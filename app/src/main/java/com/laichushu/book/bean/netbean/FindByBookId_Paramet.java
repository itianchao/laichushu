package com.laichushu.book.bean.netbean;

import java.io.Serializable;

/**
 * 查找图书id
 * Created by wangtong on 2016/12/21.
 */

public class FindByBookId_Paramet implements Serializable {
    private String userId, articleId;

    public FindByBookId_Paramet(String userId, String articleId) {
        this.userId = userId;
        this.articleId = articleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }
}
