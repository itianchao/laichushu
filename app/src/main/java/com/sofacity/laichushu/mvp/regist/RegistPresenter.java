package com.sofacity.laichushu.mvp.regist;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.sofacity.laichushu.bean.netbean.Login_Paramet;
import com.sofacity.laichushu.mvp.login.LoginModel;
import com.sofacity.laichushu.retrofit.ApiCallback;
import com.sofacity.laichushu.ui.activity.RegistActivity;
import com.sofacity.laichushu.ui.base.BasePresenter;

/**
 * 注册 presenter
 * Created by wangtong on 2016/10/12.
 */
public class RegistPresenter extends BasePresenter<RegistView> {
    private RegistActivity mActivity;

    //初始化构造
    public RegistPresenter(RegistView view) {
        attachView(view);
        mActivity = (RegistActivity) view;
    }
    public void loadCode(String phone){
        mvpView.showLoading();
//        Login_Paramet paramet = new Login_Paramet();
//        Logger.e("登录请求参数");
//        Logger.json(new Gson().toJson(paramet));
//        addSubscription(apiStores.loginLoadData(paramet),
//                new ApiCallback<LoginModel>() {
//                    @Override
//                    public void onSuccess(LoginModel model) {
//                        mvpView.getDataSuccess(model);
//                    }
//
//                    @Override
//                    public void onFailure(int code, String msg) {
//                        mvpView.getDataFail("code+" + code + "/msg:" + msg);
//                    }
//
//                    @Override
//                    public void onFinish() {
//                        mvpView.hideLoading();
//                    }
//
//                });
    }
}
