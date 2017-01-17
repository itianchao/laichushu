package com.laichushu.book.bean.otherbean;

/**
 * 参数
 * Created by wangtong on 2017/1/17.
 */

public class ProbationNumModle {

    /**
     * success : true
     * data : {"endLimit":3,"url":"http://101.254.183.67:9980/group1/M00/00/1D/wKiTPlhPvYSAIdMtAAAaYb_qdnE05.epub"}
     */

    private boolean success;
    private DataBean data;
    private String errMsg;

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * endLimit : 3
         * url : http://101.254.183.67:9980/group1/M00/00/1D/wKiTPlhPvYSAIdMtAAAaYb_qdnE05.epub
         */

        private int endLimit;
        private String url;

        public int getEndLimit() {
            return endLimit;
        }

        public void setEndLimit(int endLimit) {
            this.endLimit = endLimit;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
