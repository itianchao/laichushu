package com.laichushu.book.mvp.bookdetail;

import com.laichushu.book.bean.JsonBean.BalanceBean;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.mvp.home.HomeHotModel;

/**
 * 图书详情
 * Created by wangtong on 2016/11/03.
 */
public interface BookDetailView {
    void getDataFail(String msg);
    void getPayResult(RewardResult modle);
    void showLoading();
    void hideLoading();
    void getBookDataSuccess(BookDetailModle modle);
    void getBookDataError(String msg);
    void getSubscribeArticleData(SubscribeArticleModle modle, String type);
    void getBalanceData(BalanceBean modle);
    void getBalance2Data(BalanceBean modle);
    void getRewardMoneyData(RewardResult model);
    void SaveScoreLikeData(RewardResult modle, String type);
    void collectSaveData(RewardResult modle);
    void getJurisdictionData(RewardResult modle);
}
