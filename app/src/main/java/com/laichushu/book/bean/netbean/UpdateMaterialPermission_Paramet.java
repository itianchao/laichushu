package com.laichushu.book.bean.netbean;

import java.io.Serializable;

/**
 * Created by PCPC on 2016/12/10.
 */

public class UpdateMaterialPermission_Paramet implements Serializable {
    private String userId, id, permission;

    public UpdateMaterialPermission_Paramet(String userId, String id, String permission) {
        this.userId = userId;
        this.id = id;
        this.permission = permission;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
