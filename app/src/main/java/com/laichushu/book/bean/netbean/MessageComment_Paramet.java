package com.laichushu.book.bean.netbean;

import java.io.Serializable;

/**
 * Created by PCPC on 2016/11/28.
 */

public class MessageComment_Paramet implements Serializable {
    private String pageSize, pageNo, sendId, receiveId, msgType, subType;

    public MessageComment_Paramet(String pageSize, String pageNo, String sendId, String receiveId, String msgType, String subType) {
        this.pageSize = pageSize;
        this.pageNo = pageNo;
        this.sendId = sendId;
        this.receiveId = receiveId;
        this.msgType = msgType;
        this.subType = subType;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
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
