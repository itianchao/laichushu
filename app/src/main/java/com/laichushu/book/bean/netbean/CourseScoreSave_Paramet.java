package com.laichushu.book.bean.netbean;

/**
 * 发现 - 课程 - 评论 课程评分保存 参数
 * Created by wangtong on 2017/1/10.
 */

public class CourseScoreSave_Paramet {
    private String sourceId,sourceType,starLevel,content,userId;

    public CourseScoreSave_Paramet(String sourceId, String sourceType, String starLevel, String content, String userId) {
        this.sourceId = sourceId;
        this.sourceType = sourceType;
        this.starLevel = starLevel;
        this.content = content;
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

    public String getStarLevel() {
        return starLevel;
    }

    public void setStarLevel(String starLevel) {
        this.starLevel = starLevel;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
