package com.laichushu.book.bean.netbean;

import java.io.Serializable;

/**
 *
 * Created by PCPC on 2016/12/5.
 */

public class AddPerDetails_Paramet implements Serializable {
    private String senderId, msgId, content;

    public AddPerDetails_Paramet(String senderId, String msgId, String content) {
        this.senderId = senderId;
        this.msgId = msgId;
        this.content = content;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
