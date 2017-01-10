package com.laichushu.book.bean.netbean;

/**
 * 删除笔记 参数
 * Created by wangtong on 2017/1/10.
 */

public class NoteDelete_Paramet {
    private String noteId;

    public NoteDelete_Paramet(String noteId) {
        this.noteId = noteId;
    }

    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }
}
