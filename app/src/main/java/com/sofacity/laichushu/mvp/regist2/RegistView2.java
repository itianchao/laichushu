package com.sofacity.laichushu.mvp.regist2;

/**
 * 注册页面
 * Created by wangtong on 2016/10/12.
 */
public interface RegistView2 {
    void getDataSuccess(RegistModel2 model);
    void getDataFail(String msg);
    void showLoading();
    void hideLoading();
}
