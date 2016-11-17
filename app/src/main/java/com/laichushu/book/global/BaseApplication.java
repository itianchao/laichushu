package com.laichushu.book.global;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.laichushu.book.db.DaoSession;
import com.laichushu.book.utils.SharePrefManager;
import com.laichushu.book.db.DaoMaster;

/**
 * 应用程序入口
 */
public class BaseApplication extends Application {
    private static Context mContext;
    private static Thread mMainThread;
    private static long mMainThreadId;
    private static Handler mMainThreadHandler;
    private static Looper mMainThreadLooper;
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;

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
    public static DaoMaster getDaoMaster(Context context) {
        if (daoMaster == null) {
            DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(context,
                    "laichushu.db", null);
            daoMaster = new DaoMaster(helper.getWritableDatabase());
        }
        return daoMaster;
    }

    /**
     * 取得DaoSession
     *
     * @param context
     * @return
     */
    public static DaoSession getDaoSession(Context context) {
        if (daoSession == null) {
            if (daoMaster == null) {
                daoMaster = getDaoMaster(context);
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }
//    /**
//     *  通过 DaoMaster 的内部类 DevOpenHelper创建数据库
//     *  注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表。
//     *  所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级（先备份数据再升级）。
//     *
//     * 参数1：Context
//     * 参数2: 要创建的数据库名字
//     * 参数3 : CursorFactroy
//     */
//    DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(mActivity, "laichushu.db", null);
//    //        // 获取数据库
//    SQLiteDatabase database = helper.getWritableDatabase();
//    //        // 获取DaoMaster
//    DaoMaster daoMaster = new DaoMaster(database);
//    //        // 获取Session
//    DaoSession daoSession = daoMaster.newSession();
//        // 获取对应的表的DAO对象
}