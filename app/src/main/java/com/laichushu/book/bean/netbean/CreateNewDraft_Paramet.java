package com.laichushu.book.bean.netbean;

/**
 * 创建草稿
 * Created by wangtong on 2016/11/19.
 */

public class CreateNewDraft_Paramet {

    private String articleId;
    private String name;
    private String content;
    private String userId;

    public CreateNewDraft_Paramet(String articleId, String name, String content, String userId) {
        this.articleId = articleId;
        this.name = name;
        this.content = content;
        this.userId = userId;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
