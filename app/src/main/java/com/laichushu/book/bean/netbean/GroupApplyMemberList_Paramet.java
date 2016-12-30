package com.laichushu.book.bean.netbean;

/**
 * 小组成员列表
 * Created by wangtong on 2016/12/30.
 */

public class GroupApplyMemberList_Paramet {
    private String teamId;
    private String userId;

    public GroupApplyMemberList_Paramet(String teamId,String userId) {
        this.teamId = teamId;
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }
}
