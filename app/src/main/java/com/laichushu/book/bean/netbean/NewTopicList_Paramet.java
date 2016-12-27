package com.laichushu.book.bean.netbean;

/**
 * 小组 - 最新话题
 * Created by wangtong on 2016/12/27.
 */

public class NewTopicList_Paramet {
    private String userId;
    private String pageNo;
    private String pageSize;

    public NewTopicList_Paramet(String userId, String pageNo, String pageSize) {
        this.userId = userId;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
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
}
