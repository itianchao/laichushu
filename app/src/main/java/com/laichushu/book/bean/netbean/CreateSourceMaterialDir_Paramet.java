package com.laichushu.book.bean.netbean;

/**
 * 创建素材文件夹
 * Created by wangtong on 2016/11/22.
 */

public class CreateSourceMaterialDir_Paramet {
    private String articleId;
    private String foldName;

    public CreateSourceMaterialDir_Paramet(String articleId, String foldName) {
        this.articleId = articleId;
        this.foldName = foldName;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getFoldName() {
        return foldName;
    }

    public void setFoldName(String foldName) {
        this.foldName = foldName;
    }
}
