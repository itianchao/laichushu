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
        public static final String FileCacheCompress="cache:Compressor";

    }

    //baseUrl
    public static String API_SERVER_URL1 = "http://192.168.1.103:8082/book-app/";//张峰
    public static String API_SERVER_URL2 = "http://192.168.1.150:8082/book-app/";//施大勇5
    public static String API_SERVER_URL3 = "http://test2.laichushu.com/book-app-web/";
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
}
