package com.laichushu.book.bean.netbean;

/**
 * 推荐小组
 * Created by wangtong on 2017/1/16.
 */

public class CourseCommendation_Paramet {
    private String UserId;

    public CourseCommendation_Paramet(String userId) {
        UserId = userId;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }
}
