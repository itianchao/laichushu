package com.laichushu.book.mvp.campaign;

import java.util.ArrayList;

/**
 * 活动详情modle
 */
public class CampaignModel {

    /**
     * data : {"activityId":"24","activityName":"出版机构来合作吧","applyAmount":8,"beginTime":"2016-12-10","creator":"1","detail":"坚持\u201c先授权，后传播\u201d的理念，在电子图书内容领域占有明显优势，取得国内近三百家出版机构的合法授权，拥有数字内容二十余万种。","endTime":"2016-12-31","imgUrl":"http://101.254.183.67:9980/group1/M00/00/1A/wKiTPlhLphGABV-6AAAx0RvBjB8311.jpg","isParticipate":true,"result":[{"activityId":"24","articleId":"231","articleName":"啊啊","authorId":"159","nickName":"喵了个","photo":"http://101.254.183.67:9980/group1/M00/00/24/wKiTPlhaLl-AfxDVAACxsQe_PVo348.jpg","resultDesn":"","resultType":1},{"activityId":"24","articleId":"222","articleName":"夏了一个夏天","authorId":"150","nickName":"无尽之夏","photo":"http://101.254.183.67:9980/group1/M00/00/1F/wKiTPlhSE0uAZ7BfAAEuwbmelvg627.jpg","resultDesn":"","resultType":2}],"status":"4"}
     * success : true
     */
    private String errMsg;

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    private DataBean data;
    private boolean success;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static class DataBean {
        /**
         * activityId : 24
         * activityName : 出版机构来合作吧
         * applyAmount : 8
         * beginTime : 2016-12-10
         * creator : 1
         * detail : 坚持“先授权，后传播”的理念，在电子图书内容领域占有明显优势，取得国内近三百家出版机构的合法授权，拥有数字内容二十余万种。
         * endTime : 2016-12-31
         * imgUrl : http://101.254.183.67:9980/group1/M00/00/1A/wKiTPlhLphGABV-6AAAx0RvBjB8311.jpg
         * isParticipate : true
         * result : [{"activityId":"24","articleId":"231","articleName":"啊啊","authorId":"159","nickName":"喵了个","photo":"http://101.254.183.67:9980/group1/M00/00/24/wKiTPlhaLl-AfxDVAACxsQe_PVo348.jpg","resultDesn":"","resultType":1},{"activityId":"24","articleId":"222","articleName":"夏了一个夏天","authorId":"150","nickName":"无尽之夏","photo":"http://101.254.183.67:9980/group1/M00/00/1F/wKiTPlhSE0uAZ7BfAAEuwbmelvg627.jpg","resultDesn":"","resultType":2}]
         * status : 4
         */

        private String activityId;
        private String activityName;
        private int applyAmount;
        private String beginTime;
        private String creator;
        private String detail;
        private String endTime;
        private String imgUrl;
        private boolean isParticipate;
        private String status;
        private ArrayList<ResultBean> result;

        public boolean isParticipate() {
            return isParticipate;
        }

        public void setParticipate(boolean participate) {
            isParticipate = participate;
        }

        public String getActivityId() {
            return activityId;
        }

        public void setActivityId(String activityId) {
            this.activityId = activityId;
        }

        public String getActivityName() {
            return activityName;
        }

        public void setActivityName(String activityName) {
            this.activityName = activityName;
        }

        public int getApplyAmount() {
            return applyAmount;
        }

        public void setApplyAmount(int applyAmount) {
            this.applyAmount = applyAmount;
        }

        public String getBeginTime() {
            return beginTime;
        }

        public void setBeginTime(String beginTime) {
            this.beginTime = beginTime;
        }

        public String getCreator() {
            return creator;
        }

        public void setCreator(String creator) {
            this.creator = creator;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public boolean isIsParticipate() {
            return isParticipate;
        }

        public void setIsParticipate(boolean isParticipate) {
            this.isParticipate = isParticipate;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public ArrayList<ResultBean> getResult() {
            return result;
        }

        public void setResult(ArrayList<ResultBean> result) {
            this.result = result;
        }

        public static class ResultBean {
            /**
             * activityId : 24
             * articleId : 231
             * articleName : 啊啊
             * authorId : 159
             * nickName : 喵了个
             * photo : http://101.254.183.67:9980/group1/M00/00/24/wKiTPlhaLl-AfxDVAACxsQe_PVo348.jpg
             * resultDesn :
             * resultType : 1
             */

            private String activityId;
            private String articleId;
            private String articleName;
            private String authorId;
            private String nickName;
            private String photo;
            private String resultDesn;
            private int resultType;

            public String getActivityId() {
                return activityId;
            }

            public void setActivityId(String activityId) {
                this.activityId = activityId;
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

            public String getResultDesn() {
                return resultDesn;
            }

            public void setResultDesn(String resultDesn) {
                this.resultDesn = resultDesn;
            }

            public int getResultType() {
                return resultType;
            }

            public void setResultType(int resultType) {
                this.resultType = resultType;
            }
        }
    }
}
