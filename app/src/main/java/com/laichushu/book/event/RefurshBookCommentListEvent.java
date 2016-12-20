package com.laichushu.book.event;

/**
 * 刷新刷新图书详情评论列表和评论数
 * Created by wangtong on 2016/12/19.
 */

public class RefurshBookCommentListEvent {
    public boolean isRefursh;
    public String type;
    public int size;
    public RefurshBookCommentListEvent(boolean isRefursh, int size) {
        this.isRefursh = isRefursh;
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
