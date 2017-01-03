package com.laichushu.book.bean.netbean;

/**
 * 申请加入小组
 * Created by wangtong on 2017/1/3.
 */

public class ApplyJoinGroupMember_Paramet {
    private String userId;
    private String teamId;

    public ApplyJoinGroupMember_Paramet(String userId, String teamId) {
        this.userId = userId;
        this.teamId = teamId;
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
