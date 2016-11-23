package com.laichushu.book.bean.netbean;

/**
 * 创建素材
 * Created by wangtong on 2016/11/22.
 */

public class CreateSourceMaterial_Paramet {
    private String articleId;
    private String materialName;
    private String parentId;
    private String content;

    public CreateSourceMaterial_Paramet(String articleId, String materialName, String parentId, String content) {
        this.articleId = articleId;
        this.materialName = materialName;
        this.parentId = parentId;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
