package com.laichushu.book.bean.netbean;

import java.io.Serializable;

/**
 * Created by PCPC on 2016/12/6.
 */

public class AddPerMsgInfo_Paramet implements Serializable {
    private String senderId, accepterId, content;

    public AddPerMsgInfo_Paramet(String content, String accepterId, String senderId) {
        this.content = content;
        this.accepterId = accepterId;
        this.senderId = senderId;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
