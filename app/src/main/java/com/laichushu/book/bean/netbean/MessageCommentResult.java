package com.laichushu.book.bean.netbean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by PCPC on 2016/11/28.
 */

public class MessageCommentResult implements Serializable {


    /**
     * success : true
     * data : [{"id":"140","type":"3","subType":"4","senderId":"130","accepterId":"112","senderName":"夏天","accepterName":"123","status":"1","content":"评分消息","sendTime":"2016-11-26 18:19"},{"id":"121","type":"3","subType":"2","senderId":"112","accepterId":"112","senderName":"123","accepterName":"123","status":"1","content":"收藏消息","sendTime":"2016-11-25 17:00"},{"id":"120","type":"3","subType":"2","senderId":"112","accepterId":"112","senderName":"123","accepterName":"123","status":"1","content":"收藏消息","sendTime":"2016-11-25 16:58"},{"id":"119","type":"3","subType":"2","senderId":"112","accepterId":"112","senderName":"123","accepterName":"123","status":"1","content":"收藏消息","sendTime":"2016-11-25 16:48"},{"id":"118","type":"3","subType":"2","senderId":"112","accepterId":"112","senderName":"123","accepterName":"123","status":"1","content":"收藏消息","sendTime":"2016-11-25 16:48"},{"id":"117","type":"3","subType":"2","senderId":"112","accepterId":"112","senderName":"123","accepterName":"123","status":"1","content":"收藏消息","sendTime":"2016-11-25 16:48"},{"id":"103","type":"3","subType":"7","senderId":"127","accepterId":"112","senderName":"一毛","accepterName":"123","status":"1","content":"打赏消息","sendTime":"2016-11-22 10:35"},{"id":"102","type":"3","subType":"7","senderId":"127","accepterId":"112","senderName":"一毛","accepterName":"123","status":"1","content":"打赏消息","sendTime":"2016-11-22 10:33"}]
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
         * id : 140
         * type : 3
         * subType : 4
         * senderId : 130
         * accepterId : 112
         * senderName : 夏天
         * accepterName : 123
         * status : 1
         * content : 评分消息
         * sendTime : 2016-11-26 18:19
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
    }
}
