package com.laichushu.book.bean.netbean;

/**
 * 获取素材列表
 * Created by wangtong on 2016/11/22.
 */

public class SourceMaterialDirList_Paramet {
    private String userId;
    private String articleId;

    public SourceMaterialDirList_Paramet(String userId, String articleId) {
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
