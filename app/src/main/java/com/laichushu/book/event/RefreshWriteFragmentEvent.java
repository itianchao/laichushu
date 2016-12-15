package com.laichushu.book.event;

/**
 * Created by PCPC on 2016/12/15.
 */

public class RefreshWriteFragmentEvent {
    public boolean isRefursh() {
        return isRefursh;
    }

    public void setRefursh(boolean refursh) {
        isRefursh = refursh;
    }

    public boolean isRefursh;
    public RefreshWriteFragmentEvent(boolean isRefursh) {
        this.isRefursh = isRefursh;
    }
}

