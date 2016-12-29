package com.laichushu.book.mvp.findgroup.findgroupmain;

import com.laichushu.book.mvp.mechanismtopiclist.MechanismTopicListModel;

/**
 * 小组详情
 * Created by PCPC on 2016/12/26.
 */

public interface FindGroupPageView {
    void getGroupTopicListDataSuccess(MechanismTopicListModel model);

    void getGroupTopicListDataFail(String msg);

    void getGroupSuggestTopicListSuccess(MechanismTopicListModel model);

    void getGroupSuggestTopicListFail(String msg);
}
