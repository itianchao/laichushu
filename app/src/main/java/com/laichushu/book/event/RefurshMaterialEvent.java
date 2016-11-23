package com.laichushu.book.event;

/**
 * 刷新素材
 * Created by wangtong on 2016/11/22.
 */

public class RefurshMaterialEvent {
    public boolean isRefursh;

    public RefurshMaterialEvent(boolean isRefursh) {
        this.isRefursh = isRefursh;
    }
}
