package com.laichushu.book.mvp.find.coursera.document;

import com.laichushu.book.mvp.find.coursera.video.CourseraModle;

/**
 * 发现 - 课程 - 文档 View
 * Created by wangtong on 2017/1/6.
 */

public interface FindClassDocView {
    void getVideoListDataSuccess(CourseraModle modle);

    void getVideoListDataFail(String msg);
}
