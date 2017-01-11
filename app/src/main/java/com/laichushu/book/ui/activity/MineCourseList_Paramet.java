package com.laichushu.book.ui.activity;

/**
 * 发现 - 课程 - 我的收藏、我的下载、我的浏览
 * Created by wangtong on 2017/1/10.
 */

public class MineCourseList_Paramet {
    //1：我的收藏 2我的浏览3：我的下载
    private String userId,operateType,pageNo,pageSize;

    public MineCourseList_Paramet(String userId, String operateType, String pageNo, String pageSize) {
        this.userId = userId;
        this.operateType = operateType;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
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
