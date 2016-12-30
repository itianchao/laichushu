package com.laichushu.book.mvp.mineservice;

import com.laichushu.book.bean.netbean.BookDetailsModle;
import com.laichushu.book.mvp.home.HomeHotModel;

/**
 * Created by PCPC on 2016/12/30.
 */

public interface MineServiceView {
    void showDialog();

    void dismissDialog();

    void getDataFail(String msg);

    void getCollectionDataSuccess(HomeHotModel model);
    void getBookDetailsByIdDataSuccess(BookDetailsModle model);
}
