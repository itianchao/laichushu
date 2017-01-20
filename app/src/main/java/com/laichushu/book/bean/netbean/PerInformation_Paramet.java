package com.laichushu.book.bean.netbean;

import java.io.Serializable;

/**
 * Created by PCPC on 2016/12/2.
 */

public class PerInformation_Paramet implements Serializable {

    private String userId, pageNo, pageSize, senderName;

    public PerInformation_Paramet(String userId, String pageNo, String pageSize, String senderName) {
        this.userId = userId;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.senderName = senderName;
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

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }
}
