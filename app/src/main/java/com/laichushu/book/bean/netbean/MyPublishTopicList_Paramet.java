package com.laichushu.book.bean.netbean;

/**
 * 我的话题 modle
 * Created by wangtong on 2016/12/29.
 */

public class MyPublishTopicList_Paramet {
    /***
     * type  1-个人话题，2-小组话题，3-机构话题
     */

    private String userId, type, pageNo, pageSize, teamId,title;

    /**
     * 小组话题 （全部 or 推荐）
     */
    public MyPublishTopicList_Paramet(String userId, String type, String pageNo, String pageSize, String teamId) {
        this.userId = userId;
        this.type = type;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.teamId = teamId;
    }

    /**
     * 我搜索的话题
     */
    public MyPublishTopicList_Paramet(String userId, String type, String pageNo, String pageSize, String teamId,String title) {
        this.userId = userId;
        this.type = type;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.teamId = teamId;
        this.title = title;
    }
    /**
     * 我发表的话题
     */
    public MyPublishTopicList_Paramet(String userId, String type, String pageNo, String pageSize) {
        this.userId = userId;
        this.type = type;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
