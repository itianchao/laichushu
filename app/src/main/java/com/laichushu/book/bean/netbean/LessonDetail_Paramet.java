package com.laichushu.book.bean.netbean;

/**
 * 课程详情
 * Created by wangtong on 2017/1/10.
 */

public class LessonDetail_Paramet {
    private String lessonId;
    private String userId;
    private String operateType;//1：下载 2：查询

    public LessonDetail_Paramet(String lessonId, String userId, String operateType) {
        this.lessonId = lessonId;
        this.userId = userId;
        this.operateType = operateType;
    }

    public String getLessonId() {
        return lessonId;
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }
}
