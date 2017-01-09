package com.laichushu.book.mvp.find.group.groupmain;

import com.laichushu.book.mvp.find.mechanism.mechanismtopiclist.MechanismTopicListModel;

/**
 * 小组主页
 * Created by wangtong on 2016/12/27.
 */

public interface GroupMainView {
    void getGroupListDataSuccess(GroupListModle modle);

    void getGroupListDataFail(String msg);

    void getNewTopicListDataSuccess(MechanismTopicListModel modle);

    void getNewTopicDataFail(String msg);


}