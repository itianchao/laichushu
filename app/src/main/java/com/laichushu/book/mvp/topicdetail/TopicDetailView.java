package com.laichushu.book.mvp.topicdetail;

import com.laichushu.book.bean.JsonBean.RewardResult;

/**
 * 登录页面
 * Created by wangtong on 2016/10/12.
 */
public interface TopicDetailView {
    void getDataSuccess(TopicdetailModel model);

    void SaveScoreLikeData(RewardResult model, String type);

    void getSendDataSuccess(RewardResult model);

    void getSaveCollectSuccess(RewardResult model, String type);

    void getDataFail(String msg);

    void getDataFail2(String msg);

    void showLoading();

    void hideLoading();

    void getTopicStickySuccess(RewardResult modle);

    void getTopicRecommendSuccess(RewardResult modle);

    void getTopicDeleteSuccess(RewardResult modle);

    void getDataFail3(String msg);
}
