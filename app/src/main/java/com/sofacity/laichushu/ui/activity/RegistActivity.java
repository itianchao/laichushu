package com.sofacity.laichushu.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.sofacity.laichushu.R;
import com.sofacity.laichushu.mvp.regist.RegistCodeModel;
import com.sofacity.laichushu.mvp.regist.RegistModel;
import com.sofacity.laichushu.mvp.regist.RegistPresenter;
import com.sofacity.laichushu.mvp.regist.RegistView;
import com.sofacity.laichushu.ui.base.MvpActivity;
import com.sofacity.laichushu.ui.widget.ServiceTermsDialog;
import com.sofacity.laichushu.utils.ToastUtil;
import com.sofacity.laichushu.utils.UIUtil;

/**
 * 注册页面
 * Created by wangtong on 2016/10/12.
 */
public class RegistActivity extends MvpActivity<RegistPresenter> implements RegistView, View.OnClickListener {

    private TextView titleTv;
    private ImageView finishTv;
    private EditText phoneEt;
    private EditText codeEt;
    private TextView codeTv;
    private CheckBox codeCk;
    private TextView agreementTv;
    private Button nextBtn;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_regist);
        titleTv = (TextView) findViewById(R.id.tv_title);
        finishTv = (ImageView) findViewById(R.id.iv_title_finish);
        phoneEt = (EditText) findViewById(R.id.et_phone);
        codeEt = (EditText) findViewById(R.id.et_code);
        codeTv = (TextView) findViewById(R.id.tv_code);
        codeCk = (CheckBox) findViewById(R.id.chkItem);
        agreementTv = (TextView) findViewById(R.id.tv_agreement);
        nextBtn = (Button) findViewById(R.id.bt_next);
    }

    @Override
    protected void initData() {
        titleTv.setText("注册");
        nextBtn.setOnClickListener(this);
        finishTv.setOnClickListener(this);
        codeTv.setOnClickListener(this);
        agreementTv.setOnClickListener(this);
    }

    @Override
    protected RegistPresenter createPresenter() {
        return new RegistPresenter(this);
    }

    @Override
    public void getDataSuccess(RegistModel model) {
        if (model.isSuccess()) {
            String phone = phoneEt.getText().toString().trim();
            //校验验证码是否正确
            Bundle bundle = new Bundle();
            bundle.putString("phone", phone);
            UIUtil.openActivity(mActivity, Regist2Activity.class, bundle);
        }else {
            ToastUtil.showToast(model.getErrMsg().toString());
        }
    }

    @Override
    public void getDataSuccess(RegistCodeModel model) {
        //获取验证码
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
        String phone = phoneEt.getText().toString().trim();
        String code = codeEt.getText().toString().trim();
        switch(v.getId()){
            case R.id.iv_title_finish:
                finish();
                break;
            case R.id.bt_next:
                //前台校验是否成功
                if (mvpPresenter.check(phone,code,!codeCk.isChecked())) {
                    showLoading();
                    mvpPresenter.valid(phone, code);
                }
                break;
            //用户协议
            case R.id.tv_agreement:
                new ServiceTermsDialog(mActivity).show();
                break;
            case R.id.tv_code:
                String phonenum = phoneEt.getText().toString().trim();
                mvpPresenter.showCode(codeTv, phonenum);
                break;
        }
    }

}
