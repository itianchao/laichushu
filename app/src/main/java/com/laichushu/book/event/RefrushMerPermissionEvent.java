package com.laichushu.book.event;

/**
 * 刷新素材权限
 * Created by wangtong on 2016/12/26.
 */

public class RefrushMerPermissionEvent {
    private String materialPermission;
    private int index;

    public RefrushMerPermissionEvent(String materialPermission, int index) {
        this.materialPermission = materialPermission;
        this.index = index;
    }

    public String getMaterialPermission() {
        return materialPermission;
    }

    public void setMaterialPermission(String materialPermission) {
        this.materialPermission = materialPermission;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
