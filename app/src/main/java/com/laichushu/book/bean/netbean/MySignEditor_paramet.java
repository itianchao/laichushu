package com.laichushu.book.bean.netbean;

import java.io.Serializable;

/**
 * Created by PCPC on 2016/12/13.
 */

public class MySignEditor_paramet implements Serializable {
    String pressId, id, editorId, userId;

    public MySignEditor_paramet(String pressId, String id, String editorId, String userId) {
        this.pressId = pressId;
        this.id = id;
        this.editorId = editorId;
        this.userId = userId;
    }

    public String getPressId() {
        return pressId;
    }

    public void setPressId(String pressId) {
        this.pressId = pressId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEditorId() {
        return editorId;
    }

    public void setEditorId(String editorId) {
        this.editorId = editorId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
