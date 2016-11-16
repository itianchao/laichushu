package com.sofacity.laichushu.mvp.HomeSearch;

import com.sofacity.laichushu.mvp.home.HomeHotModel;

/**
 * home 搜索
 * Created by wangtong on 2016/10/31.
 */
public interface HomeSearchView {
    void getDataSuccess(HomeHotModel model);
    void getDataFail(String msg);
}
