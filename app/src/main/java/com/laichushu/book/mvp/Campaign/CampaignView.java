package com.laichushu.book.mvp.campaign;

import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.CampaignDetailsModel;

/**
 * 活动详情
 * Created by wangtong on 2016/10/12.
 */
public interface CampaignView {

    void getJoinDataSuccess(CampaignJoinModel model, String type);

    void getAuthorWorksDataSuccess(AuthorWorksModle model);

    void getDataFail(String msg);

    void getDataFail3(String msg);

    void articleVote(RewardResult model);

    void getAddPerInfoSuccess(RewardResult model);

    void showLoading();

    void hideLoading();

    void getgetActivityByIdDataSuccess(CampaignModel model);

    void getDataFail2(String msg);
}
