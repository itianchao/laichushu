package com.laichushu.book.global;

import android.os.Environment;

import com.laichushu.book.utils.SharePrefManager;
import com.laichushu.book.utils.UIUtil;

import java.io.File;

/**
 * Created by PCPC on 2016/11/18.
 */

public class ConstantValue {

    public static String GlidCacheUrl = "files/file";
    public static String ACTION_UPDATE_DATA = "ACTION_UPDATE_DATA";
    public static String ACTION_UPDATE_DATA_PERINFO = "ACTION_UPDATE_DATA_PERINFO";
    public static String ACTION_UPDATE_DATA_MINEINFO = "ACTION_UPDATE_DATA_MINEINFO";
    public static String USERID = SharePrefManager.getUserId();//userId
    public static String PAGESIZE = "2000";//pageSize
    public static String PAGESIZE1 = "10";//pageSize
    public static String PAGESIZE4 = "9";//pageSize
    public static String PAGESIZE2 = "20";//pageSize
    public static String PAGESIZE3 = "30";//pageSize
    public static String PAGESIZE5= "4";//pageSize
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
    public static String SUB_TYPE_4 = "3";//评论
    public static String SUB_TYPE_5 = "4";//关注
    public static String SUB_TYPE_6 = "5";//打赏
    public static String SUB_TYPE_7 = "7";//活动通知
    public static String SUB_TYPE_8 = "8";//其他消息
    //评论
    public static String BOOKCOMMENTTYPE = "1";//图书
    public static String TOPICCOMMENT_TYPE = "2";//话题
    //收藏类型
    public static String COMMENTBOOK_TYPE = "1";//图书
    public static String MECHANISM_TYPE = "2";//机构
    public static String COLLECTTOPIC_TYPE = "3";//话题
    public static String COLLECTSERVICE_TYPE = "4";//服务
    public static String COLLECTCOURSE_TYPE = "5";//课程
    public static String COLLECTEDITOR_TYPE = "6";//编辑
    //用户类型  作者:1  服务者:3  读者:4
    public static String AUTHOR = "1";
    public static String EDITOR = "2";
    public static String SERVICER = "3";
    public static String READER = "4";
    //分类类型
    public static String HOME_CATEGROY_TYPE = "1";//首页
    public static String CREADTBOOK_CATEGROY_TYPE = "2";//创建新书

    //短信验证码
    public static String REGIST_CODE = "1";//注册
    public static String FORGETPWD_CODE = "2";//忘记密码

    //小组状态
    public static String GROUP_START = "0";//0：启用，1：停用，3：解散
    public static String GROUP_END = "1";
    public static String GROUP_DISMISS = "3";
    //发现类型  1:编辑 2：服务者
    public static String FIND_EDITORTYPE = "1";
    public static String FIND_SERVICETYPE = "2";

    //搜索历史
    public static String SEARCH_TYPE_BOOK = "1";//图书
    public static String SEARCH_TYPE_GROUP = "2";//小组
    public static String SEARCH_TYPE_TOPIC = "3";//话题
    public static String SEARCH_TYPE_MECHANISM = "4";//机构
    public static String SEARCH_TYPE_VIDEO = "5";//视频
    public static String SEARCH_TYPE_DOCUMENT = "6";//文档
    //空字符串
    public static String STRING_NULL = "";
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
        public static final String SD_PATH = UIUtil.getContext().getFilesDir().getAbsolutePath() + File.separator;//应用根目录
        //        public static final String SD_PATH = Environment.getExternalStorageDirectory().getPath()+ File.separator;
        // 项目名称
        public static final String PROJECT_NAME = "/laichushu";
        // 文件路径
        public static final String FILE = "/file/";
        // 图片路径
        public static final String IMAGE = "/image/";
        // apk路径
        public static final String APK = "/apk/";
        // 图片压缩文件
        public static final String FileCacheCompress = "cache:Compressor";

    }

    //baseUrl
    public static String API_SERVER_URL1 = "http://192.168.1.103:8082/book-app/";//张峰
    public static String API_SERVER_URL2 = "http://192.168.1.150:8082/book-app/";//施大勇5
    public static String API_SERVER_URL3 = "http://test2.laichushu.com/book-app-web/";
    public static String API_SERVER_URL4 = "http://192.168.1.138:8082/book-app/";//胡体勇
    //    public static String API_SERVER_URL = "http://60.205.141.21:8099/";
