package com.laichushu.book.utils;

import android.util.Base64;

import com.laichushu.book.global.ConstantValue;

import java.io.UnsupportedEncodingException;

/**
 * Created by PCPC on 2017/1/10.
 */

public class Base64Utils {

    public static String getStringUrl(String id, String type) {
        return ConstantValue.API_HYPERLINK_URL + "?shareData=" + getBase64("id=" + id + "&type=" + type + "&userId=" + SharePrefManager.getUserId());
    }
    public static String getBase64(String str) {
        String result = "";
        if (str != null) {
            try {
                result = new String(Base64.encode(str.getBytes("utf-8"), Base64.NO_WRAP), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    // 解密
    public static String getFromBase64(String str) {
        String result = "";
        if (str != null) {
            try {
                result = new String(Base64.decode(str, Base64.NO_WRAP), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
