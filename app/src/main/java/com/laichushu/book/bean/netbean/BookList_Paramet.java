package com.laichushu.book.bean.netbean;

/**
 * 章节列表
 * Created by wangtong on 2016/11/7.
 */
public class BookList_Paramet {
    private String articleId;
    private String userId;
    private String pageNo;
    private String pageSize;

    public BookList_Paramet(String articleId, String userId, String pageNo, String pageSize) {
        this.articleId = articleId;
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

    public String getPageSize() {
        return pageSize;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }
}
