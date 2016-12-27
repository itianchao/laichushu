package com.laichushu.book.ui.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.laichushu.book.R;
import com.laichushu.book.bean.netbean.PersonalCentreResult;
import com.laichushu.book.db.Cache_Json;
import com.laichushu.book.db.Cache_JsonDao;
import com.laichushu.book.db.DaoSession;
import com.laichushu.book.global.BaseApplication;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.mvp.home.HomeHotModel;
import com.laichushu.book.mvp.home.HomeModel;
import com.laichushu.book.mvp.init.InitPresenter;
import com.laichushu.book.mvp.init.InitView;
import com.laichushu.book.ui.base.MvpActivity;
import com.laichushu.book.utils.SharePrefManager;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;

import java.lang.ref.WeakReference;
import java.util.List;

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
    private boolean four = false;

    private HomeModel homeModel;
    private HomeHotModel homeHotModel;
    private HomeHotModel homeAllModel;

    /**
     * handler
     */
    private MyHandler mhandler = new MyHandler(this);
    static class MyHandler extends Handler {
        private WeakReference<InitActivity> weakReference;

        MyHandler(InitActivity mActivity) {
            weakReference = new WeakReference<>(mActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            InitActivity mActivity = weakReference.get();
            if (mActivity != null) {
                if (mActivity.frist && mActivity.second && mActivity.thried && mActivity.four) {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("homeModel", mActivity.homeModel);
                    bundle.putParcelable("homeHotModel", mActivity.homeHotModel);
                    bundle.putParcelable("homeAllModel", mActivity.homeAllModel);
                    UIUtil.openActivity(mActivity, MainActivity.class, bundle);
                    mActivity.finish();
                }
            }
        }
    }

    private Cache_JsonDao cache_jsonDao;

    private InitActivity.UpdateReceiver mUpdateReceiver;

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
        registerPlayerReceiver();
        DaoSession daoSession = BaseApplication.getDaoSession(mActivity);
        cache_jsonDao = daoSession.getCache_JsonDao();
        List<Cache_Json> cache_jsons = cache_jsonDao.queryBuilder()
                .where(Cache_JsonDao.Properties.Inter.eq("PersonalDetails")).list();
        if (cache_jsons.isEmpty()) {
            mvpPresenter.loadMineData();//个人中心
        } else {
            four = true;
        }
        mvpPresenter.loadHomeCarouseData();//首页轮播图
        mvpPresenter.loadHomeHotData();//热门推荐
        mvpPresenter.loadHomeAllData("1");//全部图书
    }

    /**
     * 首页轮播图 成功返回数据
     * @param model
     */
    @Override
    public void getDataSuccess(HomeModel model) {
        if (model.isSuccess()) {
            this.homeModel = model;
            frist = true;
            Message msg = new Message();
            mhandler.sendMessage(msg);
        } else {
            ToastUtil.showToast(model.getErrMsg());
            getDataFail(model.getErrMsg());
        }
    }

    /**
     * 热门推荐 成功返回数据
     * @param model
     */
    @Override
    public void getHotDataSuccess(HomeHotModel model) {
        if (model.isSuccess()) {
            this.homeHotModel = model;
            second = true;
            Message msg = new Message();
            mhandler.sendMessage(msg);
        } else {
            ToastUtil.showToast(model.getErrMsg());
            getDataFail(model.getErrMsg());
        }
    }

    /**
     * 全部图书 成功返回数据
     * @param model
     */
    @Override
    public void getAllData(HomeHotModel model) {
        if (model.isSuccess()) {
            this.homeAllModel = model;
            thried = true;
            Message msg = new Message();
            mhandler.sendMessage(msg);
        } else {
            ToastUtil.showToast(model.getErrMsg());
            getDataFail(model.getErrMsg());
        }
    }

    /**
     * 个人中心 成功返回数据
     * @param model
     */
    @Override
    public void loadMineDataSuccess(PersonalCentreResult model) {
        if (model.getSuccess()) {
            //本地存储--->已存在更新
            cache_jsonDao.insert(new Cache_Json(null, "PersonalDetails", new Gson().toJson(model)));
            four = true;
            Message msg = new Message();
            mhandler.sendMessage(msg);
            //sharePerfance存储
            SharePrefManager.setNickName(model.getNickName());
        } else {
            ToastUtil.showToast(model.getErrMsg());
            getDataFail(model.getErrMsg());
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
    protected InitPresenter createPresenter() {
        return new InitPresenter(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.error_btn_retry:
                if (!frist) {
                    mvpPresenter.loadHomeCarouseData();
                }
                if (!second) {
                    mvpPresenter.loadHomeHotData();
                }
                if (!thried) {
                    mvpPresenter.loadHomeAllData("1");
                }
                if (!four) {
                    mvpPresenter.loadMineData();
                }
                break;
        }
    }

    public class UpdateReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (ConstantValue.ACTION_UPDATE_DATA_MINEINFO.equals(action)) {
                //更新信息
                mvpPresenter.loadMineData();
            }
        }
    }

    private void registerPlayerReceiver() {
        if (mUpdateReceiver == null) {
            mUpdateReceiver = new InitActivity.UpdateReceiver();
            IntentFilter filter = new IntentFilter();
            filter.addCategory(mActivity.getPackageName());
            filter.addAction(ConstantValue.ACTION_UPDATE_DATA_MINEINFO);
            mActivity.registerReceiver(mUpdateReceiver, filter);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mUpdateReceiver);
    }
}
