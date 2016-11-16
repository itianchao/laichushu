package com.laichushu.book.mvp.bookdetail;

import com.laichushu.book.bean.JsonBean.BalanceBean;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.mvp.home.HomeHotModel;

/**
 * 图书详情
 * Created by wangtong on 2016/11/03.
 */
public interface BookDetailView {
    void getAuthorDetailData(AuthorDetailModle model);
    void getDataFail(String msg);
    void getPayResult(RewardResult model);
    void showLoading();
    void hideLoading();
    void getBestLikeSuggestlData(HomeHotModel model);
    void getSubscribeArticleData(SubscribeArticleModle model, String type);
    void getArticleCommentData(ArticleCommentModle model);
    void getBalanceData(BalanceBean model);
    void getBalance2Data(BalanceBean model);
    void getRewardMoneyData(RewardResult model);
    void SaveScoreLikeData(RewardResult model, String type);
    void collectSaveData(RewardResult model);
    void getJurisdictionData(RewardResult model);
}
