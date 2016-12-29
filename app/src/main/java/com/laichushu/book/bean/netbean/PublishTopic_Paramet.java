package com.laichushu.book.bean.netbean;

import java.io.Serializable;

/**
 * 发表话题
 * Created by PCPC on 2016/11/25.
 */

public class PublishTopic_Paramet implements Serializable {
    private String userId, title, content;
    private String type;
    private String partyId;

    public PublishTopic_Paramet(String userId, String partyId, String type, String title, String content) {
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.type = type;
        this.partyId = partyId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getPartyId() {
        return partyId;
    }

    public void setPartyId(String partyId) {
        this.partyId = partyId;
    }
}
