package com.laichushu.book.mvp.draftmodle;

/**
 * 草稿模式页面
 * Created by wangtong on 2016/11/18.
 */
public interface DraftModleView {
    void getDataSuccess(DraftModle model);
    void getDataFail(String msg);
    void showLoading();
    void hideLoading();
}
