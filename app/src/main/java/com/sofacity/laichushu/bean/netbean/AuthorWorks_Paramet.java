package com.sofacity.laichushu.bean.netbean;

/**
 * 作者作品
 * Created by wangtong on 2016/11/4.
 */
public class AuthorWorks_Paramet {
    private String userId;
    private String pageSize;
    private String pageNo;

    public AuthorWorks_Paramet(String userId, String pageSize, String pageNo) {
        this.userId = userId;
        this.pageSize = pageSize;
        this.pageNo = pageNo;
    }

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
}
