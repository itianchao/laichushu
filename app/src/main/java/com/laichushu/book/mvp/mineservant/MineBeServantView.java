package com.laichushu.book.mvp.mineservant;

import com.laichushu.book.bean.JsonBean.RewardResult;

/**
 * Created by PCPC on 2016/12/29.
 */

public interface MineBeServantView {

    void getSaveServerInfoDataSuccess(RewardResult model);
    void getDataFail(String msg);

    void showDialog();

    void dismissDialog();
}
