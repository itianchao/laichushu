package com.laichushu.book.bean.otherbean;

/**
 * 封面模版
 * Created by wangtong on 2016/11/23.
 */

public class CoverDirBean {
    private String name;
    private int drawableId;

    public CoverDirBean(String name, int drawableId) {
        this.name = name;
        this.drawableId = drawableId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }
}
