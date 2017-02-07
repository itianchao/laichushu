package com.laichushu.book.utils;

import com.laichushu.book.ui.widget.LoadDialog;


/**
 * 图书详情权限
 * Created by wangtong on 2016/9/20.
 */
public class ArticleDialogUtil {
    private static LoadDialog mLoadDialog;

    public static void showDialog(String msg) {
        mLoadDialog = new LoadDialog(AppManager.getTopActivity());
        mLoadDialog.setCancelable(false);
        mLoadDialog.setCanceledOnTouchOutside(false);
        mLoadDialog.setTitle(msg);
        mLoadDialog.show();
        UIUtil.getMainThreadHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mLoadDialog.dismiss();
            }
        }, 1600);
        AppManager.getTopActivity().finish();
    }
}
