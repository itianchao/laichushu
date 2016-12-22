package com.laichushu.book.event;

/**
 * Created by PCPC on 2016/12/20.
 */

public class RefrushAunnManagerEvent {
    public boolean isRefursh() {
        return isRefursh;
    }

    public void setRefursh(boolean refursh) {
        isRefursh = refursh;
    }

    public boolean isRefursh;
    public RefrushAunnManagerEvent(boolean isRefursh) {
        this.isRefursh = isRefursh;
    }
}
