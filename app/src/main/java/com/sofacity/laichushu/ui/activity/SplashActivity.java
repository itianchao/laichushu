package com.sofacity.laichushu.ui.activity;


import android.app.Activity;
import android.text.TextUtils;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.ValueAnimator;
import com.sofacity.laichushu.R;
import com.sofacity.laichushu.ui.base.BaseActivity;
import com.sofacity.laichushu.utils.SharePrefManager;
import com.sofacity.laichushu.utils.UIUtil;

/**
 * 闪屏页
 * Created by wangtong on 2016/10/11.
 */
public class SplashActivity extends BaseActivity {
    //跳转页面
    private Class loadActivity;
    //启动页图片
    private ImageView splashIv;

    @Override
    protected void initView() {
        super.initView();
        setContentView(R.layout.activity_splash);
        splashIv = (ImageView)findViewById(R.id.iv_splash);
    }
    //动画
    @Override
    protected void initData() {
        ObjectAnimator mAnimator = ObjectAnimator.ofFloat(splashIv, "alpha", 0, 0.25f, 0.5f, 0.75f, 1);
        mAnimator.setDuration(1000);
        mAnimator.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadActivity();
    }
    private void loadActivity( ){
//        如果保存了帐号和密码，跳转主页面
        if (!TextUtils.isEmpty(SharePrefManager.getLoginInfo())) {
            loadActivity = MainActivity.class;
        }else {
            //如果第一次登录则跳转引导页
            if (SharePrefManager.getFristLogin()) {
                loadActivity = GuideActivity.class;
            //如果不是第一次登录则跳转登录页
            }else {
                loadActivity = LoginActivity.class;
            }
        }
        UIUtil.postDelayed(new Runnable() {
            @Override
            public void run() {
                UIUtil.openActivity(mActivity, loadActivity);
                finish();
            }
        }, 2000);
    }
}
