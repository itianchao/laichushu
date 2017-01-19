package com.laichushu.book.bean.netbean;

/**
 * 推荐小组
 * Created by wangtong on 2017/1/16.
 */

public class CourseCommendation_Paramet {
    private String userId;

    public CourseCommendation_Paramet(String userId) {
        userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        userId = userId;
    }
}
