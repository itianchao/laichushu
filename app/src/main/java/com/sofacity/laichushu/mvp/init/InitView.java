package com.sofacity.laichushu.mvp.init;

import com.sofacity.laichushu.mvp.home.HomeAllModel;
import com.sofacity.laichushu.mvp.home.HomeHotModel;
import com.sofacity.laichushu.mvp.home.HomeModel;

/**
 * init页面
 * Created by wangtong on 2016/10/12.
 */
public interface InitView {
    void getDataSuccess(HomeModel model);
    void getHotDataSuccess(HomeHotModel model);
    void getAllData(HomeHotModel model);
    void getDataFail(String msg);
    void showLoading();
    void hideLoading();
}
