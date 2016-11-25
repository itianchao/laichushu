package com.laichushu.book.mvp.mechanismdetail;

import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.mvp.campaign.AuthorWorksModle;

/**
 * 注册页面
 * Created by wangtong on 2016/10/12.
 */
public interface MechanismDetailView {
    void getDataSuccess(MechanisDetailModel model);

    void getAuthorWorksDataSuccess(AuthorWorksModle model);

    void articleVote(RewardResult model);

    void getDataFail(String msg);

    void getDataFail2(String msg);

    void getDataFail3(String msg);

    void showLoading();

    void hideLoading();

}
