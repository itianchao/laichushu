package com.laichushu.book.mvp.campaign;

/**
 * 活动详情
 * Created by wangtong on 2016/10/12.
 */
public interface CampaignView {
    void getDataSuccess(CampaignModel model);
    void getJoinDataSuccess(CampaignJoinModel model, String type);
    void getAuthorWorksDataSuccess(AuthorWorksModle model);
    void getDataFail(String msg);
    void showLoading();
    void hideLoading();

}
