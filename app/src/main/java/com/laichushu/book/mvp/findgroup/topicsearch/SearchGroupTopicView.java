package com.laichushu.book.mvp.findgroup.topicsearch;

import com.laichushu.book.mvp.findgroup.groupsearch.FindGroupModle;
import com.laichushu.book.mvp.mechanismtopiclist.MechanismTopicListModel;

/**
 * 小组话题搜索
 * Created by wangtong on 2017/1/3.
 */

public interface SearchGroupTopicView {

    void searchTopicDataSuccess(MechanismTopicListModel modle);

    void searchTopicDataFail(String msg);
}
