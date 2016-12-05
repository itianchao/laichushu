package com.laichushu.book.bean.netbean;

import java.io.Serializable;

/**
 * Created by PCPC on 2016/12/2.
 */

public class CollectSaveDate_Paramet implements Serializable {
    private String userId;
    private String sourceId;
    private String sourceType;
    private String type;//0 收藏 1 取消

    public CollectSaveDate_Paramet(String userId, String sourceId, String sourceType, String type) {
        this.userId = userId;
        this.sourceId = sourceId;
        this.sourceType = sourceType;
        this.type = type;
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

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
