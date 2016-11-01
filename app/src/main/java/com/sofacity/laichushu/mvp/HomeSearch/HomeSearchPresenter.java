package com.sofacity.laichushu.mvp.HomeSearch;

import com.sofacity.laichushu.ui.activity.HomeSearchActivity;
import com.sofacity.laichushu.ui.base.BasePresenter;

/**
 * home 搜索
 * Created by wangtong on 2016/10/31.
 */
public class HomeSearchPresenter extends BasePresenter<HomeSearchView> {
    private HomeSearchActivity mActivity;

    //初始化构造
    public HomeSearchPresenter(HomeSearchView view) {
        attachView(view);
        mActivity = (HomeSearchActivity) view;
    }
}
