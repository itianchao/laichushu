package com.laichushu.book.event;

import com.laichushu.book.mvp.home.HomeHotModel;

/**
 * 刷新写作书籍详情
 * Created by PCPC on 2016/12/15.
 */

public class RefrushWriteFragmentEvent {
    public boolean isRefursh;
    public HomeHotModel.DataBean bean;
    public RefrushWriteFragmentEvent(boolean isRefursh, HomeHotModel.DataBean bean) {
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

