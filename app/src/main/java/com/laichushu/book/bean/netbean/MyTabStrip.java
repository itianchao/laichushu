package com.laichushu.book.bean.netbean;

import java.io.Serializable;

/**
 * Created by PCPC on 2016/12/10.
 */

public class MyTabStrip implements Serializable {
    private String title;
    private int drawble;

    public MyTabStrip(String title, int drawble) {
        this.title = title;
        this.drawble = drawble;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDrawble() {
        return drawble;
    }

    public void setDrawble(int drawble) {
        this.drawble = drawble;
    }
}
