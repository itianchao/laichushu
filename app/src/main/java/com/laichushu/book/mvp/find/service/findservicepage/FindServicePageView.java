package com.laichushu.book.mvp.find.service.findservicepage;

import com.laichushu.book.bean.netbean.FindServiceInfoModel;

/**
 * Created by PCPC on 2016/12/26.
 */

public interface FindServicePageView {

    void getServercerListDataSuccess(FindServiceInfoModel model,final String serviceType, final String orderBy);
    void getDataFail(String msg);

    void showDialog();

    void dismissDialog();
}
