package com.laichushu.book.mvp.regist;

/**
 * 注册页面
 * Created by wangtong on 2016/10/12.
 */
public interface RegistView {
    void getDataSuccess(RegistModel model);
    void getDataSuccess(RegistCodeModel model);
    void getDataFail(String msg);
    void showLoading();
    void hideLoading();
}
