package com.laichushu.book.mvp.find.group.findgrouplist;

import com.laichushu.book.mvp.find.group.GroupListModle;

/**
 * 发现 - 小组列表
 * Created by wangtong on 2016/12/29.
 */

public interface FindGroupListView {

    void getGroupListDataSuccess(GroupListModle modle);

    void getGroupListDataFail(String msg);
}
