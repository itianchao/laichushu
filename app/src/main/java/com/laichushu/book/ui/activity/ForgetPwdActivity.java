package com.laichushu.book.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.mvp.entry.forgetpwd.ForgetPwdModel;
import com.laichushu.book.mvp.entry.forgetpwd.ForgetPwdPresenter;
import com.laichushu.book.mvp.entry.forgetpwd.ForgetPwdView;
import com.laichushu.book.ui.base.MvpActivity;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;
import com.orhanobut.logger.Logger;
import com.laichushu.book.R;

/**
 * 重置密码页面
 * Created by wangtong on 2016/10/12.
 */
public class ForgetPwdActivity extends MvpActivity<ForgetPwdPresenter> implements ForgetPwdView, View.OnClickListener {

    private TextView titleTv;
    private ImageView finishTv;
    private EditText phoneEt;
    private EditText codeEt;
    private EditText newPwdEt;
    private EditText rePwdEt;
    private TextView codeTv;
    private Button finishBtn;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_forgetpwd);
        titleTv = (TextView) findViewById(R.id.tv_title);
        finishTv = (ImageView) findViewById(R.id.iv_title_finish);
        phoneEt = (EditText) findViewById(R.id.et_phone);
        codeEt = (EditText) findViewById(R.id.et_code);
        newPwdEt = (EditText) findViewById(R.id.et_new_pwd);
        rePwdEt = (EditText) findViewById(R.id.et_re_pwd);
        codeTv = (TextView) findViewById(R.id.tv_getcode);
        finishBtn = (Button) findViewById(R.id.bt_finish);
    }

    @Override
    protected void initData() {
        titleTv.setText("重置密码");
        codeTv.setOnClickListener(this);
        finishTv.setOnClickListener(this);
        finishBtn.setOnClickListener(this);
    }

    @Override
    protected ForgetPwdPresenter createPresenter() {
        return new ForgetPwdPresenter(this);
    }

    @Override
    public void getDataSuccess(ForgetPwdModel model) {
        hideLoading();
        if (model.isSuccess()) {
            ToastUtil.showToast("修改密码成功");
            UIUtil.postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            }, 1700);
        }else {
            ToastUtil.showToast(model.getErrMsg());
        }
    }

    @Override
    public void getCodeDataSuccess(RewardResult model) {
    }

    @Override
    public void getDataFail(String msg) {
        toastShow(msg);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_getcode:
                String phonenum = phoneEt.getText().toString().trim();
                mvpPresenter.showCode(codeTv, phonenum);
                break;
            case R.id.iv_title_finish:
                finish();
                break;
            case R.id.bt_finish:
                String code = codeEt.getText().toString().trim();
                String phone = phoneEt.getText().toString().trim();
                String newPwd = newPwdEt.getText().toString().trim();
                String rePwd = rePwdEt.getText().toString().trim();
                if (mvpPresenter.check(code,phone,newPwd,rePwd)) {
                    //请求网络校验
                    mvpPresenter.reset(phone,newPwd,rePwd);
                }
                break;
        }
    }
}
