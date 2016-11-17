package com.laichushu.book.event;

/**
 * 刷新写作页面
 * Created by wangtong on 2016/11/17.
 */

public class RefurshWriteFragmentEvent {
    boolean isRefursh;

    public RefurshWriteFragmentEvent(boolean isRefursh) {
        this.isRefursh = isRefursh;
    }

    public boolean isRefursh() {
        return isRefursh;
    }

    public void setRefursh(boolean refursh) {
        isRefursh = refursh;
    }
}
