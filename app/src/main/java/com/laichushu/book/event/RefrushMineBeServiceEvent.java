package com.laichushu.book.event;

/**
 * Created by PCPC on 2016/12/31.
 */

public class RefrushMineBeServiceEvent {
    public boolean isRefursh() {
        return isRefursh;
    }

    public void setRefursh(boolean refursh) {
        isRefursh = refursh;
    }

    public boolean isRefursh;

    public RefrushMineBeServiceEvent(boolean isRefursh) {
        this.isRefursh = isRefursh;
    }
}
