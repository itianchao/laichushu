package com.laichushu.book.mvp.mechanismtopiclist;

/**
 * 机构话题列表页面
 * Created by wangtong on 2016/11/26.
 */
public interface MechanismTopicListView {
    void getDataSuccess(MechanismTopicListModel model);
    void getDataFail(String msg);
    void showLoading();
    void hideLoading();
}
