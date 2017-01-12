package com.laichushu.book.bean.netbean;

/**
 * 发现 - 课程 - 评论 课程评分列表 参数
 * Created by wangtong on 2017/1/10.
 */

public class CourseScoreList_Paramet {
    private String sourceId, sourceType, pageNo, pageSize, userId;

    public CourseScoreList_Paramet(String sourceId, String sourceType, String pageNo, String pageSize, String userId) {
        this.sourceId = sourceId;
        this.sourceType = sourceType;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
