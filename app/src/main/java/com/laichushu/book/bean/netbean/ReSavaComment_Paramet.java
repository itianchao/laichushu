package com.laichushu.book.bean.netbean;

/**
 * 保存回复评论
 * Created by wangtong on 2016/11/4.
 */
public class ReSavaComment_Paramet {
    private String sourceId;
    private String userId;
    private String content;
    private String sourceType;

    public ReSavaComment_Paramet(String sourceId, String userId, String content, String sourceType) {
        this.sourceId = sourceId;
        this.userId = userId;
        this.content = content;
        this.sourceType = sourceType;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
