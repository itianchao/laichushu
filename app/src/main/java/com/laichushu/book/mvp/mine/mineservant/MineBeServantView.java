package com.laichushu.book.mvp.mine.mineservant;

import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.FindServerInfoModel;

/**
 * Created by PCPC on 2016/12/29.
 */

public interface MineBeServantView {

    void getSaveServerInfoDataSuccess(RewardResult model);
    void getEditorInfoDataSuccess(FindServerInfoModel model);
    void getDataFail(String msg);

    void showDialog();

    void dismissDialog();
}
