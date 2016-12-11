package com.laichushu.book.event;

import org.geometerplus.fbreader.book.Bookmark;

/**
 * 传递标签
 * Created by wt on 2016/12/10.
 */

public class TransmitBookMarkEvent {
    private Bookmark myBookmark;

    public Bookmark getMyBookmark() {
        return myBookmark;
    }

    public void setMyBookmark(Bookmark myBookmark) {
        this.myBookmark = myBookmark;
    }
}
