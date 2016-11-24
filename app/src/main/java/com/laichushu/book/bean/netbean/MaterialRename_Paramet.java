package com.laichushu.book.bean.netbean;

/**
 * 素材重命名
 * Created by wangtong on 2016/11/24.
 */

public class MaterialRename_Paramet {
    private String id;
    private String materialName;
    private String userId;

    public MaterialRename_Paramet(String id, String materialName, String userId) {
        this.id = id;
        this.materialName = materialName;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
