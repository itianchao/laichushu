package com.laichushu.book.bean.netbean;

/**
 * 草稿重命名
 * Created by wangtong on 2016/11/24.
 */

public class ChapterRename_Paramet {
    private String id, name, userId;

    public ChapterRename_Paramet(String id, String name, String userId) {
        this.id = id;
        this.name = name;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
