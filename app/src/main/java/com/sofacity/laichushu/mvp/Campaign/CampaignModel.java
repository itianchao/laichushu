package com.sofacity.laichushu.mvp.Campaign;

import java.util.ArrayList;

/**
 * 活动详情 结果
 * Created by wangtong on 2016/11/4.
 */
public class CampaignModel {

    /**
     * success : true
     * data : [{"activityId":"11","authorId":"105","nickName":"澶у啺","photo":"http://192.168.147.62/group1/M00/00/01/wKiTPlgYWiWAAJ8oAALeCroPT5I540.jpg","articleId":"74","articleName":"涔�,鎽告懜澶�","resultType":1,"resultDesn":"澶囨敞澶囨敞澶囨敞澶囨敞"}]
     */

    private boolean success;
    /**
     * activityId : 11
     * authorId : 105
     * nickName : 澶у啺
     * photo : http://192.168.147.62/group1/M00/00/01/wKiTPlgYWiWAAJ8oAALeCroPT5I540.jpg
     * articleId : 74
     * articleName : 涔�,鎽告懜澶�
     * resultType : 1
     * resultDesn : 澶囨敞澶囨敞澶囨敞澶囨敞
     */
    private String errorMsg;

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
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
        private String activityId;
        private String authorId;
        private String nickName;
        private String photo;
        private String articleId;
        private String articleName;
        private int resultType;
        private String resultDesn;

        public String getActivityId() {
            return activityId;
        }

        public void setActivityId(String activityId) {
            this.activityId = activityId;
        }

        public String getAuthorId() {
            return authorId;
        }

        public void setAuthorId(String authorId) {
            this.authorId = authorId;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

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

        public int getResultType() {
            return resultType;
        }

        public void setResultType(int resultType) {
            this.resultType = resultType;
        }

        public String getResultDesn() {
            return resultDesn;
        }

        public void setResultDesn(String resultDesn) {
            this.resultDesn = resultDesn;
        }
    }
}
