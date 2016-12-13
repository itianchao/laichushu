package com.laichushu.book.bean.netbean;

import java.io.Serializable;

/**
 * Created by PCPC on 2016/12/9.
 */

public class AuthorWorksByBookId_Paramet implements Serializable {
    private String userId, articleId;

    public AuthorWorksByBookId_Paramet(String userId, String articleId) {
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
