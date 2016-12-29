package com.laichushu.book.bean.netbean;

/**
 * 我发表的话题
 * Created by wangtong on 2016/12/29.
 */

public class MyPublishTopicList_Paramet {
    /***
     * type  1-个人话题，2-小组话题，3-机构话题
     */

    private String userId, type, pageNo, pageSize, teamId;

    public MyPublishTopicList_Paramet(String userId, String type, String pageNo, String pageSize, String teamId) {
        this.userId = userId;
        this.type = type;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.teamId = teamId;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
