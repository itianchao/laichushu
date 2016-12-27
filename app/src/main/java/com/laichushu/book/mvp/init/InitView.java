package com.laichushu.book.mvp.init;

import com.laichushu.book.bean.netbean.PersonalCentreResult;
import com.laichushu.book.mvp.home.HomeHotModel;
import com.laichushu.book.mvp.home.HomeModel;

/**
 * init页面
 * Created by wangtong on 2016/10/12.
 */
public interface InitView {
    void getDataSuccess(HomeModel model);
    void getHotDataSuccess(HomeHotModel model);
    void getAllData(HomeHotModel model);
    void loadMineDataSuccess(PersonalCentreResult result);
    void getDataFail(String msg);
    void showLoading();

}
