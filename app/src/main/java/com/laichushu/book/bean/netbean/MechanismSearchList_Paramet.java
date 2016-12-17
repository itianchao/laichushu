package com.laichushu.book.bean.netbean;

/**
 * 获取机构列表
 * Created by wt on 2016/11/24.
 */

public class MechanismSearchList_Paramet {
    private String name,pageNo,pageSize,userId;

    public MechanismSearchList_Paramet(String name, String pageNo, String pageSize, String userId) {
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
