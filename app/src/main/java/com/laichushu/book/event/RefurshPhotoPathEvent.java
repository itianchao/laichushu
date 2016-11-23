package com.laichushu.book.event;

/**
 * 刷新活动列表
 * Created by wangtong on 2016/11/9.
 */

public class RefurshPhotoPathEvent {
    public String url;
    public RefurshPhotoPathEvent(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
