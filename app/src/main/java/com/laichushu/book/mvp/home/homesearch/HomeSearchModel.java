package com.laichushu.book.mvp.home.homesearch;

import java.util.ArrayList;

/**
 * home页搜索
 * Created by wangtong on 2016/10/31.
 */
public class HomeSearchModel {

    /**
     * success : true
     * data : [{"articleId":"253","articleName":"鐞ョ弨涓栫晫"},{"articleId":"263","articleName":"浣犲ソ"},{"articleId":"216","articleName":"鎰挎棤宀佹湀鍙洖澶�"},{"articleId":"218","articleName":"瀹岀編涓栫晫"},{"articleId":"233","articleName":"妗冭姳搴垫瓕"}]
     */

    private boolean success;
    private String errMsg;

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    private ArrayList<DataBean> data;
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

    public static class DataBean {
        /**
         * articleId : 253
         * articleName : 鐞ョ弨涓栫晫
         */

        private String articleId;
        private String articleName;

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
    }
}