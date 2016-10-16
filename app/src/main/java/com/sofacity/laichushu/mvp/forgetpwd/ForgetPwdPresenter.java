package com.sofacity.laichushu.mvp.forgetpwd;

import com.sofacity.laichushu.ui.activity.ForgetPwdActivity;
import com.sofacity.laichushu.ui.base.BasePresenter;

/**
 * 重置密码 presenter
 * Created by wangtong on 2016/10/12.
 */
public class ForgetPwdPresenter extends BasePresenter<ForgetPwdView> {
    private ForgetPwdActivity mActivity;

    //初始化构造
    public ForgetPwdPresenter(ForgetPwdView view) {
        attachView(view);
        mActivity = (ForgetPwdActivity) view;
    }
}
