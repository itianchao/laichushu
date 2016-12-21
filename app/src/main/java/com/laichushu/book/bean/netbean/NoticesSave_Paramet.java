package com.laichushu.book.bean.netbean;

import java.io.Serializable;

/**
 * Created by PCPC on 2016/12/20.
 */

public class NoticesSave_Paramet implements Serializable {
    private String partyId, title, content;

    public NoticesSave_Paramet(String partyId, String title, String content) {
        this.partyId = partyId;
        this.title = title;
        this.content = content;
    }

    public String getPartyId() {
        return partyId;
    }

    public void setPartyId(String partyId) {
        this.partyId = partyId;
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
}
