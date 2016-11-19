package com.laichushu.book.utils;

import android.os.Bundle;

import com.google.gson.Gson;
import com.laichushu.book.BuildConfig;
import com.orhanobut.logger.Logger;

/**
 * 对logger二次封装
 * Created by wangtong on 2016/11/19.
 */

public class LoggerUtil {

    private static boolean debug=BuildConfig.DEBUG;

    /**
     * 打印json log
     * @param paramet 对象
     */
    public static void toJson(Object paramet){
        if (debug){
            Logger.json(new Gson().toJson(paramet));
        }
    }
    /**
     * 打印String log
     * @param msg 字符串
     */
    public static void e(String msg){
        if (debug){
            Logger.e(msg);
        }
    }
}
