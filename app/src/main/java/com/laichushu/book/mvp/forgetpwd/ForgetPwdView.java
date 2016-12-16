package com.laichushu.book.mvp.forgetpwd;

import com.laichushu.book.bean.JsonBean.RewardResult;

/**
 * 重置密码页面
 * Created by wangtong on 2016/10/12.
 */
public interface ForgetPwdView {
    void getDataSuccess(ForgetPwdModel model);
    void getCodeDataSuccess(RewardResult model);
    void getDataFail(String msg);
    void showLoading();
    void hideLoading();
}
