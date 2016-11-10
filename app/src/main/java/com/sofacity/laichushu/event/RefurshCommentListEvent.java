package com.sofacity.laichushu.event;

/**
 * 刷新活动列表
 * Created by wangtong on 2016/11/9.
 */

public class RefurshCommentListEvent {
    public boolean isRefursh;
    public String type;
    public RefurshCommentListEvent(boolean isRefursh) {
        this.isRefursh = isRefursh;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
