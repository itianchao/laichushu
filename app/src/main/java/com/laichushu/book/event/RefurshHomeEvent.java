package com.laichushu.book.event;

import com.laichushu.book.mvp.home.homelist.HomeHotModel;

/**
 * 刷新首页列表
 * Created by wangtong on 2016/11/9.
 */

public class RefurshHomeEvent {
    public boolean isRefursh;
    public HomeHotModel.DataBean bean;
    public RefurshHomeEvent(boolean isRefursh, HomeHotModel.DataBean bean) {
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
