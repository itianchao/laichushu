package com.laichushu.book.mvp.bookcast;

import com.laichushu.book.mvp.home.HomeHotModel;

/**
 * Created by PCPC on 2016/11/21.
 */

public interface BookcastView {
    void getDataSuccess(HomeHotModel model);

    void getDataFail(String msg);
}
