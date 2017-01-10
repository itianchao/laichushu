package com.laichushu.book.bean.netbean;

/**
 * 讲义列表
 * Created by wangtong on 2017/1/10.
 */

public class SpeakpdfDetail_Paramet {
    private String lessonId,pageNo,pageSize;

    public SpeakpdfDetail_Paramet(String lessonId, String pageNo, String pageSize) {
        this.lessonId = lessonId;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public String getLessonId() {
        return lessonId;
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId;
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
