package com.laichushu.book.bean.netbean;

/**
 * 解散小组
 * Created by wangtong on 2016/12/30.
 */

public class DismissGroup_Paramet {
    public String teamId,userId;

    public DismissGroup_Paramet(String teamId, String userId) {
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
