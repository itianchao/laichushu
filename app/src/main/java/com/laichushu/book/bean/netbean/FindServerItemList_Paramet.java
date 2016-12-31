package com.laichushu.book.bean.netbean;

/**
 * Created by PCPC on 2016/12/31.
 */

public class FindServerItemList_Paramet {
    private String userId,
            name,
            content,
            pageNo,
            pageSize;

    public FindServerItemList_Paramet(String userId, String name, String content, String pageNo, String pageSize) {
        this.userId = userId;
        this.name = name;
        this.content = content;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }
}
