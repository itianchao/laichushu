package com.laichushu.book.mvp.topicmanage;

import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.HomeUseDyrResult;

/**
 * Created by PCPC on 2016/12/21.
 */

public interface TopicManageView {
    void getDeleteTopicDateSuccess(RewardResult model);
    void getTopicListDateSuccess(HomeUseDyrResult model);
    void getSaveCollectSuccess(RewardResult model,String type);
    void getDataFail(String msg);
    void showDialog();
    void dissmissDialog();
}
