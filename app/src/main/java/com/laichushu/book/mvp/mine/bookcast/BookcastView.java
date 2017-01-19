package com.laichushu.book.mvp.mine.bookcast;

import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.BookDetailsModle;
import com.laichushu.book.mvp.home.homelist.HomeHotModel;

import java.util.List;

/**
 * Created by PCPC on 2016/11/21.
 */

public interface BookcastView {
    void getDataSuccess(HomeHotModel model);

    void getCollectionDataSuccess(HomeHotModel model);

    void getBookDetailsByIdDataSuccess(BookDetailsModle modle);

    void getDeleteBookByIdDataSuccess(RewardResult modle, int position);

    void getDataFail(String msg, int flg);

    void showDialog();

    void dismissDialog();
}
