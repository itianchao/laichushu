package com.laichushu.book.mvp.find;

import com.laichushu.book.bean.netbean.FindCourseCommResult;
import com.laichushu.book.bean.netbean.FindLessonListResult;

/**
 * 发现 - fragment - View
 * Created by PCPC on 2016/12/19.
 */

public interface FindView {
    void getCourseDataSuccess(FindCourseCommResult model);
    void getLessonListDataSuccess(FindLessonListResult model);
    void getDataFail(String msg);
    void showLoading();
    void hideLoading();
}
