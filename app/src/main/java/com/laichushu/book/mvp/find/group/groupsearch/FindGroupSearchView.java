package com.laichushu.book.mvp.find.group.groupsearch;

/**
 * 发现 - 小组 - 搜索
 * Created by wangtong on 2016/12/27.
 */

public interface FindGroupSearchView {
    void searchGroupDataSuccess(FindGroupModle model);
    void searchGroupDataFail(String msg);
}
