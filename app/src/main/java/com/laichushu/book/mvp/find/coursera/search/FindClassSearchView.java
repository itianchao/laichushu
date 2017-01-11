package com.laichushu.book.mvp.find.coursera.search;

import com.laichushu.book.mvp.find.coursera.video.CourseraModle;

/**
 * 发现 - 课程 - 搜索 View
 * Created by wangtong on 2017/1/6.
 */

public interface FindClassSearchView {

    void getVideoListDataSuccess(CourseraModle modle);

    void getVideoListDataFail(String msg);
}
