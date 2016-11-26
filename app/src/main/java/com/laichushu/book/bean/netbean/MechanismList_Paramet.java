package com.laichushu.book.bean.netbean;

/**
 * 获取机构列表
 * Created by wt on 2016/11/24.
 */

public class MechanismList_Paramet {
    private String type,pageNo,pageSize,userId;

    public MechanismList_Paramet(String type, String pageNo, String pageSize, String userId) {
        this.type = type;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.userId = userId;
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
}
