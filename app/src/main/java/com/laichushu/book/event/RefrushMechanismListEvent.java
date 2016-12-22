package com.laichushu.book.event;

/**
 * Created by PCPC on 2016/12/22.
 */

public class RefrushMechanismListEvent {
    public boolean isRefursh() {
        return isRefursh;
    }

    public void setRefursh(boolean refursh) {
        isRefursh = refursh;
    }

    public boolean isRefursh;
    public RefrushMechanismListEvent(boolean isRefursh) {
        this.isRefursh = isRefursh;
    }

}
