package com.laichushu.book.mvp.home.allcomment;

import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.mvp.home.bookdetail.ArticleCommentModle;

/**
 * 首页页面
 * Created by wangtong on 2016/10/12.
 */
public interface AllCommentView {
    void getDataSuccess(ArticleCommentModle model);
    void getSendDataSuccess(SendCommentMoudle model);
    void SaveScoreLikeData(RewardResult model, String type, int position);
    void getDataFail(String msg);
    void showLoading();
    void hideLoading();

}
