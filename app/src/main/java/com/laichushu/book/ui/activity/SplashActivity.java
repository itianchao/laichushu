package com.laichushu.book.ui.activity;


import android.text.TextUtils;
import android.widget.ImageView;

import com.laichushu.book.R;
import com.laichushu.book.bean.JsonBean.PreviewCoverBean;
import com.laichushu.book.ui.base.BaseActivity;
import com.laichushu.book.utils.SharePrefManager;
import com.laichushu.book.utils.UIUtil;
import com.nineoldandroids.animation.ObjectAnimator;

import java.util.ArrayList;
import java.util.List;

/**
 * 闪屏页
 * Created by wangtong on 2016/10/11.
 */
public class SplashActivity extends BaseActivity {
    //跳转页面
    private Class loadActivity;
    //启动页图片
    private ImageView splashIv;
    private List<PreviewCoverBean> proData = new ArrayList<>();

    @Override
    protected void initView() {
        super.initView();
        setContentView(R.layout.activity_splash);
        splashIv = (ImageView) findViewById(R.id.iv_splash);
    }

    //动画
    @Override
    protected void initData() {
        ObjectAnimator mAnimator = ObjectAnimator.ofFloat(splashIv, "alpha", 0, 0.25f, 0.5f, 0.75f, 1);
        mAnimator.setDuration(1000);
        mAnimator.start();
        //
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadActivity();
    }

    private void loadActivity() {
//        如果保存了帐号和密码，跳转主页面
        if (!TextUtils.isEmpty(SharePrefManager.getLoginInfo())) {
            loadActivity = InitActivity.class;
        } else {
            //如果第一次登录则跳转引导页
            if (SharePrefManager.getFristLogin()) {
                loadActivity = GuideActivity.class;
                //如果不是第一次登录则跳转登录页
            } else {
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
//        BaseBookEntity baseBookEntity = new BaseBookEntity();
//        baseBookEntity.setBook_path(ConstantValue.LOCAL_PATH.SD_PATH+"/test.epub");
//        UIUtil.startBookFBReaderActivity(this,baseBookEntity);
    }


}
