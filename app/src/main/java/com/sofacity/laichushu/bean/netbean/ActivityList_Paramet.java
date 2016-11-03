package com.sofacity.laichushu.bean.netbean;

/**
 * 活动列表
 * Created by wangtong on 2016/11/3.
 */
public class ActivityList_Paramet {
    private String pageNo;
    private String pageSize;

    public ActivityList_Paramet(String pageNo, String pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }
}
