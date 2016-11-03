package com.sofacity.laichushu.global;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Looper;

import com.sofacity.laichushu.db.DaoMaster;
import com.sofacity.laichushu.db.DaoSession;
import com.sofacity.laichushu.db.Search_HistoryDao;
import com.sofacity.laichushu.utils.SharePrefManager;

/**
 * 应用程序入口
 */
public class BaseApplication extends Application {
    private static Context mContext;
    private static Thread mMainThread;
    private static long mMainThreadId;
    private static Handler mMainThreadHandler;
    private static Looper mMainThreadLooper;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        // 主线程和子线程
        mMainThread = Thread.currentThread();
        // 主线程id
        mMainThreadId = android.os.Process.myTid();// 当前线程id
        // 主线程handler
        mMainThreadHandler = new Handler();
        mMainThreadLooper = getMainLooper();
        //首页RadiuButton设置
        SharePrefManager.setPosition(0);
        //重写系统的异常处理器
//        Thread.currentThread().setUncaughtExceptionHandler(new MyExceptionHandler());
    }

    public static Context getContext() {
        return mContext;
    }

    public static Thread getMainThread() {
        return mMainThread;
    }

    public static long getMainThreadId() {
        return mMainThreadId;
    }

    public static Handler getMainThreadHandler() {
        return mMainThreadHandler;
    }

    public static Looper getMainThreadLooper() {
        return mMainThreadLooper;
    }

    private class MyExceptionHandler implements Thread.UncaughtExceptionHandler {
        //当发现了未捕获异常的时候调用的方法
        @Override
        public void uncaughtException(Thread thread, Throwable ex) {
                android.os.Process.killProcess(android.os.Process.myPid());
        }
    }
}
