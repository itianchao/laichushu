package com.laichushu.book.mvp.homePage;

import com.laichushu.book.bean.netbean.HomeFocusResult;
import com.laichushu.book.bean.netbean.HomePageFocusBeResult;
import com.laichushu.book.bean.netbean.HomePersonFocusResult;
import com.laichushu.book.bean.netbean.HomeUseDyrResult;

/**
 * Created by PCPC on 2016/11/22.
 */

public interface HomePageView {
    void getDyDataSuccess(HomeUseDyrResult model);

    void getFocusMeDataSuccess(HomePersonFocusResult model);

    void getFocusBeDataSuccess(HomePersonFocusResult model);

    void getFocusBeStatus(HomeFocusResult modle,boolean flg);

    void getFocusMeStatus(HomeFocusResult modle, boolean isFocus);

    void getDataFail(String msg);
}
