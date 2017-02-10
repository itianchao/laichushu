package com.laichushu.book.bean.netbean;

import com.laichushu.book.global.ConstantValue;

/**
 * 课程 - 视频 列表 or 文档列表
 * Created by wangtong on 2017/1/9.
 */

public class LessonList_Paramet {
    private String lessonType;//课程类型1：点播2：直播
    private String fileType;//1：视频2：文档
    private String sortWay;//1：按点击量排序2：按下载量排序3：按收藏量排序
    private String pageNo;
    private String pageSize;
    private String lessonName;
    private String lessonCategoryId;//分类是传id
    private String userId = ConstantValue.USERID;//分类是传id
    /**
     * @param lessonType 课程类型1：点播2：直播
     * @param fileType 1：视频2：文档
     * @param pageNo
     * @param pageSize
     */
    public LessonList_Paramet(String lessonType, String fileType, String pageNo, String pageSize) {
        this.lessonType = lessonType;
        this.fileType = fileType;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    /**
     * @param lessonType 课程类型1：点播2：直播
     * @param fileType 1：视频2：文档
     * @param sortWay 1：按点击量排序2：按下载量排序3：按收藏量排序
     * @param pageNo
     * @param pageSize
     */
    public LessonList_Paramet(String lessonType, String fileType, String sortWay, String pageNo, String pageSize) {
        this.lessonType = lessonType;
        this.fileType = fileType;
        this.sortWay = sortWay;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    /**
     * 直播
     * @param lessonType
     * @param pageNo
     * @param pageSize
     */
    public LessonList_Paramet(String lessonType, String pageNo, String pageSize) {
        this.lessonType = lessonType;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }


    public String getLessonType() {
        return lessonType;
    }

    public void setLessonType(String lessonType) {
        this.lessonType = lessonType;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getSortWay() {
        return sortWay;
    }

    public void setSortWay(String sortWay) {
        this.sortWay = sortWay;
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

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getLessonCategoryId() {
        return lessonCategoryId;
    }

    public void setLessonCategoryId(String lessonCategoryId) {
        this.lessonCategoryId = lessonCategoryId;
    }
}
