package com.laichushu.book.mvp.findeditmainpage;

import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.FindEditorInfoModel;
import com.laichushu.book.mvp.home.campaign.AuthorWorksModle;
import com.laichushu.book.mvp.home.homelist.HomeHotModel;
import com.laichushu.book.mvp.topicdetail.TopicdetailModel;

/**
 * Created by PCPC on 2016/12/24.
 */

public interface FindEditMainPageView {

    void getEditorInfoDataSuccess(FindEditorInfoModel model);

    void getSendDataSuccess(RewardResult model);
    void getEditorFindArticleDataSuccess(HomeHotModel model);
    void getSaveCollectSuccess(RewardResult model,String type);

    void SaveScoreLikeData(RewardResult model, String type);

    void getEditorCommentListDataSuccess(TopicdetailModel model);

    void getAuthorWorksDataSuccess(AuthorWorksModle model);
    void getArticleVoteDataSuccess(RewardResult model);
    void getSendMsgToPartyDataSuccess(RewardResult model);

    void getDataFail(String msg);

    void getDataFail5(String msg);

    void showDialog();

    void dismissDialog();
}