//    public static String API_SERVER_URL = "http://192.168.191.1:8082/book-app/";
//    public static String API_SERVER_URL = "http://test2.laichushu.com/book-app-web/";
//      public static String API_SERVER_URL = "http://192.168.1.103:8082/book-app/";//张峰
//    public static String API_SERVER_URL = "http://192.168.1.119:8082/book-app/";//施大勇1
//    public static String API_SERVER_URL = "http://192.168.1.129:8082/book-app/";//施大勇2
//    public static String API_SERVER_URL = "http://192.168.1.148:8082/book-app/";//施大勇3
//    public static String API_SERVER_URL = "http://192.168.0.123:8082/book-app/";//施大勇4
    public static String API_SERVER_URL = "http://192.168.1.150:8082/book-app/";//施大勇5
//    public static String API_SERVER_URL = "http://192.168.1.130:8082/book-app/";//施大勇6
//    public static String API_SERVER_URL = "http://192.168.147.101:8082/book-app/";//张永生
//    public static String API_SERVER_URL = "http://test2.laichushu.com/book-app-web/";
//    public static String API_SERVER_URL = "http://192.168.1.122:8082/book-app/";//赵红江

    public static String getApiServerUrl() {
        return API_SERVER_URL;
    }

    public static void setApiServerUrl(String apiServerUrl) {
        API_SERVER_URL = apiServerUrl;
    }

    //支付回调
    public static final String ALIPAY_CALLBACK_URL = "";
    //微信ID
    public static final String WECHAT_APPID = "wx4c34cc3d9bc5a38b";

    // 商户PID
//    public static final String PARTNER = "2088221508305679";//品维会
//    public static final String PARTNER = "2088421691773363";//第二教室
    public static final String PARTNER = "";//来出书
    // 商户收款账号
//    public static final String SELLER = "pwdzsw@163.com";//品维会
//    public static final String SELLER = "zhongshuku@163.com";//第二教室
    public static final String SELLER = "";//来出书
    // 商户私钥，pkcs8格式
//    public static final String RSA_PRIVATE = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDCS5eAva1/yAuQD7WVMWPkEMwlCuvS9Bm0CeYZOFfwIyuLDtNKqwvT5AmleGH962nKo8KMGf21jdRI1ZHl+vc1WVZ3fBDDi2ya93dOjhtDs+1YUDluoMC1IIGutO9uFuQaODyNrcmIA7fGLk6X1MlxvTzmIBQQMAWLFbSqHiluxQIDAQAB";
    public static final String RSA_PRIVATE = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMJLl4C9rX/IC5APtZUxY+QQzCUK69L0GbQJ5hk4V/AjK4sO00qrC9PkCaV4Yf3racqjwowZ/bWN1EjVkeX69zVZVnd8EMOLbJr3d06OG0Oz7VhQOW6gwLUgga60724W5Bo4PI2tyYgDt8YuTpfUyXG9POYgFBAwBYsVtKoeKW7FAgMBAAECgYA6+qNUyz89iMYpxvsB/OorswWlQPlxIfQjeNXGVhorpSF0pt1wzxdaDOD4v+BPKgUFJEzMJp+jWxhTu7D8NQCRQpXOCrHOkjbqa6B3A4Vux7+NGSXhq6h+M0Q8GoEJJLZPlq3EmymFXMrH8XoFO46HCWaTQ26rxbpWuuD6+UEYAQJBAP9i1mSKKfObbw7TVIzCg+RjhfdzNXPtiyq21OYbgw5F2z2lxEwrlhiDUX1n2h2Msqd02RyBtVjVT8cLVRF0yQECQQDCwyjYUbIEQEsK9iUJZXwiblmbulKVNaiwc/J0Mf07iCJzP+JO0+hQp+z0M9aVI7+tjdOYj4Iiiwyu15UawcHFAkEAlCo9hBr8d87nwcwts3RunKR45rU6f8WDBgcIwW+Yu0EgD0YK+r4W2KXnM0B7NMWaKLkL9RPzqFQpcqtKcVQ2AQJABjeMmjshX5ldy9/HluEycTbsjVgJQtIPrHJHDwZ5eukBkIQ9iR6ij9CMc88jzmbxu2yHkJskIE4n/XzMOaptsQJAUO+eZ0URvyCHAQOBjs3E749l1FJMj1LmDV1EiDDd8yDjLpDUbPFfhshLUWNjr/EqQtJgElfeZn8LsrxwhHo1Ng==";
}
