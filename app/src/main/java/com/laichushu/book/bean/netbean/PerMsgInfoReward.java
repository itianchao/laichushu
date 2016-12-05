package com.laichushu.book.bean.netbean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by PCPC on 2016/12/2.
 */

public class PerMsgInfoReward implements Serializable {

    /**
     * success : true
     * data : [{"id":"320","senderId":"112","senderName":"哦的的哦","content":"张峰很二/n13843838438","createDate":"2016-12-05 03:03","senderPhoto":"http://101.254.183.67:9980/group1/M00/00/15/wKiTPlhE0uaAIzBCAAC3Uzqum5U734.jpg","perMsgId":"304"},{"id":"7","senderId":"1","senderName":"admin","content":"2222","createDate":"2016-12-03 10:02","senderPhoto":"http://101.254.183.67:9980/group1/M00/00/15/wKiTPlhE0uaAIzBCAAC3Uzqum5U734.jpg","perMsgId":"304"},{"id":"305","senderId":"112","senderName":"哦的的哦","content":"今天为什么不下雨/n13843838438","createDate":"2016-12-03 08:07","senderPhoto":"http://101.254.183.67:9980/group1/M00/00/15/wKiTPlhE0uaAIzBCAAC3Uzqum5U734.jpg","perMsgId":"304"}]
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
         * id : 320
         * senderId : 112
         * senderName : 哦的的哦
         * content : 张峰很二/n13843838438
         * createDate : 2016-12-05 03:03
         * senderPhoto : http://101.254.183.67:9980/group1/M00/00/15/wKiTPlhE0uaAIzBCAAC3Uzqum5U734.jpg
         * perMsgId : 304
         */

        private String id;
        private String senderId;
        private String senderName;
        private String content;
        private String createDate;
        private String senderPhoto;
        private String perMsgId;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSenderId() {
            return senderId;
        }

        public void setSenderId(String senderId) {
            this.senderId = senderId;
        }

        public String getSenderName() {
            return senderName;
        }

        public void setSenderName(String senderName) {
            this.senderName = senderName;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getSenderPhoto() {
            return senderPhoto;
        }

        public void setSenderPhoto(String senderPhoto) {
            this.senderPhoto = senderPhoto;
        }

        public String getPerMsgId() {
            return perMsgId;
        }

        public void setPerMsgId(String perMsgId) {
            this.perMsgId = perMsgId;
        }
    }
}
