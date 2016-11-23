package com.laichushu.book.mvp.HomePage;

import com.laichushu.book.bean.netbean.HomeUseDyrResult;
import com.laichushu.book.bean.netbean.HomeUserResult;
import com.laichushu.book.bean.netbean.MyHomeModel;

/**
 * Created by PCPC on 2016/11/22.
 */

public interface HomePageView {
    void getDyDataSuccess(HomeUseDyrResult model);
//    void getFocusMeDataSuccess(MyHomeModel model);
//    void getFocusDataSuccess(MyHomeModel model);
    void getDataFail(String msg);
}
