package com.sofacity.laichushu.utils;

import android.content.Intent;

import com.sofacity.laichushu.R;
import com.sofacity.laichushu.ui.activity.LoginActivity;
import com.sofacity.laichushu.ui.widget.LoadDialog;


/**
 * 账户已在其他设备登录
 * Created by wangtong on 2016/9/20.
 */
public class DialogUtil {
    private static LoadDialog mLoadDialog;
    public static void showDialog(){
        mLoadDialog = new LoadDialog(AppManager.getTopActivity());
        mLoadDialog.setCancelable(false);
        mLoadDialog.setCanceledOnTouchOutside(false);
        mLoadDialog.setTitle(UIUtil.getString(R.string.errMsg2));
        SharePrefManager.setLoginInfo("");
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
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        }, 1600);
    }
}
