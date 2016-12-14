package com.laichushu.book.event;

/**
 * Created by PCPC on 2016/12/14.
 */

public class RefrushPerInfoEvent {
    public boolean isRefursh() {
        return isRefursh;
    }

    public void setRefursh(boolean refursh) {
        isRefursh = refursh;
    }

    public boolean isRefursh;
    public RefrushPerInfoEvent(boolean isRefursh) {
        this.isRefursh = isRefursh;
    }
}
