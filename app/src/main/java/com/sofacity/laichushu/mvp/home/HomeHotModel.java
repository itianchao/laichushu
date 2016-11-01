package com.sofacity.laichushu.mvp.home;

import java.util.List;

/**
 * 首页热门
 * Created by wangtong on 2016/11/1.
 */
public class HomeHotModel {

    /**
     * success : true
     * data : [{"articleId":"16","articleName":"瑗挎父璁�","authorId":"寮犳檽","coverUrl":"http://192.168.147.62/group1/M00/00/00/wKiTPlgNxbuADGT0ABB9bkYLzjI276.jpg","coverName":"Mypsd_29771_201206250844140005B.jpg","status":"0","sort":"1"},{"articleId":"24","articleName":"澶�","authorId":"绯荤粺绠＄悊鍛�","coverUrl":"http://192.168.147.62/group1/M00/00/00/wKiTPlf_YSWAAAUDAAMW_jC4dU0232.jpg","coverName":"2113444_004433009618_2.jpg","status":"0","sort":"2"}]
     */

    private boolean success;
    /**
     * articleId : 16
     * articleName : 瑗挎父璁�
     * authorId : 寮犳檽
     * coverUrl : http://192.168.147.62/group1/M00/00/00/wKiTPlgNxbuADGT0ABB9bkYLzjI276.jpg
     * coverName : Mypsd_29771_201206250844140005B.jpg
     * status : 0
     * sort : 1
     */

    private List<DataBean> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String articleId;
        private String articleName;
        private String authorId;
        private String coverUrl;
        private String coverName;
        private String status;
        private String sort;

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

        public String getAuthorId() {
            return authorId;
        }

        public void setAuthorId(String authorId) {
            this.authorId = authorId;
        }

        public String getCoverUrl() {
            return coverUrl;
        }

        public void setCoverUrl(String coverUrl) {
            this.coverUrl = coverUrl;
        }

        public String getCoverName() {
            return coverName;
        }

        public void setCoverName(String coverName) {
            this.coverName = coverName;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }
    }
}
