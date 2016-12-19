package com.laichushu.book.event;

import com.laichushu.book.mvp.home.HomeHotModel;

/**
 * 刷新活动列表
 * Created by wangtong on 2016/11/9.
 */

public class RefurshBookDetaileCommentEvent {
    public int position;
    public HomeHotModel.DataBean bean;

    public RefurshBookDetaileCommentEvent(HomeHotModel.DataBean bean, int position) {
        this.position = position;
        this.bean = bean;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public HomeHotModel.DataBean getBean() {
        return bean;
    }

    public void setBean(HomeHotModel.DataBean bean) {
        this.bean = bean;
    }
}
