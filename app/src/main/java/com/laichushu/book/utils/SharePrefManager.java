package com.laichushu.book.utils;

import com.laichushu.book.R;

public class SharePrefManager {

    /**
     * 保存阅读字体大小
     */
    public static void setCharacterNum(String characterNum) {
        CacheUtil.setString(UIUtil.getContext(), "characterNum", characterNum);
    }

    /**
     * 保存消息推送状态
     */
    public static void setMsgState(String msgState) {
        CacheUtil.setString(UIUtil.getContext(), "msgState", msgState);
    }

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
     * 保存nickName
     */
    public static void setNickName(String username) {
        CacheUtil.setString(UIUtil.getContext(), "nickName", username);
    }

    /**
     * 获取nickNAme
     */
    public static String getNickName() {
        return CacheUtil.getString(UIUtil.getContext(), "nickName", "");
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
        return CacheUtil.getInt(UIUtil.getContext(), "position", 0);
    }

    /**
     * 保存TextSize 字体大小
     */
    public static void setTextSize(int textSize) {
        CacheUtil.setInt(UIUtil.getContext(), "textSize", textSize);
    }

    /**
     * 获取TextSize
     */
    public static int getTextSize() {
        return CacheUtil.getInt(UIUtil.getContext(), "textSize", 30);
    }

    /**
     * 阅读设置是否显示toast
     */
    public static void setIsShowToast(boolean isShowToast) {
        CacheUtil.setBoolean(UIUtil.getContext(), "isShowToast", isShowToast);
    }

    /**
     * 阅读是否显示toast
     */
    public static boolean getIsShowToast() {
        return CacheUtil.getBoolean(UIUtil.getContext(), "isShowToast", true);
    }

    /**
     * 设置阅读样式
     */
    public static void setReadMoudle(int readMoudle) {
        CacheUtil.setInt(UIUtil.getContext(), "readMoudle", readMoudle);
    }

    /**
     * 阅读样式
     */
    public static int getReadMoudle() {
        return CacheUtil.getInt(UIUtil.getContext(), "readMoudle", R.drawable.reading__reading_themes_vine_white);
    }

    /**
     * 设置日间模式
     */
    public static void setReadState(int readState) {
        CacheUtil.setInt(UIUtil.getContext(), "readState", readState);
    }

    /**
     * 设置日间模式
     */
    public static int getReadState() {
        return CacheUtil.getInt(UIUtil.getContext(), "readState", getReadMoudle());
    }

    /**
     * 设置日间模式
     */
    public static void setType(String type) {
        CacheUtil.setString(UIUtil.getContext(), "type", type);
    }

    /**
     * 设置日间模式
     */
    public static String getType() {
        return CacheUtil.getString(UIUtil.getContext(), "type");
    }

    /**
     * 保存离线定位地址信息
     */
    public static void setAddressProvince(String address) {
        CacheUtil.setString(UIUtil.getContext(), "province", address);
    }

    public static String getAddressProvince() {
        return CacheUtil.getString(UIUtil.getContext(), "province", "北京市");
    }

    public static void setAddressCity(String address) {
        CacheUtil.setString(UIUtil.getContext(), "city", address);
    }

    public static String getAddressCity() {
        return CacheUtil.getString(UIUtil.getContext(), "city", "0101");
    }

    public static void setCityId(String cityId) {
        CacheUtil.setString(UIUtil.getContext(), "cityId", cityId);
    }

    public static String getCityId() {
        return CacheUtil.getString(UIUtil.getContext(), "cityId", "0101");
    }
}