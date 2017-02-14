package com.laichushu.book.utils;

import android.content.Intent;

import com.laichushu.book.db.Cache_Json;
import com.laichushu.book.db.Cache_JsonDao;
import com.laichushu.book.db.DaoSession;
import com.laichushu.book.global.BaseApplication;
import com.laichushu.book.ui.activity.LoginActivity;
import com.laichushu.book.ui.widget.LoadDialog;
import com.laichushu.book.R;


/**
 * 账户已在其他设备登录
 * Created by wangtong on 2016/9/20.
 */
public class DialogUtil {
    private static LoadDialog mLoadDialog;
    private static Cache_JsonDao cache_jsonDao;
    private static Cache_Json personalDetails;

    public static void showDialog(){
        mLoadDialog = new LoadDialog(AppManager.getTopActivity());
        mLoadDialog.setCancelable(false);
        mLoadDialog.setCanceledOnTouchOutside(false);
        mLoadDialog.setTitle(UIUtil.getString(R.string.errMsg2));
        SharePrefManager.setLoginInfo("");
        DaoSession daoSession = BaseApplication.getDaoSession(AppManager.getTopActivity());
        SharePrefManager.setUserId(null);
        cache_jsonDao = daoSession.getCache_JsonDao();
        personalDetails= cache_jsonDao.queryBuilder()
                .where(Cache_JsonDao.Properties.Inter.eq("PersonalDetails")).list().get(0);
        cache_jsonDao.delete(personalDetails);
        mLoadDialog.show();
        UIUtil.getMainThreadHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mLoadDialog.dismiss();
            }
        }, 1000);
        UIUtil.getMainThreadHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(UIUtil.getContext(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                UIUtil.getContext().startActivity(intent);
                AppManager.getInstance().killAllActivity();
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        }, 1600);
    }
}
