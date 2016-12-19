package com.laichushu.book.mvp.write;

import com.laichushu.book.mvp.home.HomeModel;

/**
 * Created by PCPC on 2016/12/19.
 */

public interface FindView {
    void getDataSuccess(HomeModel model);
    void getDataFail(String msg);
    void showLoading();
    void hideLoading();
}
