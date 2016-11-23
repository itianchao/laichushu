package com.laichushu.book.mvp.HomePage;

import com.laichushu.book.bean.netbean.HomePageFocusBeResult;
import com.laichushu.book.bean.netbean.HomePersonFocusResult;
import com.laichushu.book.bean.netbean.HomeUseDyrResult;
import com.laichushu.book.bean.netbean.HomeUserResult;
import com.laichushu.book.bean.netbean.MyHomeModel;

/**
 * Created by PCPC on 2016/11/22.
 */

public interface HomePageView {
    void getDyDataSuccess(HomeUseDyrResult model);
    void getFocusMeDataSuccess(HomePersonFocusResult model);
    void getFocusBeDataSuccess(HomePageFocusBeResult model);
    void getDataFail(String msg);
}
