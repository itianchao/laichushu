package com.laichushu.book.event;

/**
 * Created by PCPC on 2016/12/19.
 */

public class RefrushWalletEvent {
    public boolean isRefursh() {
        return isRefursh;
    }

    public void setRefursh(boolean refursh) {
        isRefursh = refursh;
    }

    public boolean isRefursh;
    public RefrushWalletEvent(boolean isRefursh) {
        this.isRefursh = isRefursh;
    }
}
