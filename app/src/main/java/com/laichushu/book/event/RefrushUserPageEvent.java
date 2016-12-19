package com.laichushu.book.event;

/**
 * Created by PCPC on 2016/12/16.
 */

public class RefrushUserPageEvent {
    public boolean isRefursh() {
        return isRefursh;
    }

    public void setRefursh(boolean refursh) {
        isRefursh = refursh;
    }

    public boolean isRefursh;
    public RefrushUserPageEvent(boolean isRefursh) {
        this.isRefursh = isRefursh;
    }
}
