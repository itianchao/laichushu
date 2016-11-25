package com.laichushu.book.bean.netbean;

import java.io.Serializable;

/**
 * Created by PCPC on 2016/11/23.
 */

public class HomeUserFocusMe_parmet implements Serializable {
    private String userId, pageNo, pageSize, targetId;

    public HomeUserFocusMe_parmet(String userId, String pageNo, String pageSize, String targetId) {
        this.userId = userId;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.targetId = targetId;
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

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }
}
