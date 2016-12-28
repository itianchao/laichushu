package com.laichushu.book.bean.netbean;

/**
 * 发现 - 小组主页 - 搜索小组列表 参数
 * Created by wangtong on 2016/12/28.
 */

public class SearchGroupList_Paramet {
    private String userId,name,pageNo,pageSize;

    public SearchGroupList_Paramet(String userId, String name, String pageNo, String pageSize) {
        this.userId = userId;
        this.name = name;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
