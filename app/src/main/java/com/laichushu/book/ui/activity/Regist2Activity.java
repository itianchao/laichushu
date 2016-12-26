package com.laichushu.book.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.laichushu.book.mvp.login.LoginModel;
import com.laichushu.book.mvp.login.LoginPresenter;
import com.laichushu.book.mvp.regist2.RegistModel2;
import com.laichushu.book.ui.base.MvpActivity;
import com.laichushu.book.utils.DialogUtil;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.ToastUtil;
import com.orhanobut.logger.Logger;
import com.laichushu.book.R;
import com.laichushu.book.mvp.regist2.RegistPresenter2;
import com.laichushu.book.mvp.regist2.RegistView2;
import com.laichushu.book.utils.UIUtil;

/**
 * 注册页面
 * Created by wangtong on 2016/10/12.
 */
public class Regist2Activity extends MvpActivity<RegistPresenter2> implements RegistView2, View.OnClickListener {

    private TextView titleTv;
    private ImageView finishTv;
    private EditText nameEt;
    private TextView sexTv;
    private TextView phoneTv;
    private EditText pwdEt;
    private EditText repwdEt;
    private Button finishBtn;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_regist2);
        titleTv = (TextView) findViewById(R.id.tv_title);
        finishTv = (ImageView) findViewById(R.id.iv_title_finish);
        nameEt = (EditText) findViewById(R.id.et_name);
        sexTv = (TextView) findViewById(R.id.tv_sex);
        phoneTv = (TextView) findViewById(R.id.tv_phone);
        pwdEt = (EditText) findViewById(R.id.et_pwd);
        repwdEt = (EditText) findViewById(R.id.et_re_pwd);
        finishBtn = (Button) findViewById(R.id.bt_finish);
    }

    @Override
    protected void initData() {
        titleTv.setText("填写信息");
        sexTv.setOnClickListener(this);
        finishTv.setOnClickListener(this);
        finishBtn.setOnClickListener(this);
        String phone = mActivity.getIntent().getStringExtra("phone");
        phoneTv.setText(phone);
    }

    @Override
    protected RegistPresenter2 createPresenter() {
        return new RegistPresenter2(this);
    }

    @Override
    public void getDataSuccess(RegistModel2 model) {
        hideLoading();
        if (model.isSuccess()) {
            //登陆成功跳转页面
            ToastUtil.showToast("注册成功");
            UIUtil.postDelayed(new Runnable() {
                @Override
                public void run() {
                    showProgressDialog("登陆中...");
                    String phone = phoneTv.getText().toString().trim();
                    String pwd = pwdEt.getText().toString().trim();
                    mvpPresenter.loginData(phone,pwd);
                }
            }, 1710);
        }else {
            ToastUtil.showToast(model.getErrMsg());
            LoggerUtil.e(model.getErrMsg());
        }
    }

    @Override
    public void getDataFail(String msg) {
        toastShow("请检查网络");
        Logger.e("网络失败原因：", msg);
    }

    @Override
    public void showLoading() {
        showProgressDialog();
    }

    @Override
    public void hideLoading() {
        dismissProgressDialog();
    }

    /**
     * 登录接口成功
     * @param model
     */
    @Override
    public void getLoginSuccess(LoginModel model) {
        hideLoading();
        if (model.isSuccess()) {
            String username = phoneTv.getText().toString().trim();
            String password = pwdEt.getText().toString().trim();
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
            UIUtil.postStartActivity(mActivity, LoginActivity.class);
        }
    }

    /**
     * 登录接口失败
     * @param msg
     */
    @Override
    public void getLoginFail(String msg) {
        hideLoading();
        ToastUtil.showToast("登录失败");
        UIUtil.postStartActivity(mActivity, LoginActivity.class);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_finish:
                finish();
                break;
            case R.id.bt_finish:
                String name = nameEt.getText().toString().trim();
                String sex = sexTv.getText().toString().trim();
                String phonenum = phoneTv.getText().toString().trim();
                String pwd = pwdEt.getText().toString().trim();
                String repwd = repwdEt.getText().toString().trim();
                if (mvpPresenter.check(name, sex, pwd, repwd)) {
                    //请求网络
                    showLoading();
                    mvpPresenter.regist(phonenum, name, sex, pwd);
                }
                break;
            case R.id.tv_sex:
                mvpPresenter.getSex(sexTv);
                break;
        }
    }
}
