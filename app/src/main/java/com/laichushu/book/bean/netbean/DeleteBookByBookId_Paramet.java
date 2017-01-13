package com.laichushu.book.bean.netbean;

/**
 * Created by PCPC on 2017/1/13.
 */

public class DeleteBookByBookId_Paramet {
    private String collectId,browseId;

    public DeleteBookByBookId_Paramet(String collectId, String browseId) {
        this.collectId = collectId;
        this.browseId = browseId;
    }

    public String getCollectId() {
        return collectId;
    }

    public void setCollectId(String collectId) {
        this.collectId = collectId;
    }

    public String getBrowseId() {
        return browseId;
    }

    public void setBrowseId(String browseId) {
        this.browseId = browseId;
    }
}
