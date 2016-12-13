package com.laichushu.book.event;

/**
 * Created by PCPC on 2016/12/8.
 */

public class RefreshHomePageEvent {
    public boolean isRefursh() {
        return isRefursh;
    }

    public void setRefursh(boolean refursh) {
        isRefursh = refursh;
    }

    public boolean isRefursh;
    public RefreshHomePageEvent(boolean isRefursh) {
        this.isRefursh = isRefursh;
    }
}

