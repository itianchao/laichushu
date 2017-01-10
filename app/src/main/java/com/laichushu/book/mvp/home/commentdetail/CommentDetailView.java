package com.laichushu.book.mvp.home.commentdetail;

import com.laichushu.book.bean.JsonBean.RewardResult;

/**
 * 评价详情页面
 * Created by wangtong on 2016/10/12.
 */
public interface CommentDetailView {
    void getDataSuccess(CommentDetailModle model);
    void getDataFail(String msg);
    void SaveScoreLikeData(RewardResult model, String type);
    void showLoading();
    void hideLoading();

}
