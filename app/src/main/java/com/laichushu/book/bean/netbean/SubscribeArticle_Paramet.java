package com.laichushu.book.bean.netbean;

/**
 * 图书订阅 参数
 * Created by wangtong on 2016/11/3.
 */
public class SubscribeArticle_Paramet {
    private String userId;
    private String articleId;
    private String type;

    public SubscribeArticle_Paramet(String userId, String articleId, String type) {
        this.userId = userId;
        this.articleId = articleId;
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

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }
}
