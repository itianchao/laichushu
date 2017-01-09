package com.laichushu.book.mvp.findfragment;

import com.laichushu.book.bean.netbean.FindCourseCommResult;
import com.laichushu.book.bean.netbean.FindLessonListResult;

/**
 * Created by PCPC on 2016/12/19.
 */

public interface FindView {
    void getCourseDataSuccess(FindCourseCommResult model);
    void getLessonListDataSuccess(FindLessonListResult model);
    void getDataFail(String msg);
    void showLoading();
    void hideLoading();
}
