package com.laichushu.book.event;

/**
 * 刷新草稿
 * Created by wangtong on 2016/11/9.
 */

public class RefurshDraftEvent {
    public boolean isRefursh;

    public RefurshDraftEvent(boolean isRefursh) {
        this.isRefursh = isRefursh;
    }
}
