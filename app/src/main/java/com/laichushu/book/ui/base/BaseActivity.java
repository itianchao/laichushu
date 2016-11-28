package com.laichushu.book.ui.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.Toast;

import com.laichushu.book.R;
import com.laichushu.book.retrofit.ApiStores;
import com.laichushu.book.retrofit.AppClient;
import com.laichushu.book.ui.widget.LoadDialog;
import com.laichushu.book.utils.AppManager;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;


import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class BaseActivity extends FragmentActivity {

    private long exitTime = 0;
    /**
     * 存储全局的activity
     */
    private static List<Activity> mActivities = new LinkedList<Activity>();

    /**
     * 当前前台的activity
     */
    private static Activity mForegroundActivity;

    public Activity mActivity;
    public ApiStores apiStores = AppClient.retrofit().create(ApiStores.class);
    private CompositeSubscription mCompositeSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getInstance().addActivity(this);
        AppManager.getActivityStackInfos();
        requestWindowFeature(Window.FEATURE_NO_TITLE); // 去掉标题
//        UIUtil.setNotifyBgColor(this, R.color.title_bg_color); // 设置通知栏颜色
        mActivity = this;
        synchronized (mActivities) {
            mActivities.add(this);
        }
        initView();
        initData();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mActivity = this;
        mForegroundActivity = this;
    }

    @Override
    protected void onPause() {
        super.onPause();
        mForegroundActivity = null;
    }

    public void exitApp() {
        ListIterator<Activity> iterator = mActivities.listIterator();
        while (iterator.hasNext()) {
            Activity next = iterator.next();
            next.finish();
        }
    }

    /**
     * 加载数据的方法，自己覆盖实现
     */
    protected void initData() {

    }

    /**
     * 初始化View的方法，子类如果有View的初始化，自己覆盖实现
     */
    protected void initView() {

    }

    /**
     * 获取前台activity
     *
     * @return
     */
    public Activity getForegroundActivity() {
        return mForegroundActivity;
    }

    /**
     * 返回上页
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            Activity foregroundActivity = getForegroundActivity();
            if (foregroundActivity!=null){
                if ("MainActivity".equals(foregroundActivity.getClass().getSimpleName())) {
                    exit();
                } else {
                    AppManager.getInstance().killActivity(this);
                }
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            AppManager.getInstance().killAllActivity();
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    }
    @Override
    protected void onDestroy() {
        onUnsubscribe();
        synchronized (mActivities) {
            AppManager.getInstance().killActivity(this);
        }
        super.onDestroy();
    }


    public void onUnsubscribe() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();//取消注册，以避免内存泄露
        }
    }

    public void addSubscription(Observable observable, Subscriber subscriber) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(observable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }

    public void addSubscription(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }
    public void toastShow(int resId) {
        ToastUtil.showToast(resId);
    }

    public void toastShow(String resId) {
        ToastUtil.showToast(resId);
    }

    public LoadDialog progressDialog;

    public LoadDialog showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new LoadDialog(mActivity);
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setTitle("加载中");
        }
        progressDialog.show();
        return progressDialog;
    }

    public LoadDialog showProgressDialog(CharSequence message) {
        if (progressDialog == null) {
            progressDialog = new LoadDialog(mActivity);
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setTitle(message);
        }
        progressDialog.show();
        return progressDialog;
    }

    public void dismissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            UIUtil.getMainThreadHandler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    progressDialog.dismiss();
                }
            }, 1600);
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.close_enter, R.anim.close_exit);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.open_enter, R.anim.open_exit);
    }
}
