package com.sofacity.laichushu.bean.netbean;

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

    private String commentId;
    private String userId;
    private String pageNo;
    private String pageSize;

    public ReCommentList_Paramet(String commentId, String userId, String pageNo, String pageSize) {
        this.commentId = commentId;
        this.userId = userId;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
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
