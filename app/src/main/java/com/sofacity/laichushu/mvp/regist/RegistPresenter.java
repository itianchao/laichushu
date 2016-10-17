package com.sofacity.laichushu.mvp.regist;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.TextView;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.sofacity.laichushu.bean.netbean.Login_Paramet;
import com.sofacity.laichushu.mvp.login.LoginModel;
import com.sofacity.laichushu.retrofit.ApiCallback;
import com.sofacity.laichushu.ui.activity.RegistActivity;
import com.sofacity.laichushu.ui.base.BasePresenter;
import com.sofacity.laichushu.utils.ToastUtil;
import com.sofacity.laichushu.utils.UIUtil;
import com.sofacity.laichushu.utils.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
//        mvpView.showLoading();
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

    //更新验证码显示

    private int	TIME = 60; // 倒计时60s
    private static final int	CODE_ING		= 1;				// 已发送，倒计时
    private static final int	CODE_REPEAT		= 2;				// 重新发送
    private String content;
    private TextView codeTv;
    public void showCode(final TextView codeTv, String phonenum){
        this.codeTv = codeTv;
        if(TextUtils.isEmpty(phonenum)){
            ToastUtil.showToast("手机号不能为空!");
            return;
        }
        if(!Validator.isMobile(phonenum)){
            ToastUtil.showToast("请输入正确的手机号!");
            return;
        }
        loadCode(phonenum);
        codeTv.setClickable(false);
        TIME = 60;
        new Thread(new Runnable() {
            @Override
            public void run(){
                for (int i = 60; i > 0; i--){
                    mHandler.sendEmptyMessage(CODE_ING);
                    if (i <= 0) break;
                    try{
                        Thread.sleep(1000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }

                mHandler.sendEmptyMessage(CODE_REPEAT);
            }
        }).start();
    }

    private Handler mHandler = new Handler(){
        public void handleMessage(Message msg){
            switch (msg.what){
                case CODE_ING:// 已发送,倒计时
                    content = "重新发送(" + --TIME + "s)";
                    break;
                case CODE_REPEAT:// 重新发送
                    content = "获取验证码";
                    codeTv.setClickable(true);
                    break;
            }
            codeTv.setText(getContent());
        }
    };

    public String getContent() {
        return content;
    }

    //校验
    public boolean check(String phone, String code, boolean b) {
        boolean isCheck ;

        if(TextUtils.isEmpty(phone)){
            ToastUtil.showToast("帐号不能为空!");
            return isCheck = false;
        }
        if(TextUtils.isEmpty(code)){
            ToastUtil.showToast("验证码不能为空!");
            return isCheck = false;
        }
        if (code.length()<4){
            ToastUtil.showToast("验证码位数不正确，请重新输入！");
            return isCheck = false;
        }
        if (code.length()<8){
            ToastUtil.showToast("验证码位数不正确，请重新输入！");
            return isCheck = false;
        }
        if (!Validator.isUsername(code)){
            ToastUtil.showToast("您输入的验证码含有汉字或特殊字符，请重新输入！");
            return isCheck = false;
        }
        if (b){
            ToastUtil.showToast("请同意来出书的用户协议!");
            return isCheck = false;
        }
        if (!Validator.isUsername(phone)){
            ToastUtil.showToast("您输入的帐号含有汉字或特殊字符，请重新输入！");
            return isCheck = false;
        }

        if (!Validator.isMobile(phone)) {
            ToastUtil.showToast("请输入正确的手机号！");
            return isCheck = false;
        }
        return isCheck = true;
    }
}
