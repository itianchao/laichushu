package com.laichushu.book.event;

import org.geometerplus.zlibrary.ui.android.view.ZLAndroidWidget;

/**
 * fb控件view
 * Created by wangtong on 2016/12/13.
 */

public class FBReaderZLAndroidWidgetEvent {
    ZLAndroidWidget mZLAndroidWidget;

    public FBReaderZLAndroidWidgetEvent(ZLAndroidWidget mZLAndroidWidget) {
        this.mZLAndroidWidget = mZLAndroidWidget;
    }

    public ZLAndroidWidget getmZLAndroidWidget() {
        return mZLAndroidWidget;
    }

    public void setmZLAndroidWidget(ZLAndroidWidget mZLAndroidWidget) {
        this.mZLAndroidWidget = mZLAndroidWidget;
    }
}
