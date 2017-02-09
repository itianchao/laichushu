package com.laichushu.book.utils;


/**
 * 图书详情权限
 * Created by wangtong on 2016/9/20.
 */
public class NetDialogUtil {

    public static void showToast(String msg) {
        ToastUtil.showToast(msg,AppManager.getTopActivity());
    }
}
