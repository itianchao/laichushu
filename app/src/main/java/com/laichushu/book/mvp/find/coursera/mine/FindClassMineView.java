package com.laichushu.book.mvp.find.coursera.mine;

import com.laichushu.book.mvp.find.coursera.video.CourseraModle;

/**
 * 发现 - 课程 - 我的 View
 * Created by wangtong on 2017/1/6.
 */

public interface FindClassMineView {
    void getMineListDataSuccess(CourseraModle modle);

    void getMineListDataFail(String msg);
}
