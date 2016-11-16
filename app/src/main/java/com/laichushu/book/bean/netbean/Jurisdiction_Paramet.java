package com.laichushu.book.bean.netbean;

/**
 * 阅读权限
 * Created by wangtong on 2016/11/14.
 */

public class Jurisdiction_Paramet {
    private String articleId;
    private String userId;
    private String pageSize;
    private String pageNo;

    public Jurisdiction_Paramet(String articleId, String userId, String pageSize, String pageNo) {
        this.articleId = articleId;
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

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
