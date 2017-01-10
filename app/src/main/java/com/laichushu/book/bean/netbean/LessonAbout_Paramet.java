package com.laichushu.book.bean.netbean;

/**
 * 课程相关
 * Created by wangtong on 2017/1/10.
 */

public class LessonAbout_Paramet {

    /**
     * courseId : 10
     * pageNo : 1
     * pageSize : 20
     */

    private String courseId;
    private String pageNo;
    private String pageSize;

    public LessonAbout_Paramet(String courseId, String pageNo, String pageSize) {
        this.courseId = courseId;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }
}
