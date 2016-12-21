package com.laichushu.book.event;

import com.laichushu.book.mvp.home.HomeHotModel;

/**
 * 刷新首页搜索
 * Created by wangtong on 2016/12/21.
 */

public class RefrushHomeSearchEvent {
    public boolean isRefursh;
    public HomeHotModel.DataBean bean;
    public RefrushHomeSearchEvent(boolean isRefursh, HomeHotModel.DataBean bean) {
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

