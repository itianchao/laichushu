package com.laichushu.book.bean.netbean;

/**
 * 回复评论
 * Created by wangtong on 2016/11/4.
 */
public class ReCommentList_Paramet {

    /**
     * scoreId : 1
     * userId : 112
     * pageNo : 1
     * pageSize : 10
     */

    private String scoreId;
    private String userId;
    private String pageNo;
    private String pageSize;

    public ReCommentList_Paramet(String scoreId, String userId, String pageNo, String pageSize) {
        this.scoreId = scoreId;
        this.userId = userId;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public String getScoreId() {
        return scoreId;
    }

    public void setScoreId(String scoreId) {
        this.scoreId = scoreId;
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