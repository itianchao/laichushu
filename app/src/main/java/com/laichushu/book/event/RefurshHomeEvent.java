package com.laichushu.book.event;

/**
 * 刷新活动列表
 * Created by wangtong on 2016/11/9.
 */

public class RefurshHomeEvent {
    public boolean isRefursh;
    public RefurshHomeEvent(boolean isRefursh) {
        this.isRefursh = isRefursh;
    }
}
