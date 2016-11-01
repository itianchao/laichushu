package com.sofacity.laichushu.mvp.HomeSearch;

/**
 * home 搜索
 * Created by wangtong on 2016/10/31.
 */
public interface HomeSearchView {
    void getDataSuccess(HomeSearchModel model);
    void getDataFail(String msg);
    void showLoading();
    void hideLoading();
}
