package com.laichushu.book.mvp.write;

import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.mvp.home.HomeHotModel;

/**
 * 写作
 * Created by wangtong on 2016/11/16.
 */

public interface WriteView {
    void getDataSuccess(HomeHotModel model);
    void deleteNewBook(RewardResult model, int position);
    void articleVote(RewardResult model);
    void publishNewBook(RewardResult model);
    void getDataFail(String msg);
    void getDataFail2(String msg);
    void getDataFail3(String msg);
    void getDataFail4(String msg);
    void showLoading();
    void hideLoading();

}
