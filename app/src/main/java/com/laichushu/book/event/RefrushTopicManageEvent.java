package com.laichushu.book.event;

/**
 * Created by PCPC on 2016/12/21.
 */

public class RefrushTopicManageEvent {
    public boolean isRefursh() {
        return isRefursh;
    }

    public void setRefursh(boolean refursh) {
        isRefursh = refursh;
    }

    public boolean isRefursh;

    public RefrushTopicManageEvent(boolean isRefursh) {
        this.isRefursh = isRefursh;
    }
}
