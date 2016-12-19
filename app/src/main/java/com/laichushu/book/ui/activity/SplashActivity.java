package com.laichushu.book.ui.activity;


import android.Manifest;
import android.os.Build;
import android.text.TextUtils;
import android.widget.ImageView;

import com.laichushu.book.R;
import com.laichushu.book.bean.JsonBean.PreviewCoverBean;
import com.laichushu.book.ui.base.BaseActivity;
import com.laichushu.book.utils.SharePrefManager;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ObjectAnimator;

import java.util.ArrayList;
import java.util.List;

import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;

/**
 * 闪屏页
 * Created by wangtong on 2016/10/11.
 */
public class SplashActivity extends BaseActivity implements Animator.AnimatorListener {
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
        //判断版本
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            PermissionGen.with(SplashActivity.this)
                    .addRequestCode(100)
                    .permissions(
                            Manifest.permission.WAKE_LOCK,
                            Manifest.permission.CAMERA,
                            Manifest.permission.CLEAR_APP_CACHE,
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .request();

        }

        ObjectAnimator mAnimator = ObjectAnimator.ofFloat(splashIv, "alpha", 0, 0.25f, 0.5f, 0.75f, 1);
        mAnimator.setDuration(1000);
        mAnimator.start();
        mAnimator.addListener(this);
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
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @PermissionSuccess(requestCode = 100)
    public void success(){
        ObjectAnimator mAnimator = ObjectAnimator.ofFloat(splashIv, "alpha", 0, 0.25f, 0.5f, 0.75f, 1);
        mAnimator.setDuration(1000);
        mAnimator.start();
        mAnimator.addListener(this);
    }
    @PermissionFail(requestCode =100)
    public void fail() {
        //提示用户权限未被授予
        ToastUtil.showToast("权限已拒绝");
    }
    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {
        loadActivity();
    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }
}
