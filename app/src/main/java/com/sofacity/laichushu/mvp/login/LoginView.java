package com.sofacity.laichushu.mvp.login;

/**
 * 登录页面
 * Created by wangtong on 2016/10/12.
 */
public interface LoginView {
    void getDataSuccess(LoginModel model);
    void getDataFail(String msg);
    void showLoading();
    void hideLoading();
}
