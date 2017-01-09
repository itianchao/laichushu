package com.laichushu.book.mvp.entry.init;

import com.laichushu.book.bean.netbean.PersonalCentreResult;
import com.laichushu.book.mvp.home.homelist.HomeHotModel;
import com.laichushu.book.mvp.home.homelist.HomeModel;

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
