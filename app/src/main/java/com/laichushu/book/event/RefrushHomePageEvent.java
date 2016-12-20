package com.laichushu.book.event;

/**
 * Created by PCPC on 2016/12/8.
 */

public class RefrushHomePageEvent {
    public boolean isRefursh() {
        return isRefursh;
    }

    public void setRefursh(boolean refursh) {
        isRefursh = refursh;
    }

    public boolean isRefursh;
    public RefrushHomePageEvent(boolean isRefursh) {
        this.isRefursh = isRefursh;
    }
}

