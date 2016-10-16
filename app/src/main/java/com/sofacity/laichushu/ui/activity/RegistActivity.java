package com.sofacity.laichushu.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.sofacity.laichushu.R;
import com.sofacity.laichushu.mvp.regist.RegistCodeModel;
import com.sofacity.laichushu.mvp.regist.RegistModel;
import com.sofacity.laichushu.mvp.regist.RegistPresenter;
import com.sofacity.laichushu.mvp.regist.RegistView;
import com.sofacity.laichushu.ui.base.MvpActivity;
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

    private int	TIME = 60; // 倒计时60s
    private static final int	CODE_ING		= 1;				// 已发送，倒计时
    private static final int	CODE_REPEAT		= 2;				// 重新发送
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

    }

    @Override
    public void getDataSuccess(RegistCodeModel model) {
        updateBtnText();
    }

    @Override
    public void getDataFail(String msg) {

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

                if(TextUtils.isEmpty(phone)){
                    ToastUtil.showToast("帐号不能为空!");
                    return;
                }
                if(TextUtils.isEmpty(code)){
                    ToastUtil.showToast("验证码不能为空!");
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putString("phone", phone);
                UIUtil.openActivity(mActivity,Regist2Activity.class,bundle);
                break;
            case R.id.tv_agreement:
                mvpPresenter.loadCode(phone);
                break;
            case R.id.tv_code:
                break;
        }
    }

    // 更新获取验证码按钮
    private void updateBtnText(){
        String phonenum = phoneEt.getText().toString().trim();
        if(TextUtils.isEmpty(phonenum)){
            ToastUtil.showToast("手机号不能为空!");
            return;
        }
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
                    codeTv.setText("重新发送(" + --TIME + "s)");
                    break;
                case CODE_REPEAT:// 重新发送
                    codeTv.setText("获取验证码");
                    codeTv.setClickable(true);
                    break;
            }
        }
    };
}
