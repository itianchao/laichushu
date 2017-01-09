package com.laichushu.book.mvp.entry.regist2;

import com.laichushu.book.mvp.entry.login.LoginModel;

/**
 * 注册页面
 * Created by wangtong on 2016/10/12.
 */
public interface RegistView2 {
    void getDataSuccess(RegistModel2 model);
    void getDataFail(String msg);
    void showLoading();
    void hideLoading();

    void getLoginSuccess(LoginModel model);
    void getLoginFail(String msg);
}
