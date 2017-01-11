package com.laichushu.book.bean.netbean;

/**
 * 发现 - 课程 - 笔记列表
 * Created by wangtong on 2017/1/10.
 */

public class NoteList_Paramet {

    /**
     * {"lessonId":"15","userId":"175","pageNo":"1","pageSize":"20"}
     * lessonId : 15
     * userId : 175
     * pageNo : 1
     * pageSize : 20
     */

    private String lessonId;
    private String userId;
    private String pageNo;
    private String pageSize;

    public NoteList_Paramet(String lessonId, String userId, String pageNo, String pageSize) {
        this.lessonId = lessonId;
        this.userId = userId;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
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
