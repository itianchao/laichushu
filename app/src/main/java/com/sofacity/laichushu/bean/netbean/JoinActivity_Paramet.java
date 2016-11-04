package com.sofacity.laichushu.bean.netbean;

/**
 * 参加活动 参数
 * Created by wangtong on 2016/11/4.
 */
public class JoinActivity_Paramet {
    private String activityId;
    private String articleId;
    private String userId;

    public JoinActivity_Paramet(String activityId, String articleId, String userId) {
        this.activityId = activityId;
        this.articleId = articleId;
        this.userId = userId;
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
