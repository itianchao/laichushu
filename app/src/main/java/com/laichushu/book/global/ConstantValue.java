package com.laichushu.book.global;

import com.laichushu.book.utils.SharePrefManager;

/**
 * Created by PCPC on 2016/11/18.
 */

public class ConstantValue {
    public static String ACTION_UPDATE_DATA = "ACTION_UPDATE_DATA";
    public static String USERID = SharePrefManager.getUserId();//userId
    public static String PAGESIZE = "2000";//pageSize
    public static String PAGESIZE1 = "10";//pageSize
    public static String PAGESIZE4 = "9";//pageSize
    public static String PAGESIZE2 = "20";//pageSize
    public static String PAGESIZE3 = "30";//pageSize
//    public static String PHOTO_SEVERCE_PATH = "http://101.254.183.67:9980/";//图片服务器路径

    public static int ACTIVITY_REQUEST_SELECT_PHOTO = 100;
    //消息列表
    public static String  MSG_TYPE_1="1";//系统消息
    public static String  MSG_TYPE_2="2";//投稿消息
    public static String  MSG_TYPE_3="3";//个人消息
    // 消息列表--子列表
    public static String  SUB_TYPE_1="1";//订阅
    public static String  SUB_TYPE_2="2";//喜欢
    public static String  SUB_TYPE_3="3";//点赞
    public static String  SUB_TYPE_4="4";//评分
    public static String  SUB_TYPE_5="5";//评论
    public static String  SUB_TYPE_6="6";//关注
    public static String  SUB_TYPE_7="7";//打赏
    public static String  SUB_TYPE_8="8";//私信
    //图书话题类型
    public static String  COMMENTBOOK_TYPE="1";//图书
    public static String  COMMENTTOPIC_TYPE="2";//话题
}
