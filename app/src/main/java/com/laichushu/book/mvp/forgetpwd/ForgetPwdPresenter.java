package com.laichushu.book.mvp.forgetpwd;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.TextView;

import com.google.gson.Gson;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.SendMsg_Paramet;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.Validator;
import com.orhanobut.logger.Logger;
import com.laichushu.book.bean.netbean.ForgetPwd_Paramet;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.activity.ForgetPwdActivity;

/**
 * 重置密码 presenter
 * Created by wangtong on 2016/10/12.
 */
public class ForgetPwdPresenter extends BasePresenter<ForgetPwdView> {
    private ForgetPwdActivity mActivity;
    private String type = ConstantValue.FORGETPWD_CODE;

    //初始化构造
    public ForgetPwdPresenter(ForgetPwdView view) {
        attachView(view);
        mActivity = (ForgetPwdActivity) view;
    }

    public void loadCode(String phone) {
        SendMsg_Paramet paramet = new SendMsg_Paramet(phone,type);

        LoggerUtil.e("校验code参数");
        LoggerUtil.toJson(new Gson().toJson(paramet));
        addSubscription(apiStores.sendMsg(paramet), new ApiCallback<RewardResult>() {
            @Override
            public void onSuccess(RewardResult model) {
                mvpView.getCodeDataSuccess(model);
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

    //更新验证码显示

    private int TIME = 90; // 倒计时60s
    private static final int CODE_ING = 1;                // 已发送，倒计时
    private static final int CODE_REPEAT = 2;                // 重新发送
    private String content;
    private TextView codeTv;

    public void showCode(final TextView codeTv, String phonenum) {
        this.codeTv = codeTv;
        if (TextUtils.isEmpty(phonenum)) {
            ToastUtil.showToast("手机号不能为空!");
            return;
        }
        if (!Validator.isMobile(phonenum)) {
            ToastUtil.showToast("请输入正确的手机号!");
            return;
        }
        loadCode(phonenum);
        codeTv.setClickable(false);
        TIME = 60;
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 60; i > 0; i--) {
                    mHandler.sendEmptyMessage(CODE_ING);
                    if (i <= 0) break;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                mHandler.sendEmptyMessage(CODE_REPEAT);
            }
        }).start();
    }

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
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

    public boolean check(String code, String phone, String newPwd, String rePwd) {
        boolean isCheck;
        if (phone.isEmpty()) {
            ToastUtil.showToast("手机号不能为空！");
            return isCheck = false;
        } else if (newPwd.isEmpty() || rePwd.isEmpty()) {
            ToastUtil.showToast("密码不能为空！");
            return isCheck = false;
        } else if (phone.length() < 11) {
            ToastUtil.showToast("手机号11位！");
            return isCheck = false;
        } else if (newPwd.length() < 6 || rePwd.length() < 6) {
            ToastUtil.showToast("密码至少6位！");
            return isCheck = false;
        }
        if (!Validator.isMobile(phone)) {
            ToastUtil.showToast("请输入正确的手机号!");
            return isCheck = false;
        }
        if (!Validator.isUsername(newPwd) || !Validator.isUsername(rePwd)) {
            ToastUtil.showToast("您输入的密码含有汉字或特殊字符，请重新输入！");
            return isCheck = false;
        }
        if (code.length() < 4) {
            ToastUtil.showToast("验证码位数不正确，请重新输入！");
            return isCheck = false;
        } else if (code.length() > 8) {
            ToastUtil.showToast("验证码位数不正确，请重新输入！");
            return isCheck = false;
        } else if (!Validator.isUsername(code)) {
            ToastUtil.showToast("您输入的验证码含有汉字或特殊字符，请重新输入！");
            return isCheck = false;
        }
        return isCheck = true;
    }

    /***
     * 重置密码
     *
     * @param phone
     * @param newPwd
     * @param rePwd
     */
    public void reset(String phone, String newPwd, String rePwd) {
        mvpView.showLoading();
        ForgetPwd_Paramet paramet = new ForgetPwd_Paramet(phone, newPwd, rePwd);
        Logger.e("登录请求参数");
        Logger.json(new Gson().toJson(paramet));
        addSubscription(apiStores.forgetPwdData(paramet),
                new ApiCallback<ForgetPwdModel>() {
                    @Override
                    public void onSuccess(ForgetPwdModel model) {
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
}
