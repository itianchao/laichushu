package com.laichushu.book.event;

/**
 * Created by PCPC on 2016/12/19.
 */

public class RefreshWalletEvent {
    public boolean isRefursh() {
        return isRefursh;
    }

    public void setRefursh(boolean refursh) {
        isRefursh = refursh;
    }

    public boolean isRefursh;
    public RefreshWalletEvent(boolean isRefursh) {
        this.isRefursh = isRefursh;
    }
}
