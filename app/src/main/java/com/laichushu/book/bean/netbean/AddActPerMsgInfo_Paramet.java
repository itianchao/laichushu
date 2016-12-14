package com.laichushu.book.bean.netbean;

import java.io.Serializable;

/**
 * Created by PCPC on 2016/12/14.
 */

public class AddActPerMsgInfo_Paramet implements Serializable {
    private String senderId, accepterId, content;

    public AddActPerMsgInfo_Paramet(String senderId, String accepterId, String content) {
        this.senderId = senderId;
        this.accepterId = accepterId;
        this.content = content;
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
