package com.sofacity.laichushu.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.sofacity.laichushu.R;
import com.sofacity.laichushu.mvp.regist2.RegistModel2;
import com.sofacity.laichushu.mvp.regist2.RegistPresenter2;
import com.sofacity.laichushu.mvp.regist2.RegistView2;
import com.sofacity.laichushu.ui.base.MvpActivity;
import com.sofacity.laichushu.utils.ToastUtil;
import com.sofacity.laichushu.utils.UIUtil;

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
    }

    @Override
    protected RegistPresenter2 createPresenter() {
        return new RegistPresenter2(this);
    }

    @Override
    public void getDataSuccess(RegistModel2 model) {

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
                if ("".equals(name)) {
                    ToastUtil.showToast("请输入昵称");
                    return;
                }
                if ("".equals(sex)) {
                    ToastUtil.showToast("请选择性别");
                    return;
                }
                if ("".equals(pwd)) {
                    ToastUtil.showToast("请输入密码");
                    return;
                }
                if ("".equals(repwd)) {
                    ToastUtil.showToast("请输入密码");
                    return;
                }
                if (!pwd.equals(repwd)) {
                    ToastUtil.showToast("两次输入的密码不一致");
                    return;
                }
                //请求网络
                UIUtil.openActivity(mActivity, LoginActivity.class);
                break;
            case R.id.tv_sex:
                final String[] sexArray = {"男", "女"};
                AlertDialog.Builder builder = new AlertDialog.Builder(Regist2Activity.this);
                final int[] posion = new int[1];
                builder.setTitle("请选择性别")
                        .setSingleChoiceItems(sexArray,0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                posion[0] = which;
                            }
                        }).setNegativeButton("取消", null).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                                sexTv.setText(sexArray[posion[0]]);
                    }
                }).show();
                break;
        }
    }
}
