package com.sofacity.laichushu.bean.netbean;

/**
 * 点赞
 * Created by wangtong on 2016/11/9.
 */

public class ScoreLike_Paramet {
    private String sourceId;
    private String userId;
    private String type;

    public ScoreLike_Paramet(String sourceId, String userId, String type) {
        this.sourceId = sourceId;
        this.userId = userId;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
