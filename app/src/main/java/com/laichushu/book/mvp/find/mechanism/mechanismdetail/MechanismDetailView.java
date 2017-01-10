package com.laichushu.book.mvp.find.mechanism.mechanismdetail;

import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.mvp.home.campaign.AuthorWorksModle;

/**
 * 机构详情=页面
 * Created by wangtong on 2016/10/12.
 */
public interface MechanismDetailView {
    void getDataSuccess(MechanisDetailModel model);

    void getAuthorWorksDataSuccess(AuthorWorksModle model);

    void articleVote(RewardResult model);

    void collectSaveData(RewardResult model, boolean collect);

    void getSendMsgToPartyDataSuccess(RewardResult model);

    void getDataFail(String msg);

    void getDataFail2(String msg);

    void getDataFail3(String msg);

    void getDataFail4(String msg);

    void getDataFail5(String s);

    void showLoading();

    void hideLoading();

}
