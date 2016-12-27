package com.laichushu.book.mvp.findeditmypage;

import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.FindEditorInfoModel;
import com.laichushu.book.mvp.allcomment.SendCommentMoudle;
import com.laichushu.book.mvp.bookdetail.ArticleCommentModle;
import com.laichushu.book.mvp.topicdetail.TopicdetailModel;

/**
 * Created by PCPC on 2016/12/24.
 */

public interface FindEditMyPageView {

    void getEditorInfoDataSuccess(FindEditorInfoModel model);

    void getSendDataSuccess(SendCommentMoudle model);

    void SaveScoreLikeData(RewardResult model, String type);

    void getEditorCommentListDataSuccess(TopicdetailModel model);

    void getSendMsgToPartyDataSuccess(RewardResult model);

    void getDataFail(String msg);

    void getDataFail5(String msg);

    void showDialog();

    void dismissDialog();
}
