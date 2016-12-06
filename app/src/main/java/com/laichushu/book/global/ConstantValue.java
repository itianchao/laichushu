package com.laichushu.book.global;

import android.os.Environment;

import com.laichushu.book.utils.SharePrefManager;

/**
 * Created by PCPC on 2016/11/18.
 */

public class ConstantValue {

    public static String GlidCacheUrl="files/file";
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
    public static String MSG_TYPE_1 = "1";//系统消息
    public static String MSG_TYPE_2 = "2";//投稿消息
    public static String MSG_TYPE_3 = "3";//个人消息
    // 消息列表--子列表
    public static String SUB_TYPE_1 = "1";//订阅
    public static String SUB_TYPE_2 = "2";//喜欢
    public static String SUB_TYPE_3 = "3";//点赞
    public static String SUB_TYPE_4 = "4";//评论
    public static String SUB_TYPE_5 = "5";//关注
    public static String SUB_TYPE_6 = "6";//打赏
    public static String SUB_TYPE_7 = "7";//活动通知
    public static String SUB_TYPE_8 = "8";//其他消息
    //图书话题类型
    public static String COMMENTBOOK_TYPE = "1";//图书
    public static String COMMENTTOPIC_TYPE = "2";//话题
    //收藏类型
    public static String COLLECTBOOK_TYPE = "1";//图书
    public static String MECHANISM_TYPE = "1";//机构
    public static String COLLECTTOPIC_TYPE = "3";//话题

    //baseBook
    public static final String BASEBOOK = "basebook";

    public static class FB_READER {

        // 打开非本地书籍
        public static final String ACTION_OPEN_FROM_SHELF = "action.fromShelf";

        // 从书城打开书籍
        public static final String ACTION_OPEN_FROM_BOOK_STORE = "action.fromBookStore";
    }


    //文件路径
    public static class LOCAL_PATH {

        // 文件保存在SD卡路径
        public static final String SD_PATH = Environment.getExternalStorageDirectory().getPath();
        // 项目名称
        public static final String PROJECT_NAME = "/laichushu";
        // 文件路径
        public static final String FILE = "/file/";
        // 图片路径
        public static final String IMAGE = "/image/";
        // apk路径
        public static final String APK = "/apk/";


    }


}
