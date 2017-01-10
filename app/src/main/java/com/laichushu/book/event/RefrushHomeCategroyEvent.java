package com.laichushu.book.event;

import com.laichushu.book.mvp.home.homelist.HomeHotModel;

/**
 * 刷新首页分类
 * Created by wangtong on 2016/12/21.
 */

public class RefrushHomeCategroyEvent {
    public boolean isRefursh;
    public HomeHotModel.DataBean bean;
    public RefrushHomeCategroyEvent(boolean isRefursh, HomeHotModel.DataBean bean) {
        this.isRefursh = isRefursh;
        this.bean = bean;
    }

    public boolean isRefursh() {
        return isRefursh;
    }

    public void setRefursh(boolean refursh) {
        isRefursh = refursh;
    }

    public HomeHotModel.DataBean getBean() {
        return bean;
    }

    public void setBean(HomeHotModel.DataBean bean) {
        this.bean = bean;
    }
}

