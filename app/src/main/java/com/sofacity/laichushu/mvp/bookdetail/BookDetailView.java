package com.sofacity.laichushu.mvp.bookdetail;

import com.sofacity.laichushu.mvp.home.HomeHotModel;

/**
 * 图书详情
 * Created by wangtong on 2016/11/03.
 */
public interface BookDetailView {
    void getAuthorDetailData(AuthorDetailModle model);
    void getDataFail(String msg);
    void showLoading();
    void hideLoading();
    void getBestLikeSuggestlData(HomeHotModel model);
    void getSubscribeArticleData(SubscribeArticleModle model);
    void getArticleCommentData(ArticleCommentModle model);
}
