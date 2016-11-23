package com.laichushu.book.bean.netbean;

/**
 * 编辑 保存素材
 * Created by wangtong on 2016/11/22.
 */

public class EditMaterialBook_Paramet {
    private String id;
    private String materialName;
    private String content;
    private String userId;

    public EditMaterialBook_Paramet(String id, String materialName, String content, String userId) {
        this.id = id;
        this.materialName = materialName;
        this.content = content;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
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
