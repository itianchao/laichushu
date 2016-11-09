package com.sofacity.laichushu.mvp.allcomment;

import com.sofacity.laichushu.bean.JsonBean.RewardResult;
import com.sofacity.laichushu.mvp.bookdetail.ArticleCommentModle;

/**
 * 首页页面
 * Created by wangtong on 2016/10/12.
 */
public interface AllCommentView {
    void getDataSuccess(ArticleCommentModle model);
    void getSendDataSuccess(SendCommentMoudle model);
    void SaveScoreLikeData(RewardResult model, String type);
    void getDataFail(String msg);
    void showLoading();
    void hideLoading();

}
