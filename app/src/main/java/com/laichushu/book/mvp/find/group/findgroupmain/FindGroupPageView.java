package com.laichushu.book.mvp.find.group.findgroupmain;

import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.mvp.find.mechanism.mechanismtopiclist.MechanismTopicListModel;

/**
 * 小组详情
 * Created by PCPC on 2016/12/26.
 */

public interface FindGroupPageView {
    void getGroupTopicListDataSuccess(MechanismTopicListModel modle);

    void getGroupTopicListDataFail(String msg);

    void getGroupSuggestTopicListSuccess(MechanismTopicListModel modle);

    void getGroupSuggestTopicListFail(String msg);

    void dismissGroupSuccess(RewardResult modle);

    void dismissGroupFail(String msg);

    void getLeaveGroupSuccess(RewardResult modle);

    void getLeaveGroupFail(String msg);

    void getJoinGroupSuccess(RewardResult modle);

    void getJoinGroupFail(String msg);
}
