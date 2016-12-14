package com.laichushu.book.mvp.login;

import android.provider.Telephony;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.ui.activity.InitActivity;
import com.laichushu.book.ui.activity.LoginActivity;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.utils.SharePrefManager;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.Validator;
import com.orhanobut.logger.Logger;
import com.laichushu.book.anim.ShakeAnim;
import com.laichushu.book.bean.netbean.Login_Paramet;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.utils.UIUtil;

/**
 * 登录 presenter
 * Created by wangtong on 2016/10/12.
 */
public class LoginPresenter extends BasePresenter<LoginView> {
    private LoginActivity mActivity;

    //初始化构造
    public LoginPresenter(LoginView view) {
        attachView(view);
        mActivity = (LoginActivity) view;
    }

    //登陆前的判断
    public void preLogin() {
        String loginInfo = SharePrefManager.getLoginInfo();
        //保存正确的帐号密码 直接进app
        if (!"".equals(loginInfo)) {
            String[] loginStrs = loginInfo.split(",");
            String username = loginStrs[0];
            String password = loginStrs[1];
            UIUtil.openActivity(mActivity, InitActivity.class);
            mActivity.finish();
        }
    }

    //登陆
    public boolean login(EditText usernameEt, EditText passwordEt, Button loginBtn) {
        String username = usernameEt.getText().toString().trim();
        String password = passwordEt.getText().toString().trim();
        boolean isLogin ;
        //校验
        if (username.isEmpty()) {
            ToastUtil.showToast("手机号不能为空！");
            usernameEt.startAnimation(ShakeAnim.shakeAnimation(3));
            return isLogin = false;
        } else if (password.isEmpty()) {
            ToastUtil.showToast("密码不能为空！");
            passwordEt.startAnimation(ShakeAnim.shakeAnimation(3));
            return isLogin = false;
        } else if (username.length() < 11) {
            ToastUtil.showToast("手机号11位！");
            usernameEt.startAnimation(ShakeAnim.shakeAnimation(3));
            return isLogin = false;
        } else if (password.length() < 6) {
            ToastUtil.showToast("密码至少6位！");
            passwordEt.startAnimation(ShakeAnim.shakeAnimation(3));
            return isLogin = false;
        }
        if (!Validator.isMobile(username)){
            ToastUtil.showToast("请输入正确的手机号!");
            usernameEt.startAnimation(ShakeAnim.shakeAnimation(3));
            return isLogin = false;
        }
        if (!Validator.isUsername(password)) {
            ToastUtil.showToast("您输入的密码含有汉字或特殊字符，请重新输入！");
            passwordEt.startAnimation(ShakeAnim.shakeAnimation(3));
            return isLogin = false;
        }
        loginBtn.setEnabled(false);
        loadData(username, password);
        return isLogin = true;
    }

    //网络请求
    public void loadData(String username, String password) {
        mvpView.showLoading();
        Login_Paramet paramet = new Login_Paramet(username, password);
        Logger.e("登录请求参数");
        Logger.json(new Gson().toJson(paramet));
        addSubscription(apiStores.loginLoadData(paramet),
                new ApiCallback<LoginModel>() {
                    @Override
                    public void onSuccess(LoginModel model) {
                        mvpView.getDataSuccess(model);
                    }

                    @Override
                    public void onFailure(int code, String msg) {
                        mvpView.getDataFail("code+" + code + "/msg:" + msg);
                    }

                    @Override
                    public void onFinish() {
                        mvpView.hideLoading();
                    }

                });
    }

    public void lastLogin(String username, String password, String userId, String token, String type) {
        //记住密码
        SharePrefManager.setLoginInfo(username + "," + password);
        //保存userId
        SharePrefManager.setUserId(userId);
        ConstantValue.USERID = userId;
        //保存token
        SharePrefManager.setToken(token);
        //保存身份
        SharePrefManager.setType(type);
        //

        //延时跳转页面
        UIUtil.getMainThreadHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                UIUtil.openActivity(mActivity, InitActivity.class);
                mActivity.finish();
            }
        }, 1710);
    }
}