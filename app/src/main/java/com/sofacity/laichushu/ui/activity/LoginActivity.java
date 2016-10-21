package com.sofacity.laichushu.ui.activity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.sofacity.laichushu.R;
import com.sofacity.laichushu.mvp.login.LoginModel;
import com.sofacity.laichushu.mvp.login.LoginPresenter;
import com.sofacity.laichushu.mvp.login.LoginView;
import com.sofacity.laichushu.ui.base.MvpActivity;
import com.sofacity.laichushu.utils.AMUtils;
import com.sofacity.laichushu.utils.DialogUtil;
import com.sofacity.laichushu.utils.ToastUtil;
import com.sofacity.laichushu.utils.UIUtil;

/**
 * 登录页面
 * Created by wangtong on 2016/10/11.
 */
public class LoginActivity extends MvpActivity<LoginPresenter> implements LoginView, View.OnClickListener {

    private TextView titleTv;
    private ImageView backIv;
    private EditText usernameEt;
    private EditText passwordEt;
    private Button loginBt;
    private TextView registerTv;
    private TextView forgetTv;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_login);
        titleTv = (TextView) findViewById(R.id.tv_title);
        backIv = (ImageView) findViewById(R.id.iv_title_finish);
        usernameEt = (EditText) findViewById(R.id.et_username);
        passwordEt = (EditText) findViewById(R.id.et_password);
        loginBt = (Button) findViewById(R.id.bt_login);
        registerTv = (TextView) findViewById(R.id.tv_register);
        forgetTv = (TextView) findViewById(R.id.tv_forget);
    }

    @Override
    protected void initData() {
        titleTv.setText(UIUtil.getString(R.string.login));
        backIv.setVisibility(View.INVISIBLE);
        loginBt.setOnClickListener(this);
        registerTv.setOnClickListener(this);
        forgetTv.setOnClickListener(this);
        mvpPresenter.preLogin();
        addEditListen();
    }

    //控制器
    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_login:
                //登录
                if (mvpPresenter.login(usernameEt,passwordEt)) {
                    //请求网络登录
                }
                break;
            case R.id.tv_register:
                //注册
                UIUtil.openActivity(mActivity, RegistActivity.class);
                break;
            case R.id.tv_forget:
                UIUtil.openActivity(mActivity, ForgetPwdActivity.class);
                //忘记密码
                break;
        }
    }

    //视图
    @Override
    public void getDataSuccess(LoginModel model) {
        hideLoading();
        if (model.isSuccess()) {
            String username = usernameEt.getText().toString().trim();
            String password = passwordEt.getText().toString().trim();
            mvpPresenter.lastLogin(username, password);
        } else {
            String errMsg = model.getErrMsg();
            if (errMsg.contains(UIUtil.getString(R.string.errMsg2))) {
                DialogUtil.showDialog();
            } else if (errMsg.contains(UIUtil.getString(R.string.errMsg3))) {
                ToastUtil.showToast(UIUtil.getString(R.string.errMsg3));
            }else if (errMsg.contains(UIUtil.getString(R.string.errMsg4))) {
                ToastUtil.showToast(UIUtil.getString(R.string.errMsg4));
            } else {
                ToastUtil.showToast(UIUtil.getString(R.string.errMsg1));
            }
        }
    }

    @Override
    public void getDataFail(String msg) {
        toastShow("网络连接失败，请检查网络设置");
        Logger.e("网络失败原因：", msg);
    }

    @Override
    public void showLoading() {
        showProgressDialog("登录中...");
    }

    @Override
    public void hideLoading() {
        dismissProgressDialog();
    }

    private void addEditListen() {
        usernameEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 11) {
                    AMUtils.onInactive(mActivity, usernameEt);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
