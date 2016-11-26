package com.laichushu.book.bean.netbean;

/**
 * 给机构发消息 参数
 * Created by wangtong on 2016/11/26.
 */

public class SendMsgToParty_Paramet {
    private String sendId;//    发送者id
    private String receiveId;//   机构ID
    private String content;//  内容

    public SendMsgToParty_Paramet(String sendId, String receiveId, String content) {
        this.sendId = sendId;
        this.receiveId = receiveId;
        this.content = content;
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
}
