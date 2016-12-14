package com.laichushu.book.bean.netbean;

/**
 * 收藏请求参数
 * Created by wangtong on 2016/11/11.
 */

public class CollectSave_Paramet {
    private String userId;
    private String sourceId;
    private String type;//0 收藏 1 取消
    private String sourceType;

    public CollectSave_Paramet(String userId, String sourceId, String sourceType, String type) {
        this.userId = userId;
        this.sourceId = sourceId;
        this.type = type;
        this.sourceType = sourceType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCollectType() {
        return sourceType;
    }

    public void setCollectType(String collectType) {
        this.sourceType = collectType;
    }
}
