package com.laichushu.book.event;

/**
 * 收藏
 * Created by wangtong on 2017/2/10.
 */

public class CollectEvent {
    private String isCollect;
    public CollectEvent(String isCollect) {
        this.isCollect = isCollect;
    }

    public String getIsCollect() {
        return isCollect;
    }

    public void setIsCollect(String isCollect) {
        this.isCollect = isCollect;
    }
}
