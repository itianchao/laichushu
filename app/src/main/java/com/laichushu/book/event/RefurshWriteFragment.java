package com.laichushu.book.event;

/**
 * 刷新写作页面
 * Created by wangtong on 2016/12/19.
 */

public class RefurshWriteFragment {
    private boolean isRefursh;

    public RefurshWriteFragment(boolean isRefursh) {
        this.isRefursh = isRefursh;
    }

    public boolean isRefursh() {
        return isRefursh;
    }

    public void setRefursh(boolean refursh) {
        isRefursh = refursh;
    }
}
