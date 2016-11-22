package com.laichushu.book.bean.netbean;

/**
 * 创建素材
 * Created by wangtong on 2016/11/22.
 */

public class CreateSourceMaterial_Paramet {
    private String articleId;
    private String materialName;
    private String parentId;

    public CreateSourceMaterial_Paramet(String articleId, String materialName, String parentId) {
        this.articleId = articleId;
        this.materialName = materialName;
        this.parentId = parentId;
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
