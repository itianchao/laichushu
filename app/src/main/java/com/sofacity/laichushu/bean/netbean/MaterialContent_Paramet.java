package com.sofacity.laichushu.bean.netbean;

/**
 * 获取素材列表
 * Created by wangtong on 2016/11/7.
 */
public class MaterialContent_Paramet {
    private String parentId;

    public MaterialContent_Paramet(String parentId) {
        this.parentId = parentId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
