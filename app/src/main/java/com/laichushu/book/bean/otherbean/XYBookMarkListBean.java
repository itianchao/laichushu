package com.laichushu.book.bean.otherbean;

import com.laichushu.book.db.Idea_Table;

/**
 * 点击事件
 * Created by wangtong on 2016/12/14.
 */

public class XYBookMarkListBean {
    private int x;
    private int y;
    private Idea_Table i;

    public XYBookMarkListBean(int x, int y, Idea_Table i) {
        this.x = x;
        this.y = y;
        this.i = i;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Idea_Table getI() {
        return i;
    }

    public void setI(Idea_Table i) {
        this.i = i;
    }
}
