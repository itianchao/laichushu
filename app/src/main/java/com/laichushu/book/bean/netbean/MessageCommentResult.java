package com.laichushu.book.bean.netbean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by PCPC on 2016/11/28.
 */

public class MessageCommentResult implements Serializable {

    /**
     * success : true
     * data : [{"id":"5646762341","type":"2","senderId":"1","accepterId":"112","senderName":"admin","accepterName":"哦的的哦","content":"投稿","sendTime":"2016-11-30 02:47","senderPhoto":"","sendLevel":"3","sendMark":"铜牌作家","sourceType":"2","sourceId":"132","sourceName":"12331313","pressId":"1","pressName":"中国书籍出版社","contributeStatus":"投稿审批中","articleId":"129","coverUrl":"http://101.254.183.67:9980/group1/M00/00/0D/wKiTPlg2XKyAPM1RAADKLyu_8nU623.jpg"}]
     */

    private boolean success;
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
        /**
         * id : 5646762341
         * type : 2
         * senderId : 1
         * accepterId : 112
         * senderName : admin
         * accepterName : 哦的的哦
         * content : 投稿
         * sendTime : 2016-11-30 02:47
         * senderPhoto :
         * sendLevel : 3
         * sendMark : 铜牌作家
         * sourceType : 2
         * sourceId : 132
         * sourceName : 12331313
         * pressId : 1
         * pressName : 中国书籍出版社
         * contributeStatus : 投稿审批中
         * articleId : 129
         * coverUrl : http://101.254.183.67:9980/group1/M00/00/0D/wKiTPlg2XKyAPM1RAADKLyu_8nU623.jpg
         */

        private String id;
        private String type;
        private String senderId;
        private String accepterId;
        private String senderName;
        private String accepterName;
        private String content;
        private String sendTime;
        private String senderPhoto;
        private String sendLevel;
        private String sendMark;
        private String sourceType;
        private String sourceId;
        private String sourceName;
        private String pressId;
        private String pressName;
        private String contributeStatus;
        private String articleId;
        private String coverUrl;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getSenderId() {
            return senderId;
        }

        public void setSenderId(String senderId) {
            this.senderId = senderId;
        }

        public String getAccepterId() {
            return accepterId;
        }

        public void setAccepterId(String accepterId) {
            this.accepterId = accepterId;
        }

        public String getSenderName() {
            return senderName;
        }

        public void setSenderName(String senderName) {
            this.senderName = senderName;
        }

        public String getAccepterName() {
            return accepterName;
        }

        public void setAccepterName(String accepterName) {
            this.accepterName = accepterName;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getSendTime() {
            return sendTime;
        }

        public void setSendTime(String sendTime) {
            this.sendTime = sendTime;
        }

        public String getSenderPhoto() {
            return senderPhoto;
        }

        public void setSenderPhoto(String senderPhoto) {
            this.senderPhoto = senderPhoto;
        }

        public String getSendLevel() {
            return sendLevel;
        }

        public void setSendLevel(String sendLevel) {
            this.sendLevel = sendLevel;
        }

        public String getSendMark() {
            return sendMark;
        }

        public void setSendMark(String sendMark) {
            this.sendMark = sendMark;
        }

        public String getSourceType() {
            return sourceType;
        }

        public void setSourceType(String sourceType) {
            this.sourceType = sourceType;
        }

        public String getSourceId() {
            return sourceId;
        }

        public void setSourceId(String sourceId) {
            this.sourceId = sourceId;
        }

        public String getSourceName() {
            return sourceName;
        }

        public void setSourceName(String sourceName) {
            this.sourceName = sourceName;
        }

        public String getPressId() {
            return pressId;
        }

        public void setPressId(String pressId) {
            this.pressId = pressId;
        }

        public String getPressName() {
            return pressName;
        }

        public void setPressName(String pressName) {
            this.pressName = pressName;
        }

        public String getContributeStatus() {
            return contributeStatus;
        }

        public void setContributeStatus(String contributeStatus) {
            this.contributeStatus = contributeStatus;
        }

        public String getArticleId() {
            return articleId;
        }

        public void setArticleId(String articleId) {
            this.articleId = articleId;
        }

        public String getCoverUrl() {
            return coverUrl;
        }

        public void setCoverUrl(String coverUrl) {
            this.coverUrl = coverUrl;
        }
    }
}
