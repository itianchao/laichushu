package com.sofacity.laichushu.bean.netbean;

/**
 * 评论参数
 * Created by wangtong on 2016/11/3.
 */
public class Comment2_Paramet {

    /**
     * articleId : 3
     * pageSize : 1
     * pageNo : 5
     */
    private String articleId;
    private String pageSize;
    private String pageNo;
    private String userId;

    public Comment2_Paramet(String articleId, String pageSize, String pageNo, String userId) {
        this.articleId = articleId;
        this.pageSize = pageSize;
        this.pageNo = pageNo;
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
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
}
