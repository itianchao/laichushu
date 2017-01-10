package com.laichushu.book.mvp.mine.personpage;

import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.HomeFocusResult;
import com.laichushu.book.bean.netbean.HomePersonFocusResult;
import com.laichushu.book.bean.netbean.HomeUseDyrResult;

/**
 * Created by PCPC on 2016/11/22.
 */

public interface HomePageView {
    void getDyDataSuccess(HomeUseDyrResult model);

    void getFocusMeDataSuccess(HomePersonFocusResult model);

    void getFocusBeDataSuccess(HomePersonFocusResult model);

    void getFocusBeStatus(HomeFocusResult model, boolean flg, HomePersonFocusResult.DataBean dataBean, int position, int type);

    void getSaveCollectSuccess(RewardResult model, String type, HomeUseDyrResult.DataBean dataBean, int position);

    void getFocusMeStatus(HomeFocusResult model, boolean isFocus, HomePersonFocusResult.DataBean dataBean, int position, int type);

    void getDataFail(String msg);

    void showDialog();

    void dismissDialog();
}
