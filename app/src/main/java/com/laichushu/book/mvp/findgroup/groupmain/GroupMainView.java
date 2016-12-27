package com.laichushu.book.mvp.findgroup.groupmain;

import com.laichushu.book.mvp.mechanismtopiclist.MechanismTopicListModel;

/**
 * 小组主页
 * Created by wangtong on 2016/12/27.
 */

public interface GroupMainView {
    void getGroupListDataSuccess(GroupListModle modle);

    void getGroupListDataFail(String msg);

    void getTopicListDataSuccess(MechanismTopicListModel model);

    void getTopicListDataFail(String msg);
}