package com.laichushu.book.bean.otherbean;

/**
 * 举报、书签
 * Created by wangtong on 2016/12/8.
 */

public class BookSelectOptionBean {
    private String name;
    private int path;

    public BookSelectOptionBean(String name, int path) {
        this.name = name;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPath() {
        return path;
    }

    public void setPath(int path) {
        this.path = path;
    }
}
