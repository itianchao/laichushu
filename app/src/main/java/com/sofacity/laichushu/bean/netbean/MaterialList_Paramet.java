package com.sofacity.laichushu.bean.netbean;

/**
 * 获取素材列表
 * Created by wangtong on 2016/11/7.
 */
public class MaterialList_Paramet {
    private String articleId;

    public MaterialList_Paramet(String articleId) {
        this.articleId = articleId;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }
}
