package com.laichushu.book.bean.netbean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by PCPC on 2016/11/28.
 */

public class MessageCommentResult implements Serializable {

    /**
     * success : true
     * data : [{"id":"175","type":"3","subType":"8","senderId":"130","accepterId":"112","senderName":"追风筝的人1","accepterName":"哦的的哦","status":"1","content":"夏天评论了你发表的逗你玩","sendTime":"2016-11-30 18:58","senderPhoto":"http://101.254.183.67:9980/http://101.254.183.67:9980/group1/M00/00/13/wKiTPlg-SBuACrKtAAC_5dIvu_E510.jpg","sendLevel":"3","sendMark":"铜牌作家","sourceType":"","sourceId":"","sourceName":"","pressId":"","pressName":"","contributeStatusName":"","contributeStatus":"","articleId":"","coverUrl":""},{"id":"174","type":"3","subType":"8","senderId":"130","accepterId":"112","senderName":"追风筝的人1","accepterName":"哦的的哦","status":"1","content":"夏天评论了你发表的逗你玩","sendTime":"2016-11-30 18:56","senderPhoto":"http://101.254.183.67:9980/http://101.254.183.67:9980/group1/M00/00/13/wKiTPlg-SBuACrKtAAC_5dIvu_E510.jpg","sendLevel":"3","sendMark":"铜牌作家","sourceType":"","sourceId":"","sourceName":"","pressId":"","pressName":"","contributeStatusName":"","contributeStatus":"","articleId":"","coverUrl":""},{"id":"172","type":"3","subType":"8","senderId":"130","accepterId":"112","senderName":"追风筝的人1","accepterName":"哦的的哦","status":"1","content":"话题评论消息","sendTime":"2016-11-30 15:55","senderPhoto":"http://101.254.183.67:9980/http://101.254.183.67:9980/group1/M00/00/13/wKiTPlg-SBuACrKtAAC_5dIvu_E510.jpg","sendLevel":"3","sendMark":"铜牌作家","sourceType":"","sourceId":"","sourceName":"","pressId":"","pressName":"","contributeStatusName":"","contributeStatus":"","articleId":"","coverUrl":""},{"id":"5646762341","type":"2","senderId":"1","accepterId":"112","senderName":"admin","accepterName":"哦的的哦","content":"投稿","sendTime":"2016-11-30 02:47","senderPhoto":"","sendLevel":"3","sendMark":"铜牌作家","sourceType":"2","sourceId":"132","sourceName":"王晓明逛窑记","pressId":"","pressName":"","contributeStatusName":"","contributeStatus":"","articleId":"132","coverUrl":""},{"id":"164","type":"3","subType":"6","senderId":"112","accepterId":"112","senderName":"哦的的哦","accepterName":"哦的的哦","content":"112关注了你。","sendTime":"2016-11-29 14:23","senderPhoto":"http://101.254.183.67:9980/group1/M00/00/0F/wKiTPlg4FM2ANV-AAACe5hadIhg819.jpg","sourceType":"","sourceId":"","sourceName":"","pressId":"","pressName":"","contributeStatusName":"","contributeStatus":"","articleId":"","coverUrl":""},{"id":"163","type":"3","subType":"6","senderId":"112","accepterId":"112","senderName":"哦的的哦","accepterName":"哦的的哦","content":"112关注了你。","sendTime":"2016-11-29 14:20","senderPhoto":"http://101.254.183.67:9980/group1/M00/00/0F/wKiTPlg4FM2ANV-AAACe5hadIhg819.jpg","sourceType":"","sourceId":"","sourceName":"","pressId":"","pressName":"","contributeStatusName":"","contributeStatus":"","articleId":"","coverUrl":""},{"id":"140","type":"3","subType":"4","senderId":"130","accepterId":"112","senderName":"追风筝的人1","accepterName":"哦的的哦","status":"1","content":"评分消息","sendTime":"2016-11-26 18:19","senderPhoto":"http://101.254.183.67:9980/http://101.254.183.67:9980/group1/M00/00/13/wKiTPlg-SBuACrKtAAC_5dIvu_E510.jpg","sendLevel":"3","sendMark":"铜牌作家","sourceType":"","sourceId":"","sourceName":"","pressId":"","pressName":"","contributeStatusName":"","contributeStatus":"","articleId":"","coverUrl":""},{"id":"121","type":"3","subType":"2","senderId":"112","accepterId":"112","senderName":"哦的的哦","accepterName":"哦的的哦","status":"1","content":"收藏消息","sendTime":"2016-11-25 17:00","senderPhoto":"http://101.254.183.67:9980/group1/M00/00/0F/wKiTPlg4FM2ANV-AAACe5hadIhg819.jpg","sourceType":"","sourceId":"","sourceName":"","pressId":"","pressName":"","contributeStatusName":"","contributeStatus":"","articleId":"","coverUrl":""},{"id":"119","type":"3","subType":"2","senderId":"112","accepterId":"112","senderName":"哦的的哦","accepterName":"哦的的哦","status":"1","content":"收藏消息","sendTime":"2016-11-25 16:48","senderPhoto":"http://101.254.183.67:9980/group1/M00/00/0F/wKiTPlg4FM2ANV-AAACe5hadIhg819.jpg","sourceType":"","sourceId":"","sourceName":"","pressId":"","pressName":"","contributeStatusName":"","contributeStatus":"","articleId":"","coverUrl":""}]
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

    public static class DataBean implements Serializable {
        /**
         * id : 175
         * type : 3
         * subType : 8
         * senderId : 130
         * accepterId : 112
         * senderName : 追风筝的人1
         * accepterName : 哦的的哦
         * status : 1
         * content : 夏天评论了你发表的逗你玩
         * sendTime : 2016-11-30 18:58
         * senderPhoto : http://101.254.183.67:9980/http://101.254.183.67:9980/group1/M00/00/13/wKiTPlg-SBuACrKtAAC_5dIvu_E510.jpg
         * sendLevel : 3
         * sendMark : 铜牌作家
         * sourceType :
         * sourceId :
         * sourceName :
         * pressId :
         * pressName :
         * contributeStatusName :
         * contributeStatus :
         * articleId :
         * coverUrl :
         */

        private String id;
        private String type;
        private String subType;
        private String senderId;
        private String accepterId;
        private String senderName;
        private String accepterName;
        private String status;
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
        private String contributeStatusName;
        private String contributeStatus;
        private String articleId;
        private String coverUrl;
        private String articleName;
        private String authorId;
        private String authorName;

        public String getAuthorId() {
            return authorId;
        }

        public void setAuthorId(String authorId) {
            this.authorId = authorId;
        }

        public String getAuthorName() {
            return authorName;
        }

        public void setAuthorName(String authorName) {
            this.authorName = authorName;
        }

        public String getArticleName() {
            return articleName;
        }

        public void setArticleName(String articleName) {
            this.articleName = articleName;
        }

        public String getMsgId() {
            return msgId;
        }

        public void setMsgId(String msgId) {
            this.msgId = msgId;
        }

        private String msgId;

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        private String createDate;


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

        public String getSubType() {
            return subType;
        }

        public void setSubType(String subType) {
            this.subType = subType;
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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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

        public String getContributeStatusName() {
            return contributeStatusName;
        }

        public void setContributeStatusName(String contributeStatusName) {
            this.contributeStatusName = contributeStatusName;
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
