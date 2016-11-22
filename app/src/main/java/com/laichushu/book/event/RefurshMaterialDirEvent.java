package com.laichushu.book.event;

/**
 * 刷新素材目录
 * Created by wangtong on 2016/11/22.
 */

public class RefurshMaterialDirEvent {
    public boolean isRefursh;

    public RefurshMaterialDirEvent(boolean isRefursh) {
        this.isRefursh = isRefursh;
    }
}
