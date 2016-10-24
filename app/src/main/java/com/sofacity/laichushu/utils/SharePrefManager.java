package com.sofacity.laichushu.utils;

public class SharePrefManager {

    /**
     * 初始化是否完成
     */
    public static void setInitBoolean(boolean flag) {
        CacheUtil.setBoolean(UIUtil.getContext(), "isInit", flag);
    }

    /**
     * 初始化是否完成
     */
    public static boolean getInitBoolean() {
        return CacheUtil.getBoolean(UIUtil.getContext(), "isInit");
    }

    /**
     * 保存登录UserId
     */
    public static void setUserId(String userId) {
        CacheUtil.setString(UIUtil.getContext(), "userId", userId);
    }

    /**
     * 获取登录UserId
     */
    public static String getUserId() {
        return CacheUtil.getString(UIUtil.getContext(), "userId", "");
    }

    /**
     * 保存登录token
     */
    public static void setToken(String token) {
        CacheUtil.setString(UIUtil.getContext(), "token", token);
    }

    /**
     * 获取登录token
     */
    public static String getToken() {
        return CacheUtil.getString(UIUtil.getContext(), "token", "");
    }

    /**
     * 保存登录Token2
     */
    public static void setToken2(String Token2) {
        CacheUtil.setString(UIUtil.getContext(), "token2", Token2);
    }

    /**
     * 获取登录Token2
     */
    public static String getToken2() {
        return CacheUtil.getString(UIUtil.getContext(), "token2", "");
    }

    /**
     * 保存username
     */
    public static void setUserName(String username) {
        CacheUtil.setString(UIUtil.getContext(), "username", username);
    }

    /**
     * 获取username
     */
    public static String getUserName() {
        return CacheUtil.getString(UIUtil.getContext(), "username", "");
    }

    /**
     * 保存登录信息
     */
    public static void setLoginInfo(String loginInfo) {
        CacheUtil.setString(UIUtil.getContext(), "loginInfo", loginInfo);
    }

    /**
     * 获取登录信息
     */
    public static String getLoginInfo() {
        return CacheUtil.getString(UIUtil.getContext(), "loginInfo", "");
    }

    /**
     * 保存是否第一次登录
     */
    public static void setFristLogin(boolean fristLogin) {
        CacheUtil.setBoolean(UIUtil.getContext(), "fristLogin", fristLogin);
    }

    /**
     * 获取是否第一次登录
     */
    public static boolean getFristLogin() {
        return CacheUtil.getBoolean(UIUtil.getContext(), "fristLogin", true);
    }

    /**
     * 保存home RadiuButton
     */
    public static void setPosition(int position) {
        CacheUtil.setInt(UIUtil.getContext(), "position", position);
    }

    /**
     * 获取home RadiuButton
     */
    public static int getPosition() {
        return CacheUtil.getInt(UIUtil.getContext(), "position",0);
    }
}