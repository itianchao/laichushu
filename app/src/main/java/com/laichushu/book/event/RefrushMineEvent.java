package com.laichushu.book.event;

/**
 * Created by PCPC on 2016/12/7.
 */

public class RefrushMineEvent {
    public boolean isRefursh() {
        return isRefursh;
    }

    public void setRefursh(boolean refursh) {
        isRefursh = refursh;
    }

    public boolean isRefursh;
    public RefrushMineEvent(boolean isRefursh) {
        this.isRefursh = isRefursh;
    }
}
