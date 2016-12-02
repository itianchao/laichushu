package com.laichushu.book.bean.netbean;

/**
 * Created by PCPC on 2016/12/1.
 */

public class SendMsgDetails_Paramet {
    private String sendId, receiveId, content, msgType, subType;

    public SendMsgDetails_Paramet(String sendId, String receiveId, String content, String msgType, String subType) {
        this.sendId = sendId;
        this.receiveId = receiveId;
        this.content = content;
        this.msgType = msgType;
        this.subType = subType;
    }

    public String getSendId() {
        return sendId;

    }

    public void setSendId(String sendId) {
        this.sendId = sendId;
    }

    public String getReceiveId() {
        return receiveId;
    }

    public void setReceiveId(String receiveId) {
        this.receiveId = receiveId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }
}
