package com.laichushu.book.bean.netbean;

/**
 * 笔记接口
 * Created by wangtong on 2017/1/10.
 */

public class NoteSave_Paramet {

    /**
     * {"noteId":"","lessonId":"","userId":"","name":"","remarks":""}
     * noteId :
     * lessonId :
     * userId :
     * name :
     * remarks :
     */

    private String noteId;
    private String lessonId;
    private String userId;
    private String name;
    private String remarks;

    /**
     * 保存笔记
     * @param lessonId
     * @param userId
     * @param name
     * @param remarks
     */
    public NoteSave_Paramet(String lessonId, String userId, String name, String remarks) {
        this.lessonId = lessonId;
        this.userId = userId;
        this.name = name;
        this.remarks = remarks;
    }

    /**
     * 修改笔记
     * @param noteId
     * @param lessonId
     * @param userId
     * @param name
     * @param remarks
     */
    public NoteSave_Paramet(String noteId, String lessonId, String userId, String name, String remarks) {
        this.noteId = noteId;
        this.lessonId = lessonId;
        this.userId = userId;
        this.name = name;
        this.remarks = remarks;
    }

    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
