package com.laichushu.book.mvp.msg.messagecomment;

import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.BookDetailsModle;
import com.laichushu.book.bean.netbean.MessageCommentResult;
import com.laichushu.book.bean.netbean.PerMsgInfoReward;
import com.laichushu.book.mvp.home.homelist.HomeHotModel;

/**
 * Created by PCPC on 2016/11/28.
 */

public interface MessageCommentView {
    void getMsgCommentDateSuccess(MessageCommentResult model);

    void getBookDetailsDateSuccess(HomeHotModel model, int position);

    void sendMsgDetailsDateSuccess(RewardResult model);

    void getPerInfoListDateSuccess(MessageCommentResult model);

    void getPerInfoDetailsDateSuccess(PerMsgInfoReward model);

    void getSendDataSuccess(RewardResult model);

    void getDelPerIdfoDataSuccess(RewardResult model);

    void getBookDetailsByIdDataSuccess(BookDetailsModle model);

    void messageDeleteCommentSuccess(RewardResult model, int position);

    void getDataFail(String msg,int flg);

    void showDialog();

    void dismissDialog();
}
