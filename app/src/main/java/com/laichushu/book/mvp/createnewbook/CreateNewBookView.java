package com.laichushu.book.mvp.createnewbook;

import com.laichushu.book.mvp.home.HomeHotModel;

/**
 * 创建新书
 * Created by wangtong on 2016/11/17.
 */

public interface CreateNewBookView {
    void getCategoryDataSuccess(CreateNewBookModle modle);

    void getDataSuccess(HomeHotModel modle);

    void getDataFail(String msg);

    void showLoading();

    void hideLoading();
}
