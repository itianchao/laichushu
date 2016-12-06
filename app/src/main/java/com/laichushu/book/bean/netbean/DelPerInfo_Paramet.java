package com.laichushu.book.bean.netbean;

import java.io.Serializable;

/**
 * Created by PCPC on 2016/12/5.
 */

public class DelPerInfo_Paramet implements Serializable {
    private String senderId, msgId, userId;

    public DelPerInfo_Paramet(String senderId, String msgId, String userId) {
        this.senderId = senderId;
        this.msgId = msgId;
        this.userId = userId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
