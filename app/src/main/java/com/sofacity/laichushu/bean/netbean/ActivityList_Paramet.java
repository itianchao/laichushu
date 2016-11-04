package com.sofacity.laichushu.bean.netbean;

/**
 * 活动列表
 * Created by wangtong on 2016/11/3.
 */
public class ActivityList_Paramet {
    private String pageNo;
    private String pageSize;
    private String userId;
    public ActivityList_Paramet(String pageNo, String pageSize, String userId) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
