package com.laichushu.book.mvp.findgroup.findtopiclist;

import com.laichushu.book.mvp.mechanismtopiclist.MechanismTopicListModel;

/**
 * 发现 - 小组 - 我（发表、回复、收藏）的话题
 * Created by wangtong on 2016/12/30.
 */

public interface FindGroupMineTopicView {
    void getDataSuccess(MechanismTopicListModel modle);

    void getDataFail(String msg);
}
