package com.sofacity.laichushu.mvp.Campaign;

import java.util.ArrayList;

/**
 * 作者作品
 * Created by wangtong on 2016/11/4.
 */
public class AuthorWorksModle {

    /**
     * success : true
     * data : [{"articleId":"84","articleName":"1"},{"articleId":"83","articleName":"淇虹殑瀹�"},{"articleId":"82","articleName":"澶ф槑鐜嬫湞"},{"articleId":"81","articleName":"澶╅緳鍏儴"},{"articleId":"80","articleName":"浠欎緺浼�"},{"articleId":"74","articleName":"涔�,鎽告懜澶�"},{"articleId":"73","articleName":"鏋�"},{"articleId":"72","articleName":"绗笁甯濆浗鐨勫叴琛�"},{"articleId":"71","articleName":"浜屾鍏冧笘鐣�"},{"articleId":"70","articleName":"骞讳粰"},{"articleId":"66","articleName":"瀹跺洯"},{"articleId":"65","articleName":"澶╀娇澶у"},{"articleId":"63","articleName":"闃挎柉鍏扮殑鍙嶅嚮"},{"articleId":"49","articleName":"鏉冨埄鐨勬父鎴�"},{"articleId":"48","articleName":"榧庝赴"},{"articleId":"45","articleName":"鐩楀绗旇"},{"articleId":"24","articleName":"澶�"},{"articleId":"20","articleName":"浣欑姜"},{"articleId":"16","articleName":"瑗挎父璁�"},{"articleId":"14","articleName":"绾㈡ゼ姊�"},{"articleId":"69","articleName":"涓夊浗婕斾箟"}]
     */

    private boolean success;
    /**
     * articleId : 84
     * articleName : 1
     */

    private ArrayList<DataBean> data;
    private String errorMsg;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ArrayList<DataBean> getData() {
        return data;
    }

    public void setData(ArrayList<DataBean> data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public static class DataBean {
        private String articleId;
        private String articleName;
        private boolean ischeck;

        public String getArticleId() {
            return articleId;
        }

        public void setArticleId(String articleId) {
            this.articleId = articleId;
        }

        public String getArticleName() {
            return articleName;
        }

        public void setArticleName(String articleName) {
            this.articleName = articleName;
        }

        public void setIscheck(boolean ischeck) {
            this.ischeck = ischeck;
        }

        public boolean isIscheck() {
            return ischeck;
        }
    }
}
