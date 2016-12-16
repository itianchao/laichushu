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

    private String sourceId;
    private String userId;
    private String pageNo;
    private String pageSize;
    private String sourceType;

    public ReCommentList_Paramet(String sourceId, String userId, String pageNo, String pageSize,String sourceType) {
        this.sourceId = sourceId;
        this.userId = userId;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.sourceType = sourceType;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
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
