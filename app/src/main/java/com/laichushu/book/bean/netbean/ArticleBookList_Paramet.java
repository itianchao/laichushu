package com.laichushu.book.bean.netbean;

/**
 * 作品列表
 * Created by wangtong on 2016/11/19.
 */

public class ArticleBookList_Paramet {
    private String userId;
    private String pageNo;
    private String pageSize;

    public ArticleBookList_Paramet(String userId, String pageNo, String pageSize) {
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