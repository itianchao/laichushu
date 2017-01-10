package com.laichushu.book.mvp.home;

/**
 * 首页页面
 * Created by wangtong on 2016/10/12.
 */
public interface HomeView {
    void getDataSuccess(HomeModel model);
    void getHotDataSuccess(HomeHotModel model);
    void getAllData(HomeHotModel model);
    void getDataFail(String msg);
    void showLoading();
    void hideLoading();
    void getActivityData(HomeHotModel model);
    void getActivityByCityData(HomeHotModel model);
}
