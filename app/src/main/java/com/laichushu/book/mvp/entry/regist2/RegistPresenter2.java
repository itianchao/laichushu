package com.laichushu.book.mvp.entry.regist2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.widget.TextView;

import com.google.gson.Gson;
import com.laichushu.book.bean.netbean.Login_Paramet;
import com.laichushu.book.bean.netbean.Regist_Paramet;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.mvp.entry.login.LoginModel;
import com.laichushu.book.ui.activity.InitActivity;
import com.laichushu.book.ui.activity.Regist2Activity;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.utils.SharePrefManager;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;
import com.laichushu.book.utils.Validator;
import com.laichushu.book.retrofit.ApiCallback;
import com.orhanobut.logger.Logger;

/**
 * 注册 presenter
 * Created by wangtong on 2016/10/12.
 */
public class RegistPresenter2 extends BasePresenter<RegistView2> {
    private Regist2Activity mActivity;

    //初始化构造
    public RegistPresenter2(RegistView2 view) {
        attachView(view);
        mActivity = (Regist2Activity) view;
    }

    //校验输入项
    public boolean check(String name, String sex,String curProCode, String pwd, String repwd) {
        boolean isCheck;
        if ("".equals(name)) {
            ToastUtil.showToast("请输入昵称");
            return isCheck = false;
        }
        if ("".equals(sex)) {
            ToastUtil.showToast("请选择性别");
            return isCheck = false;
        }
        if (TextUtils.isEmpty(curProCode)) {
            ToastUtil.showToast("请输入您所在城市!");
            return isCheck = false;
        }
        if ("".equals(pwd)) {
            ToastUtil.showToast("请输入密码");
            return isCheck = false;
        }
        if ("".equals(repwd)) {
            ToastUtil.showToast("请输入密码");
            return isCheck = false;
        }
        if (!pwd.equals(repwd)) {
            ToastUtil.showToast("两次输入的密码不一致");
            return isCheck = false;
        }
//        if (!Validator.isUsername(pwd) && !Validator.isUsername(repwd)) {
//            ToastUtil.showToast("账号或密码错误");
//            return isCheck = false;
//        }
        return isCheck = true;
    }

    //选择性别
    public void getSex(final TextView sexTv) {
        final String[] sex = new String[1];
        final String[] sexArray = {"男", "女"};
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        final int[] posion = new int[1];
        builder.setTitle("请选择性别")
                .setSingleChoiceItems(sexArray, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        posion[0] = which;
                    }
                })
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sex[0] = sexArray[posion[0]];
                        sexTv.setText(sex[0]);
                    }
                })
                .show();
    }

    //注册请求网络
    public void regist(String phonenum, String name, String sex,String curProCode, String pwd){
        if (sex.equals("男")){
            sex = "1";
        }else{
            sex = "2";
        }
        Regist_Paramet paramet = new Regist_Paramet(phonenum,name,curProCode,sex,pwd);
        addSubscription(apiStores.registData(paramet), new ApiCallback<RegistModel2>() {
            @Override
            public void onSuccess(RegistModel2 model) {
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

    //网络请求
    public void loginData(String username, String password) {
        mvpView.showLoading();
        Login_Paramet paramet = new Login_Paramet(username, password);
        Logger.e("登录请求参数");
        Logger.json(new Gson().toJson(paramet));
        addSubscription(apiStores.loginLoadData(paramet),
                new ApiCallback<LoginModel>() {
                    @Override
                    public void onSuccess(LoginModel model) {
                        mvpView.getLoginSuccess(model);
                    }

                    @Override
                    public void onFailure(int code, String msg) {
                        mvpView.getLoginFail("code+" + code + "/msg:" + msg);
                    }

                    @Override
                    public void onFinish() {
                        mvpView.hideLoading();
                    }

                });
    }

    /**
     * 登录后的操作
     * @param username
     * @param password
     * @param userId
     * @param token
     * @param type
     */
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
        UIUtil.postStartActivity(mActivity, InitActivity.class);
    }
}
