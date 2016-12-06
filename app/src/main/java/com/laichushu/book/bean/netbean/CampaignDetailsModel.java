package com.laichushu.book.bean.netbean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by PCPC on 2016/12/5.
 */

public class CampaignDetailsModel implements Serializable {

    /**
     * id : 11
     * name : 充值送好礼
     * imgUrl : http://101.254.183.67:9980/group1/M00/00/0B/wKiTPlgz1Z6AXKcSAAl5WLU-YRY022.jpg
     * detail : 充100返100
     * applyAmount : 1
     * startTime : 2016-10-18
     * endTime : 2016-10-18
     * data : [{"activityId":"11","authorId":"105","nickName":"大冰","photo":"","articleId":"73","articleName":"林","resultType":1,"resultDesn":"备注备注备注备注"}]
     * success : true
     */

    private String id;
    private String name;
    private String imgUrl;
    private String detail;
    private int applyAmount;
    private String startTime;
    private String endTime;
    private boolean success;
    private List<DataBean> data;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getApplyAmount() {
        return applyAmount;
    }

    public void setApplyAmount(int applyAmount) {
        this.applyAmount = applyAmount;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

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
        /**
         * activityId : 11
         * authorId : 105
         * nickName : 大冰
         * photo :
         * articleId : 73
         * articleName : 林
         * resultType : 1
         * resultDesn : 备注备注备注备注
         */

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
