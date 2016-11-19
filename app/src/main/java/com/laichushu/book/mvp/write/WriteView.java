package com.laichushu.book.mvp.write;

import com.laichushu.book.mvp.home.HomeHotModel;

/**
 * 写作
 * Created by wangtong on 2016/11/16.
 */

public interface WriteView {
    void getDataSuccess(HomeHotModel model);
    void getDataFail(String msg);
}
