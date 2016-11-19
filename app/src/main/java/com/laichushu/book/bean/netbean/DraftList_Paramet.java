package com.laichushu.book.bean.netbean;

/**
 * 获取章节列表
 * Created by wangtong on 2016/11/19.
 */

public class DraftList_Paramet {
    private String articleId;
    private String pageNo;
    private String pageSize;
    private String userId;

    public DraftList_Paramet(String articleId, String pageNo, String pageSize, String userId) {
        this.articleId = articleId;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.userId = userId;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
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
