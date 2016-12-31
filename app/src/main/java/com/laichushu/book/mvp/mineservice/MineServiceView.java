package com.laichushu.book.mvp.mineservice;

import com.laichushu.book.bean.netbean.BookDetailsModle;
import com.laichushu.book.bean.netbean.FindEditorListModel;
import com.laichushu.book.bean.netbean.FindServiceCooperateMode;
import com.laichushu.book.bean.netbean.FindServiceInfoModel;

/**
 * Created by PCPC on 2016/12/30.
 */

public interface MineServiceView {
    void showDialog();

    void dismissDialog();

    void getDataFail(String msg);

    void getCollectionDataSuccess(FindServiceInfoModel model);
    void getCooperateDataSuccess(FindServiceCooperateMode model);
}
