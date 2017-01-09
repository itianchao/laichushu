package com.laichushu.book.mvp.msg.notice;

/**
 * 公告页面
 * Created by wangtong on 2016/10/12.
 */
public interface NoticeView {
    void getDataSuccess(NoticeModle model);
    void getDataFail(String msg);
    void showLoading();
    void hideLoading();
}
