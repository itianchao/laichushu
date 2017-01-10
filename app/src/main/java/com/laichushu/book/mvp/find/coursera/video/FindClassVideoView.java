package com.laichushu.book.mvp.find.coursera.video;

/**
 * 发现 - 课程 - 视频 View
 * Created by wangtong on 2017/1/6.
 */

public interface FindClassVideoView {
    void getVideoListDataSuccess(CourseraModle modle);

    void getVideoListDataFail(String s);
}
