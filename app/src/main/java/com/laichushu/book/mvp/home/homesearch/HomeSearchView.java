package com.laichushu.book.mvp.home.homesearch;

import com.laichushu.book.mvp.home.homelist.HomeHotModel;

/**
 * home 搜索
 * Created by wangtong on 2016/10/31.
 */
public interface HomeSearchView {
    void getDataSuccess(HomeHotModel model);

    void getHotSearchDataSuccess(HomeSearchModel model);

    void getDataFail(String msg);

    void getDataFail2(String msg);

}