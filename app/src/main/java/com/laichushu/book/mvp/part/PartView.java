package com.laichushu.book.mvp.part;

/**
 * 注册页面
 * Created by wangtong on 2016/10/12.
 */
public interface PartView {
    void getDataSuccess(PartModel model);
    void getDataFail(String msg);
    void showLoading();
    void hideLoading();
}
