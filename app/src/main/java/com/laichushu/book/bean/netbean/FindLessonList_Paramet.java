package com.laichushu.book.bean.netbean;

/**
 * Created by PCPC on 2017/1/4.
 */

public class FindLessonList_Paramet {
    private String lessonType;//  课程类型（1：点播 2：直播）
    private String sortWay;// 全部课程排序查询
    private String fileType;  // 文件类型（1：视频 2：文档）
    private String pageNo;
    private String pageSize;

    public FindLessonList_Paramet(String lessonType, String sortWay, String fileType, String pageNo, String pageSize) {
        this.lessonType = lessonType;
        this.sortWay = sortWay;
        this.fileType = fileType;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public String getLessonType() {
        return lessonType;
    }

    public void setLessonType(String lessonType) {
        this.lessonType = lessonType;
    }

    public String getSortWay() {
        return sortWay;
    }

    public void setSortWay(String sortWay) {
        this.sortWay = sortWay;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
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
