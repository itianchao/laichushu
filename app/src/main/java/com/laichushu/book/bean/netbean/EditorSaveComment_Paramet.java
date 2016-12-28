package com.laichushu.book.bean.netbean;

import java.io.Serializable;

/**
 * Created by PCPC on 2016/12/28.
 */

public class EditorSaveComment_Paramet implements Serializable{
    private String userId,editorId,content,starLevel;

    public EditorSaveComment_Paramet(String userId, String editorId, String content, String starLevel) {
        this.userId = userId;
        this.editorId = editorId;
        this.content = content;
        this.starLevel = starLevel;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEditorId() {
        return editorId;
    }

    public void setEditorId(String editorId) {
        this.editorId = editorId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStarLevel() {
        return starLevel;
    }

    public void setStarLevel(String starLevel) {
        this.starLevel = starLevel;
    }
}
