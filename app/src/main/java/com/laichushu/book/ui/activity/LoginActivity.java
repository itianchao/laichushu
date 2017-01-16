package com.laichushu.book.ui.activity;

import android.graphics.Paint;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.mvp.entry.login.LoginModel;
import com.laichushu.book.mvp.entry.login.LoginPresenter;
import com.laichushu.book.mvp.entry.login.LoginView;
import com.laichushu.book.ui.base.MvpActivity;
import com.laichushu.book.utils.AMUtils;
import com.laichushu.book.utils.DialogUtil;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;
import com.orhanobut.logger.Logger;

/**
 * 登录页面
 * Created by wangtong on 2016/10/11.
 */
public class LoginActivity extends MvpActivity<LoginPresenter> implements LoginView,View.OnClickListener ,RadioGroup.OnCheckedChangeListener{

    private TextView titleTv;
    private ImageView backIv;
    private EditText usernameEt;
    private EditText passwordEt;
    private Button loginBtn;
    private TextView registerTv;
    private TextView forgetTv;
    private RadioGroup radioGroup;
    private ImageView logo;
    @Override
    protected void initView() {
        setContentView(R.layout.activity_login);
        titleTv = (TextView) findViewById(R.id.tv_title);
        backIv = (ImageView) findViewById(R.id.iv_title_finish);
        logo = (ImageView) findViewById(R.id.logo);
        usernameEt = (EditText) findViewById(R.id.et_username);
        passwordEt = (EditText) findViewById(R.id.et_password);
        loginBtn = (Button) findViewById(R.id.bt_login);
        registerTv = (TextView) findViewById(R.id.tv_register);
        forgetTv = (TextView) findViewById(R.id.tv_forget);
        radioGroup = (RadioGroup) findViewById(R.id.apiSelected);
    }

    @Override
    protected void initData() {
        titleTv.setText(UIUtil.getString(R.string.login));
        forgetTv.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        forgetTv.getPaint().setFakeBoldText(true);
        backIv.setVisibility(View.INVISIBLE);
        loginBtn.setOnClickListener(this);
        registerTv.setOnClickListener(this);
        forgetTv.setOnClickListener(this);
//        logo.setOnClickListener(this);
        radioGroup.setOnCheckedChangeListener(this);
        mvpPresenter.preLogin();
        usernameEt.setInputType(InputType.TYPE_CLASS_PHONE);
        usernameEt.setInputType(InputType.TYPE_CLASS_NUMBER);

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
                mvpPresenter.login(usernameEt, passwordEt, loginBtn);
                break;
            case R.id.tv_register:
                //注册
                UIUtil.openActivity(mActivity, RegistActivity.class);
                break;
            case R.id.tv_forget:
                UIUtil.openActivity(mActivity, ForgetPwdActivity.class);
                //忘记密码
                break;
            case R.id.logo:
                if(radioGroup.getVisibility()==View.GONE){
                    radioGroup.setVisibility(View.VISIBLE);
                }else{
                    radioGroup.setVisibility(View.GONE);
                }
                break;
        }
    }

    //视图
    @Override
    public void getDataSuccess(LoginModel model) {
        loginBtn.setEnabled(true);
        hideLoading();
        if (model.isSuccess()) {
            String username = usernameEt.getText().toString().trim();
            String password = passwordEt.getText().toString().trim();
            String userId = model.getUserId();
            String token = model.getToken();
            String type = "1";
            mvpPresenter.lastLogin(username, password, userId, token, type);
        } else {
            String errMsg = model.getErrMsg();
            if (errMsg.contains(UIUtil.getString(R.string.errMsg2))) {
                DialogUtil.showDialog();
            } else if (errMsg.contains(UIUtil.getString(R.string.errMsg3))) {
                ToastUtil.showToast(UIUtil.getString(R.string.errMsg3));
            } else if (errMsg.contains(UIUtil.getString(R.string.errMsg4))) {
                ToastUtil.showToast(UIUtil.getString(R.string.errMsg4));
            } else if (errMsg.contains(UIUtil.getString(R.string.errMsg5))) {
                ToastUtil.showToast(UIUtil.getString(R.string.errMsg5));
            } else {
                ToastUtil.showToast(UIUtil.getString(R.string.errMsg1));
            }
        }
    }

    @Override
    public void getDataFail(String msg) {
        loginBtn.setEnabled(true);
        hideLoading();
        toastShow("网络连接失败");
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

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.dy:
                ConstantValue.setApiServerUrl(ConstantValue.API_SERVER_URL1);
                break;
            case R.id.zf:
                ConstantValue.setApiServerUrl(ConstantValue.API_SERVER_URL2);
                break;
            case R.id.other:
                ConstantValue.setApiServerUrl(ConstantValue.API_SERVER_URL3);
                break;
        }
    }

}
