package com.laichushu.book.mvp.forgetpwd;

/**
 * 重置密码页面
 * Created by wangtong on 2016/10/12.
 */
public interface ForgetPwdView {
    void getDataSuccess(ForgetPwdModel model);
    void getCodeDataSuccess(ForgetPwdCodeModel model);
    void getDataFail(String msg);
    void showLoading();
    void hideLoading();
}
