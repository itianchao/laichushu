package com.laichushu.book.bean.netbean;

/**
 * 查询活动详情
 * Created by wangtong on 2016/12/17.
 */

public class ActivityById_Paramet {
    private String activityId,userId;

    public ActivityById_Paramet(String activityId, String userId) {
        this.activityId = activityId;
        this.userId = userId;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
