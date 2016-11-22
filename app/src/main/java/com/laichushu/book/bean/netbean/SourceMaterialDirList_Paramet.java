package com.laichushu.book.bean.netbean;

/**
 * 获取素材列表
 * Created by wangtong on 2016/11/22.
 */

public class SourceMaterialDirList_Paramet {
    private String  articleId;

    public SourceMaterialDirList_Paramet(String articleId) {
        this.articleId = articleId;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }
}
