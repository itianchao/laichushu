package com.laichushu.book.mvp.findgroup.findgrouplist;

import com.laichushu.book.mvp.findgroup.groupmain.GroupListModle;

/**
 * 发现 - 小组列表
 * Created by wangtong on 2016/12/29.
 */

public interface FindGroupListView {

    void getGroupListDataSuccess(GroupListModle modle);

    void getGroupListDataFail(String msg);
}
