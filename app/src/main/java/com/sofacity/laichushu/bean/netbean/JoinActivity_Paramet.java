package com.sofacity.laichushu.bean.netbean;

/**
 * 参加活动 参数
 * Created by wangtong on 2016/11/4.
 */
public class JoinActivity_Paramet {
    private String activityId;
    private String articleId;
    private String userId;
    private String type;

    public JoinActivity_Paramet(String activityId, String articleId, String userId, String type) {
        this.activityId = activityId;
        this.articleId = articleId;
        this.userId = userId;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
