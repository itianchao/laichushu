package com.laichushu.book.bean.netbean;

import java.io.Serializable;

/**
 * Created by PCPC on 2016/12/1.
 */

public class TopicDyLike_Paramet implements Serializable {
    private String sourceId, sourceType, type, userId;

    public TopicDyLike_Paramet(String sourceId, String sourceType, String type, String userId) {
        this.sourceId = sourceId;
        this.sourceType = sourceType;
        this.type = type;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
