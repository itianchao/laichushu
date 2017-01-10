package com.laichushu.book.bean.netbean;

/**
 * 发现 - 课程 - 评论 课程评分列表 参数
 * Created by wangtong on 2017/1/10.
 */

public class CourseScoreList_Paramet {
    private String sourceId,sourceType,pageNo,pageSize,userId;

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
