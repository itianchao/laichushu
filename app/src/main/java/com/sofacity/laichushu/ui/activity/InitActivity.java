package com.sofacity.laichushu.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.sofacity.laichushu.R;
import com.sofacity.laichushu.mvp.home.HomeAllModel;
import com.sofacity.laichushu.mvp.home.HomeHotModel;
import com.sofacity.laichushu.mvp.home.HomeModel;
import com.sofacity.laichushu.mvp.init.InitPresenter;
import com.sofacity.laichushu.mvp.init.InitView;
import com.sofacity.laichushu.ui.base.MvpActivity;
import com.sofacity.laichushu.utils.ToastUtil;
import com.sofacity.laichushu.utils.UIUtil;

/**
 * 初始化 数据
 * Created by wangtong on 2016/11/2.
 */
public class InitActivity extends MvpActivity<InitPresenter> implements InitView, View.OnClickListener {

    private ProgressBar loadingPb;
    private LinearLayout errorLay;
    private Button errorBtn;
    private boolean frist = false;
    private boolean second = false;
    private boolean thried = false;

    private HomeModel homeModel;
    private HomeHotModel homeHotModel;
    private HomeHotModel homeAllModel;
    private Handler mhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (frist && second && thried) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("homeModel", homeModel);
                bundle.putParcelable("homeHotModel", homeHotModel);
                bundle.putParcelable("homeAllModel",homeAllModel);
                UIUtil.openActivity(InitActivity.this, MainActivity.class, bundle);
                finish();
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void initView() {
        setContentView(R.layout.activity_init);
        loadingPb = (ProgressBar) findViewById(R.id.pb_loading);
        errorLay = (LinearLayout) findViewById(R.id.lay_error);
        errorBtn = (Button) findViewById(R.id.error_btn_retry);
        errorBtn.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        mvpPresenter.loadHomeCarouseData();
        mvpPresenter.loadHomeHotData();
        mvpPresenter.loadHomeAllData("1");
    }

    @Override
    public void getDataSuccess(HomeModel model) {
        if (model.isSuccess()) {
            this.homeModel = model;
            frist = true;
            Message msg = new Message();
            msg.what = 1;
            msg.obj = frist;
            mhandler.sendMessage(msg);
        } else {
            ToastUtil.showToast(model.getErrorMsg());
            getDataFail(model.getErrorMsg());
        }
    }

    @Override
    public void getHotDataSuccess(HomeHotModel model) {
        if (model.isSuccess()) {
            this.homeHotModel = model;
            second = true;
            Message msg = new Message();
            msg.what = 2;
            msg.obj = second;
            mhandler.sendMessage(msg);
        } else {
            ToastUtil.showToast(model.getErrorMsg());
            getDataFail(model.getErrorMsg());
        }
    }

    @Override
    public void getAllData(HomeHotModel model) {
        if (model.isSuccess()) {
            this.homeAllModel = model;
            thried = true;
            Message msg = new Message();
            msg.obj = thried;
            msg.what = 3;
            mhandler.sendMessage(msg);
        } else {
            ToastUtil.showToast(model.getErrorMsg());
            getDataFail(model.getErrorMsg());
        }
    }

    @Override
    public void getDataFail(String msg) {
        errorLay.setVisibility(View.VISIBLE);
        loadingPb.setVisibility(View.GONE);
    }

    @Override
    public void showLoading() {
        errorLay.setVisibility(View.GONE);
        loadingPb.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected InitPresenter createPresenter() {
        return new InitPresenter(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.error_btn_retry:
                mvpPresenter.loadHomeCarouseData();
                mvpPresenter.loadHomeHotData();
                mvpPresenter.loadHomeAllData("1");
                break;
        }
    }

    @Override
    public void finish() {
        super.finish();
    }
}
