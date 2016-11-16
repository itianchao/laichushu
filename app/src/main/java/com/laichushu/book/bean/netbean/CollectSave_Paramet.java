package com.laichushu.book.bean.netbean;

/**
 * 收藏请求参数
 * Created by wangtong on 2016/11/11.
 */

public class CollectSave_Paramet {
    private String userId;
    private String targetId;
    private String type;
    private String collectType;

    public CollectSave_Paramet(String userId, String targetId, String type, String collectType) {
        this.userId = userId;
        this.targetId = targetId;
        this.type = type;
        this.collectType = collectType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCollectType() {
        return collectType;
    }

    public void setCollectType(String collectType) {
        this.collectType = collectType;
    }
}
