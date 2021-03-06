package com.laichushu.book.bean.netbean;

/**
 * Created by PCPC on 2016/11/22.
 */

public class MyArticBooklist_paramet {
    private String userId;
    private String pageNo;
    private String pageSize;
    private String type;
    private String sourceType;

    public MyArticBooklist_paramet(String userId, String pageNo, String pageSize, String type, String sourceType) {
        this.userId = userId;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.type = type;
        this.sourceType = sourceType;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }
}
