package com.laichushu.book.bean.netbean;

/**
 * 获取素材列表
 * Created by wangtong on 2016/11/22.
 */

public class SourceMaterialList_Paramet {
    private String parentId;

    public SourceMaterialList_Paramet(String parentId) {
        this.parentId = parentId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
