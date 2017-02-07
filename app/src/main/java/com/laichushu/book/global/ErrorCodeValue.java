package com.laichushu.book.global;

/**
 * 网络错误码
 * Created by wangtong on 2017/2/7.
 */

public class ErrorCodeValue {
    //图书
    public static final String ARTICLE_DELETE = "110";//删除
    public static final String ARTICLE_FREEZE = "111";//冻结
    public static final String ARTICLE_UNONLINE = "112";//线下图书
    public static final String ARTICLE_ADD = "113";//下架
    public static final String ARTICLE_PUB = "114";//出版中
    public static final String ARTICLE_NO_PERMISSION = "115";//没权限
    public static final String ARTICLE_NO_EXIST = "116";//不存在
    //签名
    public static final String TOKEN_ILLEGAL = "104";//非法
    public static final String TOKEN_EMPTY = "105";//空
    //登录
    public static final String LOGINNAME_PASSWORD_EMPTY = "102";//密码是空
    public static final String PARAMS_EMPTY = "103";//没权限
    // system
    public static final String SYSTEM_ERROR = "500";
    public static final String NETWORK_ERROR = "502";
}
