package com.laichushu.book.mvp.find.coursera.live;

import com.laichushu.book.mvp.find.coursera.video.CourseraModle;

/**
 * 发现 - 课程 - 视频 View
 * Created by wangtong on 2017/1/6.
 */

public interface FindClassLiveView {
    void getVideoListDataSuccess(CourseraModle modle);

    void getVideoListDataFail(String s);
}
