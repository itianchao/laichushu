package com.sofacity.laichushu.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.sofacity.laichushu.R;
import com.sofacity.laichushu.mvp.forgetpwd.ForgetPwdModel;
import com.sofacity.laichushu.mvp.forgetpwd.ForgetPwdPresenter;
import com.sofacity.laichushu.mvp.forgetpwd.ForgetPwdView;
import com.sofacity.laichushu.ui.base.MvpActivity;
import com.sofacity.laichushu.utils.UIUtil;

/**
 * 充值密码页面
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

    }

    @Override
    public void getDataFail(String msg) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.tv_getcode:
                break;
            case R.id.iv_title_finish:
                finish();
                break;
            case R.id.bt_finish:
                UIUtil.openActivity(mActivity,LoginActivity.class);
                break;
        }
    }
}
