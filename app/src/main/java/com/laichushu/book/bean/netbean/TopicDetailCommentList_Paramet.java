package com.laichushu.book.bean.netbean;

/**
 * 话题详情评论列表参数
 * Created by wangtong on 2016/11/26.
 */

public class TopicDetailCommentList_Paramet {
    private String sourceId;  //话题 id
    private String sourceType;
    private String pageNo;
    private String pageSize;

    public TopicDetailCommentList_Paramet(String sourceId, String sourceType, String pageNo, String pageSize) {
        this.sourceId = sourceId;
        this.sourceType = sourceType;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
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
