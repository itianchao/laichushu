package com.laichushu.book.bean.netbean;

import java.io.Serializable;

/**
 * Created by PCPC on 2016/11/21.
 */

public class CollectList_Paramet implements Serializable {
    private String pageSize, pageNo, userId, type;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public CollectList_Paramet(String pageSize, String pageNo, String userId, String type) {
        this.pageSize = pageSize;
        this.pageNo = pageNo;
        this.userId = userId;
        this.type = type;
    }
}
