package com.laichushu.book.bean.netbean;

import java.io.Serializable;

/**
 * Created by PCPC on 2016/12/27.
 */

public class FindEditorCommentList_Paramet implements Serializable {
    private String userId,
            editorId,
            pageSize,
            pageNo;

    public FindEditorCommentList_Paramet(String userId, String editorId, String pageSize, String pageNo) {
        this.userId = userId;
        this.editorId = editorId;
        this.pageSize = pageSize;
        this.pageNo = pageNo;
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

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }
}
