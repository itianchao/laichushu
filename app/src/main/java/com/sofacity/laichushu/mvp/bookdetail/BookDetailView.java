package com.sofacity.laichushu.mvp.bookdetail;

import com.sofacity.laichushu.bean.JsonBean.BalanceBean;
import com.sofacity.laichushu.bean.JsonBean.RewardResult;
import com.sofacity.laichushu.mvp.home.HomeHotModel;

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
}
