package com.laichushu.book.bean.netbean;

/**
 * 话题详情评论保存
 * Created by wangtong on 2016/11/26.
 */

public class TopicDetailCommentSave_Paramet {

    private String sourceId;
    private String userId;
    private String content;
    private String sourceType;   //话题id

    public TopicDetailCommentSave_Paramet(String sourceId, String userId, String content, String sourceType) {
        this.sourceId = sourceId;
        this.userId = userId;
        this.content = content;
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

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }
}
