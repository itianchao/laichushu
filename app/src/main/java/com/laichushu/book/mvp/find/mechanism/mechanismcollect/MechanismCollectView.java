package com.laichushu.book.mvp.find.mechanism.mechanismcollect;

import com.laichushu.book.bean.JsonBean.MechanismListBean;
import com.laichushu.book.mvp.home.homelist.HomeHotModel;

/**
 * Created by PCPC on 2017/1/16.
 */

public interface MechanismCollectView {
    void getCollectionDataSuccess(HomeHotModel model);
    void getDataSuccess(MechanismListBean model,String partyId);
    void showDialog();

    void dismissDialog();

    void getDataFail(String msg);
}
