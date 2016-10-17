package com.sofacity.laichushu.mvp.home;

import com.sofacity.laichushu.ui.base.BasePresenter;

/**
 * 首页 presenter
 * Created by wangtong on 2016/10/17.
 */
public class HomePresenter extends BasePresenter<HomeView> {
    public HomePresenter(HomeView view) {
        attachView(view);
    }
}
