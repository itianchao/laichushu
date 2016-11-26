package com.laichushu.book.bean.netbean;

import java.io.Serializable;

/**
 * Created by PCPC on 2016/11/26.
 */

public class FeedBack_parmet implements Serializable {
    private String userId, content, type, contactInfo;

    public FeedBack_parmet(String userId, String content, String type, String contactInfo) {
        this.userId = userId;
        this.content = content;
        this.type = type;
        this.contactInfo = contactInfo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }
}
